package com.jiangkai.framework.admin.service;

import com.jiangkai.framework.admin.bean.RealBedInfoRes;
import com.jiangkai.framework.admin.bean.RealBedRes;
import com.jiangkai.framework.admin.bean.RealMonitorRes;
import com.jiangkai.framework.admin.bean.RealSleepStatusRatioRes;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.common.enums.InfoIntefaceFinish;
import com.jiangkai.framework.admin.common.enums.SleepStatus;
import com.jiangkai.framework.admin.datasource.CustomDataSource;
import com.jiangkai.framework.admin.datasource.DataSourceHelper;
import com.jiangkai.framework.admin.timetask.JobManager;
import com.jiangkai.framework.admin.util.FormatUtils;
import com.jiangkai.framework.extdata.mapper.InfoDetailDynamicSqlSupport;
import com.jiangkai.framework.extdata.mapper.InfoDetailMapper;
import com.jiangkai.framework.extdata.mapper.InfoIntefaceDynamicSqlSupport;
import com.jiangkai.framework.extdata.mapper.InfoIntefaceMapper;
import com.jiangkai.framework.extdata.model.InfoDetail;
import com.jiangkai.framework.extdata.model.InfoInteface;
import com.jiangkai.framework.source.mapper.BedInfoDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.BedInfoMapper;
import com.jiangkai.framework.source.mapper.BedRecordDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.BedRecordMapper;
import com.jiangkai.framework.source.model.BedInfo;
import com.jiangkai.framework.source.model.BedRecord;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.render.DefaultSelectStatementProvider;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsIn;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualTo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.jiangkai.framework.extdata.mapper.InfoIntefaceDynamicSqlSupport.mainid;

/**
 * User: zvbb
 * Date: 2019/6/18
 * Description:
 */
@Component
@RequiredArgsConstructor
public class RealTimeBedInfoService {
    private final BedInfoMapper bedInfoMapper;
    private final JobManager jobManager;
    private final InfoIntefaceMapper infoIntefaceMapper;
    private final BedRecordMapper bedRecordMapper;

