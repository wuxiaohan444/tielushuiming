package com.jiangkai.framework.admin.timetask.summary.team;

import com.jiangkai.framework.admin.common.enums.HaveEdit;
import com.jiangkai.framework.admin.common.enums.SumType;
import com.jiangkai.framework.admin.timetask.JobManager;
import com.jiangkai.framework.admin.timetask.summary.data.UpdateDB;
import com.jiangkai.framework.admin.common.enums.DataName;
import com.jiangkai.framework.admin.timetask.summary.util.SummaryRedisUtil;
import com.jiangkai.framework.admin.util.DateUtils;
import com.jiangkai.framework.source.model.BedRecord;
import com.jiangkai.framework.source.model.SummaryDeptBed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User: zvbb
 * Date: 2019/5/16
 * Description: 部门下床铺维度的数据汇总
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DeptBedDimensionSummary {
    private final JobManager jobManager;
    private final UpdateDB updateDB;
    private final SummaryDeatails summaryDeatails;

    @Async
    public void sumaryEntry(List<BedRecord> bedRecordList, HashMap assistMap) {
        if (!CollectionUtils.isEmpty(bedRecordList)) {
            HashMap<Long, List<BedRecord>> bedGroupByDept = this.collectGroupByDept(bedRecordList);
            if (null != bedGroupByDept) {
                bedGroupByDept.forEach((deptID, bedRecords) -> {
                    HashMap<Date, List<BedRecord>> bedGroupByDeptAndMonthDate = this.collectGroupByMonthDate(bedRecords);
                    this.summaryDay(bedGroupByDeptAndMonthDate, assistMap);
                    this.summaryMonth(bedGroupByDeptAndMonthDate, assistMap);
                });
            }
        }
        //更新数据库
        updateDB.insertSumBedResut(jobManager.getSummaryDeptBedHashMap());
    }

    public void summaryDay(HashMap<Date, List<BedRecord>> bedGroupByDeptAndMonthDate, HashMap assistMap) {
        if (null != bedGroupByDeptAndMonthDate) {
            bedGroupByDeptAndMonthDate.forEach((recordTime, terminalRecords) -> {
                if (null != terminalRecords) {
                    HashMap<Date, List<BedRecord>> dayDateListHashMap = this.collectGroupByDayDate(terminalRecords);
                    if (null != dayDateListHashMap) {
                        dayDateListHashMap.forEach((dayDate, dayRecords) -> {
                            BedRecord bedRecord = dayRecords.get(0);
                            //统计的KEY
                            String KEY = SummaryRedisUtil.getDeptBedSummaryKey(bedRecord, SumType.DAY);
                            SummaryDeptBed summaryDeptBed = jobManager.getSummaryDeptBedHashMap().get(KEY);
                            //判断记录是否存在
                            if (null == summaryDeptBed) {
                                summaryDeptBed = new SummaryDeptBed();
                                SummaryRedisUtil.initSummaryDeptBed(summaryDeptBed, bedRecord, SumType.DAY, assistMap);
                                jobManager.getSummaryDeptBedHashMap().put(KEY, summaryDeptBed);
                            } else {
                                if (Objects.nonNull(assistMap)) {
                                    //统计到Info_Inteface中的那条记录
                                    Integer mainId = (Integer) assistMap.get(DataName.MAINID);
                                    summaryDeptBed.setMainid(mainId);
                                    if (Objects.isNull(assistMap.get(DataName.UNFINISHRECORS))) {
                                        List<Integer> unFinishRecords = (List<Integer>) assistMap.get(DataName.UNFINISHRECORS);
                                        List<String> stringList = unFinishRecords.stream().map((record) -> record.intValue() + "").collect(Collectors.toList());
                                        summaryDeptBed.setUnfinishrecords(StringUtils.join(stringList, ','));
                                    }
                                }
                                summaryDeptBed.setHaveedit(HaveEdit.EDIT.getCode());
                            }
                            //调用统计流程
                            this.deptSummaryProcControl(summaryDeptBed, dayRecords, null, SumType.DAY, assistMap);
                        });
                    }
                }
            });
        }
    }

    public void summaryMonth(HashMap<Date, List<BedRecord>> bedGroupByDeptAndMonthDate, HashMap assistMap) {
        if (null != bedGroupByDeptAndMonthDate) {
            bedGroupByDeptAndMonthDate.forEach((recordTime, terminalRecords) -> {
                BedRecord bedRecord = terminalRecords.get(0);
                //统计的KEY
                String KEY = SummaryRedisUtil.getDeptBedSummaryKey(bedRecord, SumType.MONTH);
                SummaryDeptBed summaryDeptBed = jobManager.getSummaryDeptBedHashMap().get(KEY);
                //判断记录是否存在
                if (null == summaryDeptBed) {
                    summaryDeptBed = new SummaryDeptBed();
                    SummaryRedisUtil.initSummaryDeptBed(summaryDeptBed, bedRecord, SumType.MONTH, assistMap);
                    jobManager.getSummaryDeptBedHashMap().put(KEY, summaryDeptBed);
                } else {
                    if (Objects.nonNull(assistMap)) {
                        //统计到Info_Inteface中的那条记录
                        Integer mainId = (Integer) assistMap.get(DataName.MAINID);
                        summaryDeptBed.setMainid(mainId);
                        if (Objects.isNull(assistMap.get(DataName.UNFINISHRECORS))) {
                            List<Integer> unFinishRecords = (List<Integer>) assistMap.get(DataName.UNFINISHRECORS);
                            List<String> stringList = unFinishRecords.stream().map((record) -> record.intValue() + "").collect(Collectors.toList());
                            summaryDeptBed.setUnfinishrecords(StringUtils.join(stringList, ','));
                        }
                    }
                    summaryDeptBed.setHaveedit(HaveEdit.EDIT.getCode());
                }
                //调用统计流程
                this.deptSummaryProcControl(summaryDeptBed, terminalRecords, null, SumType.MONTH, assistMap);
            });
        }
    }

    /**
     * @param bedRecordList Info_Inteface中的原始数据
     * @return HashMap<部门Id, List < BedRecord>>
     * @Description 收集生成bedRecord睡眠的床铺属于部门的.
     * @date 2019/5/18
     * @auther zvbb
     */
    private HashMap<Long, List<BedRecord>> collectGroupByDept(List<BedRecord> bedRecordList) {
        if (!CollectionUtils.isEmpty(bedRecordList)) {
            Map<Long, List<BedRecord>> collect = bedRecordList.stream()
                    .collect(Collectors.groupingBy(bedRecord -> {
                        return bedRecord.getDeptId();
                    }));
            return new HashMap<>(collect);
        }
        return null;
    }

    /**
     * @param bedRecordList Info_Inteface中的原始数据
     * @return HashMap<部门Id, List < BedRecord>>
     * @Description 收集生成bedRecord睡眠的床铺属于哪个时间段(月)
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

    /**
     * @param
     * @return
     * @Description 统计的流程控制(月)
     * @date 2019/5/17
     * @auther zvbb
     */
    private void deptSummaryProcControl(SummaryDeptBed summaryDeptBed, List<BedRecord> bedRecordList, Long deptID, SumType sumType, HashMap assistMap) {
        //bedRecordList为空,return
        if (CollectionUtils.isEmpty(bedRecordList)) {
            return;
        }
        BedRecord bedRecord = bedRecordList.get(0);
        if (null == deptID) {
            deptID = bedRecord.getDeptId();
        }
        //该部门的统计
        summaryDeatails.summaryDetails(summaryDeptBed, bedRecordList, sumType, deptID);

        Long parentDeptId = jobManager.getParentDeptId(deptID);
        //没有了上级部门,return
        if (Objects.isNull(parentDeptId) || parentDeptId.longValue() == 0) {
            return;
        } else {
            String KEY = SummaryRedisUtil.getDeptBedSummaryKey(parentDeptId, bedRecord, sumType);
            summaryDeptBed = jobManager.getSummaryDeptBedHashMap().get(KEY);
            if (Objects.isNull(summaryDeptBed)) {
                summaryDeptBed = new SummaryDeptBed();
                SummaryRedisUtil.initSummaryDeptBed(summaryDeptBed, bedRecord, sumType, assistMap, parentDeptId);
                jobManager.getSummaryDeptBedHashMap().put(KEY, summaryDeptBed);
            } else {
                summaryDeptBed.setHaveedit(HaveEdit.EDIT.getCode());
            }
            deptSummaryProcControl(summaryDeptBed, bedRecordList, parentDeptId, sumType, assistMap);
        }
    }

}
