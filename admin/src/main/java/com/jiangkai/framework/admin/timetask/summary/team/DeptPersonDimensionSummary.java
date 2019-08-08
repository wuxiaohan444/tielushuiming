package com.jiangkai.framework.admin.timetask.summary.team;

import com.jiangkai.framework.admin.common.enums.DataName;
import com.jiangkai.framework.admin.common.enums.HaveEdit;
import com.jiangkai.framework.admin.common.enums.SumType;
import com.jiangkai.framework.admin.timetask.JobManager;
import com.jiangkai.framework.admin.timetask.summary.data.UpdateDB;
import com.jiangkai.framework.admin.timetask.summary.util.SummaryRedisUtil;
import com.jiangkai.framework.admin.util.DateUtils;
import com.jiangkai.framework.source.model.BedRecord;
import com.jiangkai.framework.source.model.SummaryDeptBed;
import com.jiangkai.framework.source.model.SummaryDeptPerson;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * User: zvbb
 * Date: 2019/5/16
 * Description: 部门下司机维度的数据汇总
 */
@Component
@RequiredArgsConstructor
public class DeptPersonDimensionSummary {
    private final JobManager jobManager;
    private final UpdateDB updateDB;
    private final SummaryDeatails summaryDeatails;

    @Async
    public void sumaryEntry(List<BedRecord> bedRecordList, HashMap assistMap) {
        if (!CollectionUtils.isEmpty(bedRecordList)) {
            //以司机所属部门分组
            HashMap<Long, List<BedRecord>> bedGroupByDriver = this.collectByDriver(bedRecordList);
            if (null != bedGroupByDriver) {
                bedGroupByDriver.forEach((deptID, bedRecords) -> {
                    //以月份分组
                    HashMap<Date, List<BedRecord>> bedGroupByDriverAndMonthDate = this.collectGroupByDayDate(bedRecords);
                    this.summaryDay(bedGroupByDriverAndMonthDate, assistMap);
                    this.summaryMonth(bedGroupByDriverAndMonthDate, assistMap);
                });
            }
        }
        //更新数据库
        updateDB.insertSumPersonResut(jobManager.getSummaryDeptPersonHashMap());
    }

    public void summaryDay(HashMap<Date, List<BedRecord>> bedGroupByDriverAndMonthDate, HashMap assistMap) {
        if (null != bedGroupByDriverAndMonthDate) {
            bedGroupByDriverAndMonthDate.forEach((recordTime, terminalRecords) -> {
                if (null != terminalRecords) {
                    HashMap<Date, List<BedRecord>> dayDateListHashMap = this.collectGroupByDayDate(terminalRecords);
                    if (null != dayDateListHashMap) {
                        dayDateListHashMap.forEach((dayDate, dayRecords) -> {
                            BedRecord bedRecord = dayRecords.get(0);
                            //统计的KEY
                            String KEY = SummaryRedisUtil.getDeptPersonSummaryKey(bedRecord, SumType.DAY);
                            SummaryDeptPerson summaryDeptPerson = jobManager.getSummaryDeptPersonHashMap().get(KEY);
                            //判断记录是否存在
                            if (null == summaryDeptPerson) {
                                summaryDeptPerson = new SummaryDeptPerson();
                                SummaryRedisUtil.initSummaryDeptPerson(summaryDeptPerson, bedRecord, SumType.DAY, assistMap);
                                jobManager.getSummaryDeptPersonHashMap().put(KEY, summaryDeptPerson);
                            } else {
                                if (Objects.nonNull(assistMap)) {
                                    //统计到Info_Inteface中的那条记录
                                    Integer mainId = (Integer) assistMap.get(DataName.MAINID);
                                    summaryDeptPerson.setMainid(mainId);
                                    if (Objects.isNull(assistMap.get(DataName.UNFINISHRECORS))) {
                                        List<Integer> unFinishRecords = (List<Integer>) assistMap.get(DataName.UNFINISHRECORS);
                                        List<String> stringList = unFinishRecords.stream().map((record) -> record.intValue() + "").collect(Collectors.toList());
                                        summaryDeptPerson.setUnfinishrecords(StringUtils.join(stringList, ','));
                                    }
                                }
                                summaryDeptPerson.setHaveedit(HaveEdit.EDIT.getCode());
                            }
                            //调用统计流程
                            this.deptSummaryProcControl(summaryDeptPerson, dayRecords, null, SumType.DAY, assistMap);
                        });
                    }
                }
            });
        }
    }

    public void summaryMonth(HashMap<Date, List<BedRecord>> bedGroupByDriverAndMonthDate, HashMap assistMap) {
        if (null != bedGroupByDriverAndMonthDate) {
            bedGroupByDriverAndMonthDate.forEach((recordTime, terminalRecords) -> {
                if (null != terminalRecords) {
                    HashMap<Date, List<BedRecord>> dayDateListHashMap = this.collectGroupByMonthDate(terminalRecords);
                    if (null != dayDateListHashMap) {
                        dayDateListHashMap.forEach((monthDate, monthRecords) -> {
                            BedRecord bedRecord = monthRecords.get(0);
                            //统计的KEY
                            String KEY = SummaryRedisUtil.getDeptPersonSummaryKey(bedRecord, SumType.MONTH);
                            SummaryDeptPerson summaryDeptPerson = jobManager.getSummaryDeptPersonHashMap().get(KEY);
                            //判断记录是否存在
                            if (null == summaryDeptPerson) {
                                summaryDeptPerson = new SummaryDeptPerson();
                                SummaryRedisUtil.initSummaryDeptPerson(summaryDeptPerson, bedRecord, SumType.MONTH, assistMap);
                                jobManager.getSummaryDeptPersonHashMap().put(KEY, summaryDeptPerson);
                            } else {
                                if (Objects.nonNull(assistMap)) {
                                    //统计到Info_Inteface中的那条记录
                                    Integer mainId = (Integer) assistMap.get(DataName.MAINID);
                                    summaryDeptPerson.setMainid(mainId);
                                    if (Objects.isNull(assistMap.get(DataName.UNFINISHRECORS))) {
                                        List<Integer> unFinishRecords = (List<Integer>) assistMap.get(DataName.UNFINISHRECORS);
                                        List<String> stringList = unFinishRecords.stream().map((record) -> record.intValue() + "").collect(Collectors.toList());
                                        summaryDeptPerson.setUnfinishrecords(StringUtils.join(stringList, ','));
                                    }
                                }
                                summaryDeptPerson.setHaveedit(HaveEdit.EDIT.getCode());
                            }
                            //调用统计流程
                            this.deptSummaryProcControl(summaryDeptPerson, monthRecords, null, SumType.MONTH, assistMap);
                        });
                    }
                }
            });
        }
    }