    /**
     * @param deptID  部门id
     * @param buiding 楼号
     * @return
     * @Description 返回指定部门，指定楼号的床铺信息
     * @date 2019/6/18
     * @auther zvbb
     */
    public Result beds(Long deptID, String buiding) {
        if (Objects.isNull(deptID)) {
            return Result.businessError("参数错误");
        }
        //1.获取当前部门的所有楼号
        List<String> deptBuildList = getDeptBuildList(deptID);
        if (CollectionUtils.isEmpty(deptBuildList)) {
            return Result.success();
        }
        //2.查询指定楼号的床铺数据
        BedInfoDynamicSqlSupport.BedInfo bedInfo = BedInfoDynamicSqlSupport.bedInfo;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedInfo>>>.QueryExpressionWhereBuilder builder
                = bedInfoMapper.selectByExample().where(bedInfo.deptId, IsEqualTo.of(() -> deptID));
        if (!Objects.isNull(buiding) && !buiding.equals("")) {
            builder.and(bedInfo.restBuilding, IsEqualTo.of(() -> buiding));
        } else {
            if (Objects.nonNull(deptBuildList)) {
                //默认查询 楼号靠前的
                builder.and(bedInfo.restBuilding, IsEqualTo.of(() -> deptBuildList.get(0)));
            } else {
                //没有楼号
                return Result.success();
            }
        }
        //2.1 查询
        List<BedInfo> bedInfoList = builder
                .orderBy(bedInfo.restRoom)
                .build()
                .execute();

        //2.2 没有床铺
        if (CollectionUtils.isEmpty(bedInfoList)) {
            return Result.success();
        }

        //2.3 构建serialnoList,存放所有需要查询的所有床铺
        List<String> serialnoList = new ArrayList<>();
        for (int i = 0; i < bedInfoList.size(); i++) {
            BedInfo record = bedInfoList.get(i);
            String serialno = record.getSerialno();
            serialnoList.add(serialno);
        }

        //3. 获取床铺的状态
        //3.1获取数据源
        Long datasourceID = jobManager.getDept2Datasource().get(deptID);
        CustomDataSource dataSource = DataSourceHelper.getDataSource(datasourceID);

        //获取当前床铺的使用状况
        AssistClass assistClass = getBedStatus(dataSource, serialnoList);
        //床铺3秒一次的心跳
        List<InfoDetail> infoDetailList = assistClass.getInfoDetailList();
        //床铺的使用记录
        List<InfoInteface> infoIntefaceList = assistClass.getInfoIntefaceList();

        HashMap<String, Object> resultMap = new HashMap<>();
        List<RealBedInfoRes> bedStutasList = new ArrayList<>();
        RealBedInfoRes bedsNode = new RealBedInfoRes();
        bedStutasList.add(bedsNode);
        for (int i = 0; i < bedInfoList.size(); i++) {
            if (bedsNode.getBeds().size() == 6) {
                bedsNode = new RealBedInfoRes();
                bedStutasList.add(bedsNode);
            }
            BedInfo bed = bedInfoList.get(i);
            //存放bed信息的最小单元
            RealBedInfoRes.BedStatusNode node = new RealBedInfoRes.BedStatusNode();
            //房间号
            String restRoom = bed.getRestRoom();
            //新的房间号,需要放到新的bedsNode中
            if ((!bedsNode.getRestRoom().equals("")) && !bedsNode.getRestRoom().equals(restRoom)) {
                bedsNode = new RealBedInfoRes();
                bedStutasList.add(bedsNode);
            }
            bedsNode.setRestRoom(restRoom);

            //床铺的唯一序列号
            String serialno = bed.getSerialno();
            node.setSerialNo(serialno);
            //InfoInteface表的主键
            Integer mainID = null;
            for (int j = 0; j < infoIntefaceList.size(); j++) {
                InfoInteface infoInteface = infoIntefaceList.get(j);
                if (infoInteface.getBedSerialno().equals(serialno)) {
                    //司机名称
                    String driverName = infoInteface.getDriverName();
                    node.setSleepDriverName(driverName);
                    //司机编号
                    String driverWorkno = infoInteface.getDriverWorkno();
                    node.setSleepDriverNo(driverWorkno);

                    mainID = infoInteface.getMainid();
                    node.setMainID(mainID);
                    infoIntefaceList.remove(j);
                    break;
                }
            }
            if (!Objects.isNull(mainID)) {
                for (int j = 0; j < infoDetailList.size(); j++) {
                    InfoDetail infoDetail = infoDetailList.get(j);
                    Integer interfaceMainid = infoDetail.getInterfaceMainid();
                    if (interfaceMainid.intValue() == mainID.intValue()) {
                        //睡眠状态
                        String sleepstatus = infoDetail.getSleepstatus();
                        switch (sleepstatus) {
                            case "引睡":
                                sleepstatus = "清醒";
                                break;
                            case "离床":
                            case "脱机":
                                sleepstatus = "空床";
                                break;
                        }
                        //更新时间
                        Date updatetime = infoDetail.getUpdatetime();
                        //最后一条数据超过10秒
                        if (((new Date().getTime()) - updatetime.getTime()) > 10 * 1000) {
                            sleepstatus = "空床";
                        }
                        node.setStatus(sleepstatus);
                        infoDetailList.remove(j);
                        break;
                    }
                }
            }
            List<RealBedInfoRes.BedStatusNode> beds = bedsNode.getBeds();
            beds.add(node);
        }
        resultMap.put("bedStutasList", bedStutasList);
        resultMap.put("resetBuilds", deptBuildList);
        return Result.success(resultMap);
    }

    /**
     * @param
     * @return
     * @Description 床位信息
     * @date 2019/6/19
     * @auther zvbb
     */
    public Result bedInfo(Integer mainID, Long deptId) {
        if (Objects.isNull(mainID)) {
            return Result.paramError("mainID参数错误!");
        }
        if (Objects.isNull(deptId)) {
            return Result.paramError("deptId参数错误!");
        }

        //1.获取数据源
        Long datasourceID = jobManager.getDept2Datasource().get(deptId);
        CustomDataSource dataSource = DataSourceHelper.getDataSource(datasourceID);
        //2.查询Info_Inteface表
        List<InfoInteface> infoIntefaceList = dataSource.findMapperDoMethod(InfoIntefaceMapper.class, infoIntefaceMapper -> {
            InfoIntefaceDynamicSqlSupport.InfoInteface infoInteface = InfoIntefaceDynamicSqlSupport.infoInteface;
            //这里取出的数据，就是正在使用的床铺
            QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoInteface>>>.QueryExpressionWhereBuilder builder = infoIntefaceMapper.selectByExample()
                    .where(infoInteface.mainid, IsEqualTo.of(() -> (mainID)));
            return builder.build().execute();
        });

        RealBedRes realBedRes = new RealBedRes();
        if (!CollectionUtils.isEmpty(infoIntefaceList)) {
            InfoInteface record = infoIntefaceList.get(0);
            //床铺号
            String bedSerialno = record.getBedSerialno();
            realBedRes.setBedSerialNo(bedSerialno);
            //工号
            String driverWorkno = record.getDriverWorkno();
            realBedRes.setDriverNo(driverWorkno);
            //当前使用者
            String driverName = record.getDriverName();
            realBedRes.setDriverName(driverName);

            //入住时间
            Date checkindate = record.getCheckindate();
            Date checkoutdate = record.getCheckoutdate();
            if (Objects.isNull(checkoutdate)) {
                checkoutdate = new Date();
            }
            //睡眠时长
            BigDecimal sleepTime = BigDecimal.valueOf(checkoutdate.getTime() - checkindate.getTime()).divide(BigDecimal.valueOf(60 * 1000), 2, RoundingMode.HALF_UP);
            realBedRes.setSleepTime(sleepTime);
            //3.查询info_detail表
            List<InfoDetail> infoDetailList = getLastInfoDetail(dataSource, mainID);
            if (!CollectionUtils.isEmpty(infoDetailList)) {
                InfoDetail row = infoDetailList.get(0);
                //心跳
                Integer heartbeat = row.getHeartbeat();
                realBedRes.setHeartBeat(heartbeat);
                //呼吸
                Integer breath = row.getBreath();
                realBedRes.setBreath(breath);
            }
            return Result.success(realBedRes);
        }

        return Result.success(new RealBedRes());
    }

