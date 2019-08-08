package com.jiangkai.framework.admin.timetask;

import com.jiangkai.framework.admin.common.enums.Status;
import com.jiangkai.framework.admin.common.enums.SumType;
import com.jiangkai.framework.admin.mapper.CustomSQL;
import com.jiangkai.framework.admin.timetask.summary.util.SummaryRedisUtil;
import com.jiangkai.framework.admin.util.DateUtils;
import com.jiangkai.framework.source.mapper.*;
import com.jiangkai.framework.source.model.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.mybatis.dynamic.sql.select.aggregate.Sum;
import org.mybatis.dynamic.sql.where.condition.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


/**
 * User: zvbb
 * Date: 2019/5/14
 * Description:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JobManager {
    private final DeptMapper deptMapper;
    private final DriverMapper driverMapper;
    private final DataSourceMapper dataSourceMapper;
    private final SummaryDeptBedMapper summaryDeptBedMapper;
    private final SummaryDeptPersonMapper summaryDeptPersonMapper;
    private final BedInfoMapper bedInfoMapper;
    private final CustomSQL customSQL;
    private final BedActiveMapper bedActiveMapper;
    private final BedRecordMapper bedRecordMapper;

    //TODO 需要解决环形依赖
    @Autowired
    private PullDataJob pullDataJob;

    //数据源 到  部门的映射
    @Getter
    private ConcurrentHashMap<Long, Long> datasource2Dept = new ConcurrentHashMap();
    //部门 到  数据源的映射
    @Getter
    private ConcurrentHashMap<Long, Long> dept2Datasource = new ConcurrentHashMap<>();
    //当前所有床铺 <所属部门,<床铺编号,所属部门>>
    private ConcurrentHashMap<Long, ConcurrentHashMap<String, Long>> deptBedMap = new ConcurrentHashMap<>();
    //各部门的床铺数
    private ConcurrentHashMap<Long, AtomicLong> deptBedCnt = new ConcurrentHashMap<>();
    //记录床铺属于那个部门
    private ConcurrentHashMap<String, Long> activeBedMap = new ConcurrentHashMap<>();
    //各部门当月 使用过的 床铺数
    private ConcurrentHashMap<String, AtomicLong> deptActiveBedCnt = new ConcurrentHashMap<>();

    //当前所有司机<司机编号,部门id>
    private ConcurrentHashMap<String, Long> driverMap = new ConcurrentHashMap<>();
    //各部门的司机数
    private ConcurrentHashMap<Long, AtomicLong> deptDriverCnt = new ConcurrentHashMap();

    //部门前缀 到 部门ID
    private ConcurrentHashMap<String, Long> deptPrefix2DeptId = new ConcurrentHashMap<>();
    //部门之间的层级关系
    private ConcurrentHashMap<Long, Long> deptHierarchyMap = new ConcurrentHashMap<>();
    //记录上次读取到 Info_Inteface 表的那条数据,这里记录下主键
    private ConcurrentHashMap<Long, Integer> deptMainIDMap = new ConcurrentHashMap<>();
    //记录上次读到的 Info_Inteface 表中的数据，哪些记录的finish字段不等于2
    private ConcurrentHashMap<Long, List<Integer>> deptUnFinishRecordsMap = new ConcurrentHashMap<>();
    //记录上次读取到 Info_Leave 表的那条数据,这里记录下主键
    //<deptID,ID>
    private ConcurrentHashMap<Long, Integer> deptLeaveIDMap = new ConcurrentHashMap<>();

    @Getter
    @Setter
    private ConcurrentHashMap<String, SummaryDeptBed> summaryDeptBedHashMap = new ConcurrentHashMap<>();

    @Getter
    @Setter
    private ConcurrentHashMap<String, SummaryDeptPerson> summaryDeptPersonHashMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void getThreadPool() {
        this.initContext();
    }

    /**
     * @Description 启动数据拉取任务
     */
    @Scheduled(cron = "0/10 * * * * ?")
    private void startPullDataJob() {
        pullDataJob.execute();
    }

    /**
     * @Description 初始化统计的相关上下文
     */
    public void initContext() {
        this.initDeptMainID();
        this.initBedRecordTable();
        this.initDeptHierarchy();
        this.initDatasource2Dept();
        this.initBedMap();
        this.initDriverMap();
        this.initDeptLeaveIDMap();
        this.initActiveBedMap();
        this.initDeptPrefix2DeptId();
        this.initSummaryBedHashMap();
        this.initSummaryPersonHashMap();
    }

    /**
     * @param
     * @return
     * @Description 初始化 数据源id与部门id 之间的映射关系
     * @date 2019/5/16
     * @auther zvbb
     */
    private void initDatasource2Dept() {
        DataSourceDynamicSqlSupport.DataSource dataSourceSupport = DataSourceDynamicSqlSupport.dataSource;
        List<DataSource> dataSourceList = dataSourceMapper.selectByExample()
                .where(dataSourceSupport.status, IsEqualTo.of(Status.ENABLE::getCode))
                .build().execute();
        if (!CollectionUtils.isEmpty(dataSourceList)) {
            dataSourceList.forEach(dataSource -> {
                datasource2Dept.put(dataSource.getId(), dataSource.getDeptId());
                dept2Datasource.put(dataSource.getDeptId(), dataSource.getId());
            });
        }
    }

    /**
     * @param
     * @return
     * @Description 初始化床铺信息
     * @date 2019/6/12
     * @auther zvbb
     */
    private void initBedMap() {
        List<BedInfo> bedInfoList = bedInfoMapper.selectByExample().build().execute();
        if (!CollectionUtils.isEmpty(bedInfoList)) {
            final Long[] maxId = {0L};
            bedInfoList.forEach(bedInfo -> {
                Long deptId = bedInfo.getDeptId();
                deptBedMap.computeIfAbsent(deptId, k -> new ConcurrentHashMap<>()).put(bedInfo.getSerialno(), bedInfo.getId());
                do {
                    AtomicLong atomicLong = deptBedCnt.computeIfAbsent(deptId, k -> new AtomicLong(0));
                    atomicLong.addAndGet(1);
                    deptId = this.getParentDeptId(deptId);
                } while (!Objects.isNull(deptId));
            });
        }
    }

    /**
     * @param
     * @return
     * @Description 初始化司机信息
     * @date 2019/6/12
     * @auther zvbb
     */
    private void initDriverMap() {
        List<Driver> driverList = driverMapper.selectByExample().build().execute();
        if (!CollectionUtils.isEmpty(driverList)) {
            driverList.forEach(driver -> {
                if (Objects.isNull(driver.getDeptId())) {
                    return;
                }
                driverMap.put(driver.getDriverNo(), driver.getDeptId());
                Long deptId = driver.getDeptId();
                do {
                    AtomicLong atomicLong = deptDriverCnt.computeIfAbsent(deptId, k -> new AtomicLong(0));
                    atomicLong.addAndGet(1);
                    deptId = this.getParentDeptId(deptId);
                } while (!Objects.isNull(deptId));
            });
        }
    }

    private void initDeptPrefix2DeptId() {
        List<Dept> deptList = deptMapper.selectByExample().build().execute();
        if (!CollectionUtils.isEmpty(deptList)) {
            deptList.forEach(dept -> {
                if (!Objects.isNull(dept.getNo())) {
                    deptPrefix2DeptId.put(dept.getNo(), dept.getId());
                }
            });
        }
    }

    public Long getDeptId(Long dataSourceId) {
        return datasource2Dept.get(dataSourceId);
    }

    /**
     * @param serialNo 床垫的硬件唯一编号
     * @param deptId   所属部门id
     * @return
     * @Description 判断是否为新的床铺，如果是，添加到map中
     * @date 2019/6/12
     * @auther zvbb
     */
    public boolean isNewBed(String serialNo, Long deptId) {
        if (Objects.isNull(deptBedMap.get(deptId))
                || Objects.isNull(deptBedMap.get(deptId).get(serialNo))) {
            this.addNewBed(serialNo, deptId);
            return true;
        }
        return false;
    }

    public List<BedActive> isNewActiveBed(Long deptID, Date date, String serialno) {
        List<BedActive> addActiveList = new ArrayList<>();
        //月活跃床铺
        String monthKey = SummaryRedisUtil.activeBedKey(SumType.MONTH, deptID, date, serialno);
        if (Objects.isNull(activeBedMap.get(monthKey))) {
            this.addNewActiveBed(SumType.MONTH, deptID, date, serialno);
            BedActive bedActive = new BedActive();
            //司机所在部门
            bedActive.setDeptId(deptID);
            Date monthFirstDay = DateUtils.getMonthFirstDay(date);
            bedActive.setDate(monthFirstDay);
            bedActive.setSerialNo(serialno);
            bedActive.setType(SumType.MONTH.getCode());
            addActiveList.add(bedActive);
        }

        //天活跃床铺
        String dayKey = SummaryRedisUtil.activeBedKey(SumType.DAY, deptID, date, serialno);
        if (Objects.isNull(activeBedMap.get(dayKey))) {
            this.addNewActiveBed(SumType.DAY, deptID, date, serialno);
            BedActive bedActive = new BedActive();
            //司机所在部门
            bedActive.setDeptId(deptID);
            Date dayZeroHour = DateUtils.getDayZeroHour(date);
            bedActive.setDate(dayZeroHour);
            bedActive.setSerialNo(serialno);
            bedActive.setType(SumType.DAY.getCode());
            addActiveList.add(bedActive);
        }
        return addActiveList;
    }

    /**
     * @param serialNo 床垫的硬件唯一编号
     * @param deptId   所属部门id
     * @return
     * @Description 添加新的床铺到bedMap中
     * @date 2019/6/12
     * @auther zvbb
     */
    private void addNewBed(String serialNo, Long deptId) {
        deptBedMap.computeIfAbsent(deptId, k -> new ConcurrentHashMap<>()).put(serialNo, deptId);
        do {
            AtomicLong atomicLong = deptBedCnt.computeIfAbsent(deptId, k -> new AtomicLong(0));
            atomicLong.addAndGet(1);
            deptId = this.getParentDeptId(deptId);
        } while (!Objects.isNull(deptId));
    }

    /**
     * @param
     * @return
     * @Description 添加新的床铺到activeBedMap中
     * @date 2019/6/16
     * @auther zvbb
     */
    private void addNewActiveBed(SumType sumType, Long deptID, Date date, String serialno) {
        String key = SummaryRedisUtil.activeBedKey(sumType, deptID, date, serialno);
        activeBedMap.put(key, deptID);
        do {
            String deptAcitveKey = SummaryRedisUtil.deptActiveBedSumKey(sumType, deptID, date);
            AtomicLong atomicLong = deptActiveBedCnt.computeIfAbsent(deptAcitveKey, k -> new AtomicLong(0));
            atomicLong.addAndGet(1);
            deptID = this.getParentDeptId(deptID);
        } while (!Objects.isNull(deptID));
    }

    /**
     * @param driverNo      司机编号
     * @param defaultDeptId 默认的司机部门
     *                      因为通过"司机编号前缀"与"部门的编号"比较,可能存在匹配不上的状况,
     *                      这里使用数据源的部门id，做异常处理
     * @return
     * @Description 判断是否为新的司机, 如果是，添加到map中
     * @date 2019/6/12
     * @auther zvbb
     */
    public boolean isNewDriver(String driverNo, Long defaultDeptId) {
        if (Objects.isNull(driverMap.get(driverNo))) {
            this.addNewDriver(driverNo, defaultDeptId);
            return true;
        }
        return false;
    }

    /**
     * @param driverNo 司机编号
     * @return
     * @Description 添加新的司机到driverMap
     * @date 2019/6/12
     * @auther zvbb
     */
    private void addNewDriver(String driverNo, Long defaultDeptId) {
        Set<Map.Entry<String, Long>> entries = deptPrefix2DeptId.entrySet();
        for (Map.Entry<String, Long> entry : entries) {
            String deptNo = entry.getKey();
            if (driverNo.toLowerCase().startsWith(deptNo.toLowerCase())) {
                Long deptId = entry.getValue();
                driverMap.put(driverNo, deptId);
                break;
            }
        }

        if (Objects.isNull(driverMap.get(driverNo))) {
            driverMap.put(driverNo, defaultDeptId);
        }

        Long deptId = driverMap.get(driverNo);
        do {
            AtomicLong atomicLong = deptDriverCnt.computeIfAbsent(deptId, k -> new AtomicLong(0));
            atomicLong.addAndGet(1);
            deptId = this.getParentDeptId(deptId);
        } while (!Objects.isNull(deptId));
        return;
    }

    /**
     * @param driverNo 司机编号
     * @return
     * @Description 获取司机所在 部门
     * @date 2019/6/12
     * @auther zvbb
     */
    public Long getDriverDeptId(String driverNo) {
        return driverMap.get(driverNo);
    }

    /**
     * @param
     * @return
     * @Description 获取该部门的 床铺总数
     * @date 2019/6/12
     * @auther zvbb
     */
    public Long getDeptBedCnt(Long deptID) {
        return deptBedCnt.get(deptID) == null ? 0 : deptBedCnt.get(deptID).get();
    }

    public Long getDeptActiveBedCnt(SumType sumType, Long deptID, Date date) {
        String deptAcitveKey = SummaryRedisUtil.deptActiveBedSumKey(sumType, deptID, date);
        return deptActiveBedCnt.get(deptAcitveKey) == null ? 0 : deptActiveBedCnt.get(deptAcitveKey).get();
    }

    /**
     * @param
     * @return
     * @Description 获取该部门的 司机总数
     * @date 2019/6/12
     * @auther zvbb
     */
    public Long getDeptDriverCnt(Long deptID) {
        return deptDriverCnt.get(deptID) == null ? 0L : deptDriverCnt.get(deptID).get();
    }

    private void initDeptMainID() {
        DataSourceDynamicSqlSupport.DataSource dataSource = DataSourceDynamicSqlSupport.dataSource;
        List<DataSource> dataSourceList = dataSourceMapper.selectByExample()
                .where(dataSource.status, IsEqualTo.of(Status.ENABLE::getCode))
                .build()
                .execute();
        if (!CollectionUtils.isEmpty(dataSourceList)) {
            //当前有哪些部门有数据源
            for (int i = 0; i < dataSourceList.size(); i++) {
                Long deptId = dataSourceList.get(i).getDeptId();
                if (Objects.nonNull(deptId)) {
                    //获取每个部门，程序停止之前统计到了那条记录
                    List<CustomSQL.StatusSection> statusSectionList = customSQL.getUpDealStatus(deptId);
                    if (!CollectionUtils.isEmpty(statusSectionList)) {
                        CustomSQL.StatusSection record = statusSectionList.get(0);
                        if (Objects.nonNull(record.getDeptId()) && Objects.nonNull(record.getMainId())) {
                            //上次统计到了那个MainId
                            deptMainIDMap.put(record.getDeptId(), record.getMainId());
                            //上次统计Finish!=2的记录有哪些
                            if (Objects.nonNull(record.getUnFinishRecords()) && !record.getUnFinishRecords().equals("")) {
                                List<String> unFinishList = Arrays.asList(record.getUnFinishRecords().split(","));
                                List<Integer> unFinishMainIDList = new ArrayList<>();
                                for (int j = 0; j < unFinishList.size(); j++) {
                                    unFinishMainIDList.add(Integer.parseInt(unFinishList.get(j)));
                                }
                                deptUnFinishRecordsMap.put(record.getDeptId(), unFinishMainIDList);
                            }
                        }
                    }
                }
            }
        }
    }

    private void initBedRecordTable() {
        List<BedRecord> bedRecordList = bedRecordMapper.selectByExample().build().execute();
        List<Long> deptidList = bedRecordList.stream()
                .map(bedRecord -> bedRecord.getDeptId())
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deptidList)) {
            BedRecordDynamicSqlSupport.BedRecord bedRecordSupport = BedRecordDynamicSqlSupport.bedRecord;
            for (int i = 0; i < deptidList.size(); i++) {
                Long deptid = deptidList.get(i);
                Integer mainid = deptMainIDMap.get(deptid);
                if (Objects.isNull(mainid)) {
                    mainid = 0;
                }
                Integer finalMainid = mainid;
                bedRecordMapper.deleteByExample()
                        .where(bedRecordSupport.deptId, IsEqualTo.of(() -> deptid))
                        .and(bedRecordSupport.mainid, IsGreaterThan.of(() -> finalMainid))
                        .build()
                        .execute();
            }
        }
    }

    private void initDeptLeaveIDMap() {
        List<CustomSQL.LeaveSection> leaveSectionList = customSQL.getMaxDeptInfoLeaveID();
        leaveSectionList.forEach(record -> {
            deptLeaveIDMap.put(record.getDeptId(), record.getID());
        });
    }

    private void initActiveBedMap() {
        List<BedActive> bedActiveList = bedActiveMapper.selectByExample().build().execute();
        bedActiveList.forEach(record -> {
            Long deptId = record.getDeptId();
            Date date = record.getDate();
            Integer type = record.getType();
            String serialno = record.getSerialNo();
            String key = "";
            SumType sumType = null;
            if (SumType.MONTH.getCode().equals(type)) {
                sumType = SumType.MONTH;
                key = SummaryRedisUtil.activeBedKey(sumType, deptId, date, serialno);
            } else if (SumType.DAY.getCode().equals(type)) {
                sumType = SumType.DAY;
                key = SummaryRedisUtil.activeBedKey(sumType, deptId, date, serialno);
            }
            if (Objects.isNull(activeBedMap.get(key)) && !key.equals("")) {
                activeBedMap.put(key, deptId);
                do {
                    String deptAcitveKey = SummaryRedisUtil.deptActiveBedSumKey(sumType, deptId, date);
                    AtomicLong atomicLong = deptActiveBedCnt.computeIfAbsent(deptAcitveKey, (K) -> new AtomicLong(0));
                    atomicLong.addAndGet(1);
                    deptId = this.getParentDeptId(deptId);
                } while (!Objects.isNull(deptId));
            }
        });
    }

    /**
     * @param
     * @return
     * @Description deptHierarchyMap
     * 作用: 记录部门的层次关系
     * @date 2019/5/16
     * @auther zvbb
     */
    private void initDeptHierarchy() {
        List<Dept> depts = deptMapper.selectByExample().build().execute();
        if (!CollectionUtils.isEmpty(depts)) {
            depts.forEach(dept -> {
                deptHierarchyMap.put(dept.getId(), dept.getSuperiorId());
            });
        }
    }

    /**
     * @param
     * @return
     * @Description 返回上级部门
     * @date 2019/6/12
     * @auther zvbb
     */
    public Long getParentDeptId(Long deptdId) {
        return deptHierarchyMap.get(deptdId);
    }

    /**
     * @param
     * @return
     * @Description 获取当前，info_inteface表已经更新到了那条记录
     * @date 2019/6/13
     * @auther zvbb
     */
    public Integer getMaxMainId(Long deptID) {
        return deptMainIDMap.get(deptID);
    }

    /**
     * @Description 获取上次统计时，info_inteface表中Finish!=2的记录
     */
    public List<Integer> getUnFinishRecords(Long deptID) {
        return deptUnFinishRecordsMap.get(deptID);
    }

    /**
     * @param
     * @return
     * @Description 获取当前，info_leave表已经更新到了那条记录
     * @date 2019/6/13
     * @auther zvbb
     */
    public Integer getMaxLeaveID(Long deptID) {
        return deptLeaveIDMap.get(deptID);
    }

    public void setMaxLeaveID(Long deptId, Integer maxId) {
        deptLeaveIDMap.put(deptId, maxId);
    }

    /**
     * @param
     * @return
     * @Description 更新deptMainIDMap
     * @date 2019/6/13
     * @auther zvbb
     */
    public void setMaxMainId(Long deptId, Integer maxMainId) {
        deptMainIDMap.put(deptId, maxMainId);
    }

    public void setUnFinishRecord(Long deptId, List<Integer> unFinshRecords) {
        deptUnFinishRecordsMap.put(deptId, unFinshRecords);
    }


    /**
     * @param
     * @return
     * @Description 初始化存放团体统计(以床铺的角度看待睡眠信息)的HashMap
     * @date 2019/5/17
     * @auther zvbb
     */
    private void initSummaryBedHashMap() {
        List<SummaryDeptBed> summaryDeptBedList = summaryDeptBedMapper.selectByExample().build().execute();
        if (!CollectionUtils.isEmpty(summaryDeptBedList)) {
            summaryDeptBedList.stream().forEach(summaryDeptBed -> {
                Long deptid = summaryDeptBed.getDeptid();
                Date date = summaryDeptBed.getDate();
                //统计类型
                Integer sumtype = summaryDeptBed.getSumtype();
                //KEY
                String deptBedSummaryKey = "";
                if (sumtype.equals(SumType.MONTH.getCode())) {
                    deptBedSummaryKey = SummaryRedisUtil.getDeptBedSummaryKey(deptid, date, SumType.MONTH);
                } else if (sumtype.equals(SumType.DAY.getCode())) {
                    deptBedSummaryKey = SummaryRedisUtil.getDeptBedSummaryKey(deptid, date, SumType.DAY);
                }
                summaryDeptBedHashMap.put(deptBedSummaryKey, summaryDeptBed);
            });
        }
    }

    /**
     * @param
     * @return
     * @Description 初始化存放团体统计(以司机的角度看待睡眠信息)的HashMap
     * @date 2019/5/17
     * @auther zvbb
     */
    private void initSummaryPersonHashMap() {
        List<SummaryDeptPerson> summaryDeptPersonList = summaryDeptPersonMapper.selectByExample().build().execute();
        if (!CollectionUtils.isEmpty(summaryDeptPersonList)) {
            summaryDeptPersonList.stream().forEach(summaryDeptPerson -> {
                Long deptid = summaryDeptPerson.getDeptid();
                Date date = summaryDeptPerson.getDate();
                //统计类型
                Integer sumtype = summaryDeptPerson.getSumtype();
                //KEY
                String deptPersonSummaryKey = "";
                if (sumtype.equals(SumType.MONTH.getCode())) {
                    deptPersonSummaryKey = SummaryRedisUtil.getDeptPersonSummaryKey(deptid, date, SumType.MONTH);
                } else if (sumtype.equals(SumType.DAY.getCode())) {
                    deptPersonSummaryKey = SummaryRedisUtil.getDeptPersonSummaryKey(deptid, date, SumType.DAY);
                }
                summaryDeptPersonHashMap.put(deptPersonSummaryKey, summaryDeptPerson);
            });
        }
    }
}