    /**
     * @param
     * @return
     * @Description 统计的流程控制
     * @date 2019/5/17
     * @auther zvbb
     */
    private void deptSummaryProcControl(SummaryDeptPerson summaryDeptPerson, List<BedRecord> bedRecordList, Long driverDeptId, SumType sumType, HashMap assistMap) {
        //bedRecordList为空,return
        if (CollectionUtils.isEmpty(bedRecordList)) {
            return;
        }

        BedRecord bedRecord = bedRecordList.get(0);
        if (null == driverDeptId) {
            driverDeptId = bedRecord.getDeptId();
        }
        //该部门的统计
        summaryDeatails.summaryDetails(summaryDeptPerson, bedRecordList, sumType, driverDeptId);

        if (null == driverDeptId) {
            String driverWorkno = bedRecord.getDriverWorkno();
            driverDeptId = jobManager.getDriverDeptId(driverWorkno);
        }
        //上级部门 id
        Long parentDeptId = jobManager.getParentDeptId(driverDeptId);
        //没有了上级部门,return
        if (Objects.isNull(parentDeptId) || parentDeptId.longValue() == 0) {
            return;
        } else {
            String KEY = SummaryRedisUtil.getDeptPersonSummaryKey(parentDeptId, bedRecord, sumType);
            summaryDeptPerson = jobManager.getSummaryDeptPersonHashMap().get(KEY);
            if (Objects.isNull(summaryDeptPerson)) {
                summaryDeptPerson = new SummaryDeptPerson();
                summaryDeptPerson = new SummaryDeptPerson();
                SummaryRedisUtil.initSummaryDeptPerson(summaryDeptPerson, bedRecord, sumType, assistMap, parentDeptId);
                jobManager.getSummaryDeptPersonHashMap().put(KEY, summaryDeptPerson);
            }
            deptSummaryProcControl(summaryDeptPerson, bedRecordList, parentDeptId, sumType, assistMap);
        }
    }

    /**
     * @param bedRecordList Info_Inteface中的原始数据
     * @return HashMap<部门Id, List < BedRecord>>
     * @Description 收集生成bedRecord睡眠的司机属于哪个时间段
     * @date 2019/5/18
     * @auther zvbb
     */
    private HashMap<Long, List<BedRecord>> collectByDriver(List<BedRecord> bedRecordList) {
        if (!CollectionUtils.isEmpty(bedRecordList)) {
            Map<Long, List<BedRecord>> collect = bedRecordList.stream().collect(Collectors.groupingBy(bedRecord -> {
                String driverWorkno = bedRecord.getDriverWorkno();
                //司机所在部门
                Long driverDeptId = jobManager.getDriverDeptId(driverWorkno);
                return driverDeptId;
            }));
            return new HashMap<>(collect);
        }
        return null;
    }

    /**
     * @param bedRecordList Info_Inteface中的原始数据
     * @return HashMap<记录时间, List < BedRecord>>
     * @Description 收集生成bedRecord睡眠的床铺属于哪个时间段
     * @date 2019/5/18
     * @auther zvbb
     */
    private HashMap<Date, List<BedRecord>> collectGroupByMonthDate(List<BedRecord> bedRecordList) {
        if (!CollectionUtils.isEmpty(bedRecordList)) {
            Map<Date, List<BedRecord>> collect = bedRecordList.stream()
                    .collect(Collectors.groupingBy(bedRecord -> {
                        Date checkindate = bedRecord.getCheckindate();
                        //月份
                        Date monthFirstDay = DateUtils.getMonthFirstDay(checkindate);
                        return monthFirstDay;
                    }));
            return new HashMap<>(collect);
        }
        return null;
    }

    /**
     * @param bedRecordList Info_Inteface中的原始数据
     * @return HashMap<部门Id, List < BedRecord>>
     * @Description 收集生成bedRecord睡眠的床铺属于哪个时间段(天)
     * @date 2019/5/18
     * @auther zvbb
     */
    private HashMap<Date, List<BedRecord>> collectGroupByDayDate(List<BedRecord> bedRecordList) {
        if (!CollectionUtils.isEmpty(bedRecordList)) {
            Map<Date, List<BedRecord>> collect = bedRecordList.stream()
                    .collect(Collectors.groupingBy(bedRecord -> {
                        Date checkindate = bedRecord.getCheckindate();
                        //天
                        Date dayZeroHour = DateUtils.getDayZeroHour(checkindate);
                        return dayZeroHour;
                    }));
            return new HashMap<>(collect);
        }
        return null;
    }
}