    /**
     * @param
     * @return
     * @Description 实时监测
     * @date 2019/6/19
     * @auther zvbb
     */
    public Result realMonitor(Integer mainID, Long deptId) {
        if (Objects.isNull(mainid)) {
            return Result.paramError("mainID参数错误");
        }
        if (Objects.isNull(deptId)) {
            return Result.paramError("deptId参数错误");
        }
        //1.获取数据源
        Long datasourceID = jobManager.getDept2Datasource().get(deptId);
        CustomDataSource dataSource = DataSourceHelper.getDataSource(datasourceID);
        //2.查询Info_Inteface表
        List<InfoInteface> infoIntefaceList = dataSource.findMapperDoMethod(InfoIntefaceMapper.class, infoIntefaceMapper -> {
            InfoIntefaceDynamicSqlSupport.InfoInteface infoInteface = InfoIntefaceDynamicSqlSupport.infoInteface;
            //这里取出的数据，就是正在使用的床铺
            QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoInteface>>>.QueryExpressionWhereBuilder builder = infoIntefaceMapper.selectByExample()
                    .where(infoInteface.mainid, IsEqualTo.of(() -> (mainID)));
            return builder.build().execute();
        });

        if (!CollectionUtils.isEmpty(infoIntefaceList)) {
            InfoInteface record = infoIntefaceList.get(0);
            //3.查询info_detail表
            List<InfoDetail> infoDetailList = getInfoDetailList(dataSource, mainID);
            RealMonitorRes realMonitorRes = new RealMonitorRes();
            Integer sumHeartBeat = 0;
            Integer sumBreath = 0;
            int min = Math.min(15, infoDetailList.size());
            for (int i = 0; i < min; i++) {
                InfoDetail row = infoDetailList.get(i);
                //心跳
                Integer heartbeat = row.getHeartbeat();
                sumHeartBeat += heartbeat;
                //呼吸
                Integer breath = row.getBreath();
                sumBreath += breath;
                //睡眠状态
                String sleepstatus = row.getSleepstatus();
                switch (sleepstatus) {
                    case "引睡":
                        sleepstatus = "清醒";
                        break;
                }
                realMonitorRes.getSleepStatus().add(sleepstatus);
            }
            //平均心跳
            realMonitorRes.setAvgHeartBeat(BigDecimal.valueOf(sumHeartBeat).divide(BigDecimal.valueOf(min), 2, RoundingMode.HALF_UP));
            //平均呼吸
            realMonitorRes.setAvgBreath(BigDecimal.valueOf(sumBreath).divide(BigDecimal.valueOf(min), 2, RoundingMode.HALF_UP));
            return Result.success(realMonitorRes);
        }
        return Result.success(new RealMonitorRes());
    }

    /**
     * @Description 睡眠状态占比
     */
    public Result sleepStatusRatio(Integer mainID, Long deptId) {
        if (Objects.isNull(mainid)) {
            return Result.paramError("mainID参数错误");
        }
        if (Objects.isNull(deptId)) {
            return Result.paramError("deptId参数错误");
        }
        //1.获取数据源
        Long datasourceID = jobManager.getDept2Datasource().get(deptId);
        CustomDataSource dataSource = DataSourceHelper.getDataSource(datasourceID);
        //2.查询Info_Inteface表
        List<InfoInteface> infoIntefaceList = dataSource.findMapperDoMethod(InfoIntefaceMapper.class, infoIntefaceMapper -> {
            InfoIntefaceDynamicSqlSupport.InfoInteface infoInteface = InfoIntefaceDynamicSqlSupport.infoInteface;
            //这里取出的数据，就是正在使用的床铺
            QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoInteface>>>.QueryExpressionWhereBuilder builder = infoIntefaceMapper.selectByExample()
                    .where(infoInteface.mainid, IsEqualTo.of(() -> (mainID)));
            return builder.build().execute();
        });

        if (!CollectionUtils.isEmpty(infoIntefaceList)) {
            InfoInteface record = infoIntefaceList.get(0);

            //info_detail表中获取数据
            List<InfoDetail> infoDetailList = getInfoDetailList(dataSource, mainID);

            RealSleepStatusRatioRes realSleepStatusRatioRes = new RealSleepStatusRatioRes();
            Long sumShen = 0L;
            Long sumQian = 0L;
            Long sumQin = 0L;
            for (int i = 0; i < infoDetailList.size(); i++) {
                InfoDetail row = infoDetailList.get(i);
                //睡眠状态
                String sleepstatus = row.getSleepstatus();
                switch (sleepstatus) {
                    case "引睡":
                        sumQin += 5;
                        break;
                    case "深睡":
                        sumShen += 5;
                        break;
                    case "浅睡":
                        sumQian += 5;
                        break;
                }
            }
            //深睡+浅睡
            Long sleepTotal = sumShen + sumQian;
            if (sleepTotal != 0) {
                RealSleepStatusRatioRes.Ring1 ring1 = realSleepStatusRatioRes.getRing1();
                //深睡占比
                ring1.setShenRatio(BigDecimal.valueOf(sumShen).divide(BigDecimal.valueOf(sleepTotal), 4, RoundingMode.HALF_UP));
                if (sleepTotal != 0) {
                    //浅睡占比
                    ring1.setQianRatio(BigDecimal.valueOf(1).subtract(ring1.getShenRatio()));
                } else {
                    ring1.setQianRatio(BigDecimal.ZERO);
                }
                //浅睡时长 + 深睡时长
                ring1.setSleepTotal(FormatUtils.secondTime2HMS(sleepTotal));
            }
            Long totalSleep = sumShen + sumQian + sumQin;
            if (totalSleep != 0) {
                RealSleepStatusRatioRes.Ring2 ring2 = realSleepStatusRatioRes.getRing2();
                //(浅睡时长 + 深睡时长)/(深睡、浅睡、引睡、清醒)
                ring2.setSleepRatio(BigDecimal.valueOf(sleepTotal).divide(BigDecimal.valueOf(totalSleep), 4, RoundingMode.HALF_UP));
                //(深睡、浅睡、引睡、清醒) - (浅睡时长 + 深睡时长)/(深睡、浅睡、引睡、清醒)
                if (totalSleep != 0) {
                    ring2.setUnSleepRatio(BigDecimal.valueOf(1).subtract(ring2.getSleepRatio()));
                } else {
                    ring2.setUnSleepRatio(BigDecimal.ZERO);
                }
                ring2.setTotalTime(FormatUtils.secondTime2HMS(totalSleep));
            }

            return Result.success(realSleepStatusRatioRes);
        }

        return Result.success(new RealSleepStatusRatioRes());
    }

    /**
     * @param
     * @return
     * @Description 返回该部门有几个楼号
     * @date 2019/6/18
     * @auther zvbb
     */
    private List<String> getDeptBuildList(Long deptID) {
        List<String> restBuildList = new ArrayList<>();
        BedInfoDynamicSqlSupport.BedInfo bedInfo = BedInfoDynamicSqlSupport.bedInfo;

        DefaultSelectStatementProvider.Builder builder = new DefaultSelectStatementProvider.Builder();
        builder.withSelectStatement("select Rest_building from bed_info where dept_id=" + deptID + " group by Rest_building order by Rest_building asc");
        DefaultSelectStatementProvider defaultSelectStatementProvider = builder.build();
        List<BedInfo> bedInfoList = bedInfoMapper.selectMany(defaultSelectStatementProvider);
        for (int i = 0; i < bedInfoList.size(); i++) {
            BedInfo record = bedInfoList.get(i);
            restBuildList.add(record.getRestBuilding());
        }

        return restBuildList;
    }

    /**
     * @param customDataSource 数据源
     * @param serialnoList     床铺的查询范围
     * @return List<InfoInteface> 获取当前正在使用的床铺
     * @Description
     * @date 2019/6/18
     * @auther zvbb
     */
    private static AssistClass getBedStatus(CustomDataSource customDataSource, List<String> serialnoList) {
        //获取当前 司机 还没有离开的 睡眠记录
        List<InfoInteface> infoIntefaceList = customDataSource.findMapperDoMethod(InfoIntefaceMapper.class, infoIntefaceMapper -> {
            InfoIntefaceDynamicSqlSupport.InfoInteface infoInteface = InfoIntefaceDynamicSqlSupport.infoInteface;
            //这里取出的数据，就是正在使用的床铺,finish!=2
            QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoInteface>>>.QueryExpressionWhereBuilder builder = infoIntefaceMapper.selectByExample()
                    .where(infoInteface.bedSerialno, IsIn.of(serialnoList))
                    .and(infoInteface.finish, IsNotEqualTo.of(InfoIntefaceFinish.FINISH_RECORD::getCode));
            return builder.build().execute();
        });

        List<String> mainidList = new ArrayList<>();
        for (int i = 0; i < infoIntefaceList.size(); i++) {
            InfoInteface record = infoIntefaceList.get(i);
            String mainid = record.getMainid() + "";
            mainidList.add(mainid);
        }

        List<InfoDetail> infoDetailList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(mainidList)) {
            infoDetailList = customDataSource.findMapperDoMethod(InfoDetailMapper.class, infoDetailMapper -> {
                InfoDetailDynamicSqlSupport.InfoDetail infoDetail = InfoDetailDynamicSqlSupport.infoDetail;
                //获取床铺最后一条更新数据
                DefaultSelectStatementProvider statementProvider = new DefaultSelectStatementProvider
                        .Builder()
                        .withSelectStatement(getBedDetailLastRecordSQL(mainidList))
                        .build();
                List<InfoDetail> detailList = infoDetailMapper.selectMany(statementProvider);
                return detailList;
            });
        }
        AssistClass assistClass = new AssistClass();
        assistClass.setInfoDetailList(infoDetailList);
        assistClass.setInfoIntefaceList(infoIntefaceList);
        return assistClass;
    }

    /**
     * @param
     * @return
     * @Description 查询 info_detail表中，指定mainID的最新一条记录
     * @date 2019/6/19
     * @auther zvbb
     */
    private static List<InfoDetail> getLastInfoDetail(CustomDataSource customDataSource, Integer mainID) {
        List<InfoDetail> infoDetailList = customDataSource.findMapperDoMethod(InfoDetailMapper.class, infoDetailMapper -> {
            //获取床铺最后一条更新数据
            DefaultSelectStatementProvider statementProvider = new DefaultSelectStatementProvider.Builder().withSelectStatement("select top 1 * from info_detail where Interface_MainID=" + mainID + "ORDER BY UpDatetime DESC")
                    .build();
            List<InfoDetail> detailList = infoDetailMapper.selectMany(statementProvider);

            return detailList;
        });

        return infoDetailList;
    }

    /**
     * @param
     * @return
     * @Description 查询 info_detail表中，指定mainID的所有记录
     * @date 2019/6/19
     * @auther zvbb
     */
    private static List<InfoDetail> getInfoDetailList(CustomDataSource customDataSource, Integer mainID) {
        List<InfoDetail> infoDetailList = customDataSource.findMapperDoMethod(InfoDetailMapper.class, infoDetailMapper -> {
            InfoDetailDynamicSqlSupport.InfoDetail infoDetail = InfoDetailDynamicSqlSupport.infoDetail;
            //获取床铺最后一条更新数据
            List<InfoDetail> detailList = infoDetailMapper.selectByExample().where(infoDetail.interfaceMainid, IsEqualTo.of(() -> mainID))
                    .orderBy(infoDetail.updatetime.descending())
                    .build()
                    .execute();
            return detailList;
        });

        return infoDetailList;
    }


    /**
     * @param
     * @return
     * @Description 辅助类
     * @date 2019/6/18
     * @auther zvbb
     */
    @Data
    private static class AssistClass {
        private List<InfoInteface> infoIntefaceList;
        private List<InfoDetail> infoDetailList;
    }

    private static String getBedDetailLastRecordSQL(List<String> mainidList) {
        String template = "SELECT * FROM Info_Detail WHERE Record_No IN  (SELECT\n" +
                "MAX(Record_No)\n" +
                "FROM\n" +
                "Info_Detail \n" +
                "WHERE\n" +
                "Interface_MainID IN " +
                "(" + StringUtils.join(mainidList, ',') + ")" +
                "GROUP BY\n" +
                "\tInterface_MainID)\n";
        return template;
    }
}
