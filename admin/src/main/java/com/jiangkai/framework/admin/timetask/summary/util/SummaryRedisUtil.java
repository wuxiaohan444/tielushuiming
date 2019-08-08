package com.jiangkai.framework.admin.timetask.summary.util;

import com.jiangkai.framework.admin.common.enums.DataName;
import com.jiangkai.framework.admin.common.enums.HaveEdit;
import com.jiangkai.framework.admin.common.enums.SumType;
import com.jiangkai.framework.admin.util.DateUtils;
import com.jiangkai.framework.source.model.BedRecord;
import com.jiangkai.framework.source.model.SummaryDeptBed;
import com.jiangkai.framework.source.model.SummaryDeptPerson;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User: zvbb
 * Date: 2019/5/15
 * Description: Redis中数据的相关操作
 */
@Component
@RequiredArgsConstructor
public class SummaryRedisUtil {


    /**
     * @param
     * @return
     * @Description 生成部门床铺维度统计的key
     * @date 2019/5/17
     * @auther zvbb
     */
    public static String getDeptBedSummaryKey(BedRecord bedRecord, SumType sumType) {
        //部门id
        Long deptId = bedRecord.getDeptId();
        //入住时间
        Date checkindate = bedRecord.getCheckindate();
        //统计日期
        Date sumDate = null;
        //月份
        if (sumType.getCode().equals(sumType.MONTH.getCode())) {
            sumDate = DateUtils.getMonthFirstDay(checkindate);
        } else {//天
            sumDate = DateUtils.getDayZeroHour(checkindate);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(sumDate);
        //统计数据的KEY
        String key = bedSumKey(sumType, deptId, dateStr);
        return key;
    }

    /**
     * @param
     * @return
     * @Description 生成部门床铺维度统计的key
     * @date 2019/5/17
     * @auther zvbb
     */
    public static String getDeptBedSummaryKey(Long deptId, BedRecord bedRecord, SumType sumType) {
        //入住时间
        Date checkindate = bedRecord.getCheckindate();
        //统计日期
        Date sumDate = null;
        //月份
        if (sumType.getCode().equals(sumType.MONTH.getCode())) {
            sumDate = DateUtils.getMonthFirstDay(checkindate);
        } else {//天
            sumDate = DateUtils.getDayZeroHour(checkindate);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(sumDate);
        //统计数据的KEY
        String key = bedSumKey(sumType, deptId, dateStr);
        return key;
    }

    /**
     * @param
     * @return
     * @Description 生成部门床铺维度统计的key
     * @date 2019/5/17
     * @auther zvbb
     */
    public static String getDeptBedSummaryKey(Long deptId, Date date, SumType sumType) {
        //统计日期
        Date sumDate = null;
        //月份
        if (sumType.getCode().equals(sumType.MONTH.getCode())) {
            sumDate = DateUtils.getMonthFirstDay(date);
        } else {//天
            sumDate = DateUtils.getDayZeroHour(date);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(sumDate);
        //统计数据的KEY
        String key = bedSumKey(sumType, deptId, dateStr);
        return key;
    }


    /**
     * @param
     * @return
     * @Description 生成部门床铺维度统计的key
     * @date 2019/5/17
     * @auther zvbb
     */
    public static String getDeptPersonSummaryKey(BedRecord bedRecord, SumType sumType) {
        //部门id
        Long deptId = bedRecord.getDeptId();
        //入住时间
        Date checkindate = bedRecord.getCheckindate();
        //统计日期
        Date sumDate = null;
        //月份
        if (sumType.getCode().equals(sumType.MONTH.getCode())) {
            sumDate = DateUtils.getMonthFirstDay(checkindate);
        } else {//天
            sumDate = DateUtils.getDayZeroHour(checkindate);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(sumDate);
        //统计数据的KEY
        String key = perSumKey(sumType, deptId, dateStr);
        return key;
    }

    /**
     * @param
     * @return
     * @Description 生成部门床铺维度统计的key
     * @date 2019/5/17
     * @auther zvbb
     */
    public static String getDeptPersonSummaryKey(Long deptId, Date date, SumType sumType) {
        //统计日期
        Date sumDate = null;
        //月份
        if (sumType.getCode().equals(sumType.MONTH.getCode())) {
            sumDate = DateUtils.getMonthFirstDay(date);
        } else {//天
            sumDate = DateUtils.getDayZeroHour(date);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(sumDate);
        //统计数据的KEY
        String key = perSumKey(sumType, deptId, dateStr);
        return key;
    }

    /**
     * @param
     * @return
     * @Description 生成部门床铺维度统计的key
     * @date 2019/5/17
     * @auther zvbb
     */
    public static String getDeptPersonSummaryKey(Long deptId, BedRecord bedRecord, SumType sumType) {
        //入住时间
        Date checkindate = bedRecord.getCheckindate();
        //统计日期
        Date sumDate = null;
        //月份
        if (sumType.getCode().equals(sumType.MONTH.getCode())) {
            sumDate = DateUtils.getMonthFirstDay(checkindate);
        } else {//天
            sumDate = DateUtils.getDayZeroHour(checkindate);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(sumDate);
        //统计数据的KEY
        String key = perSumKey(sumType, deptId, dateStr);
        return key;
    }


    /**
     * @param
     * @return
     * @Description tag1-value1:tag2-value2 格式中获取 value1 与 value2
     * @date 2019/5/16
     * @auther zvbb
     */
    private static String[] splitValue(String str) {
        List list = new ArrayList();
        List<String> stringList = Arrays.asList(str.split(":"));
        stringList.stream()
                .flatMap((item) -> Arrays.asList(item.split("-")).stream())
                .forEach(list::add);
        String[] result = {(String) list.get(1), (String) list.get(3)};
        return result;
    }

    /**
     * @param
     * @return
     * @Description 初始化SummaryDeptPerson
     * @date 2019/6/12
     * @auther zvbb
     */
    public static void initSummaryDeptPerson(SummaryDeptPerson summaryDeptPerson, BedRecord bedRecord, SumType sumType, HashMap assistMap, Long... deptID) {
        if (deptID.length > 0) {
            summaryDeptPerson.setDeptid(deptID[0]);
        } else {
            summaryDeptPerson.setDeptid(bedRecord.getDeptId());
        }
        //统计类型
        summaryDeptPerson.setSumtype(sumType.getCode());
        //入住时间
        Date checkindate = bedRecord.getCheckindate();
        if (sumType.getCode().equals(SumType.MONTH.getCode())) {
            //月份
            Date monthFirstDay = DateUtils.getMonthFirstDay(checkindate);
            summaryDeptPerson.setDate(monthFirstDay);
        } else {
            //天
            Date dayZeroHour = DateUtils.getDayZeroHour(checkindate);
            summaryDeptPerson.setDate(dayZeroHour);
        }

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

        summaryDeptPerson.setSumtotaltime(0L);
        summaryDeptPerson.setSumtotalsleept(0L);
        summaryDeptPerson.setSumsleepeff(BigDecimal.valueOf(0));
        summaryDeptPerson.setSumqian(0L);
        summaryDeptPerson.setSumshen(0L);
        summaryDeptPerson.setTotalusetime(0L);
        summaryDeptPerson.setSleeptroublecnt(0L);
        summaryDeptPerson.setAutocallwakecnt(0L);
        summaryDeptPerson.setArtificalcallwakecnt(0L);
        summaryDeptPerson.setWarncnt(0L);
        summaryDeptPerson.setHaveedit(HaveEdit.EDIT.getCode());
    }

    /**
     * @param
     * @return
     * @Description 初始化SummaryDeptBed
     * @date 2019/6/12
     * @auther zvbb
     */
    public static void initSummaryDeptBed(SummaryDeptBed summaryDeptBed, BedRecord bedRecord, SumType sumType, HashMap assistMap, Long... deptID) {
        if (deptID.length > 0) {
            summaryDeptBed.setDeptid(deptID[0]);
        } else {
            summaryDeptBed.setDeptid(bedRecord.getDeptId());
        }
        //统计类型
        summaryDeptBed.setSumtype(sumType.getCode());
        //入住时间
        Date checkindate = bedRecord.getCheckindate();
        if (sumType.getCode().equals(SumType.MONTH.getCode())) {
            //月份
            Date monthFirstDay = DateUtils.getMonthFirstDay(checkindate);
            summaryDeptBed.setDate(monthFirstDay);
        } else {
            //天
            Date dayZeroHour = DateUtils.getDayZeroHour(checkindate);
            summaryDeptBed.setDate(dayZeroHour);
        }

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

        summaryDeptBed.setSumtotaltime(0L);
        summaryDeptBed.setSumtotalsleept(0L);
        summaryDeptBed.setSumsleepeff(BigDecimal.valueOf(0));
        summaryDeptBed.setSumqian(0L);
        summaryDeptBed.setSumshen(0L);
        summaryDeptBed.setTotalusetime(0L);
        summaryDeptBed.setSleeptroublecnt(0L);
        summaryDeptBed.setAutocallwakecnt(0L);
        summaryDeptBed.setArtificalcallwakecnt(0L);
        summaryDeptBed.setWarncnt(0L);
        summaryDeptBed.setHaveedit(HaveEdit.EDIT.getCode());
    }

    public static void initSummaryDeptBed(SummaryDeptBed summaryDeptBed, Long deptID, Date date, SumType sumType) {
        summaryDeptBed.setDeptid(deptID);
        //统计类型
        summaryDeptBed.setSumtype(sumType.getCode());
        //入住时间
        Date checkindate = date;
        if (sumType.getCode().equals(SumType.MONTH.getCode())) {
            //月份
            Date monthFirstDay = DateUtils.getMonthFirstDay(checkindate);
            summaryDeptBed.setDate(monthFirstDay);
        } else {
            //天
            Date dayZeroHour = DateUtils.getDayZeroHour(checkindate);
            summaryDeptBed.setDate(dayZeroHour);
        }


        summaryDeptBed.setSumtotaltime(0L);
        summaryDeptBed.setSumtotalsleept(0L);
        summaryDeptBed.setSumsleepeff(BigDecimal.valueOf(0));
        summaryDeptBed.setSumqian(0L);
        summaryDeptBed.setSumshen(0L);
        summaryDeptBed.setTotalusetime(0L);
        summaryDeptBed.setSleeptroublecnt(0L);
        summaryDeptBed.setAutocallwakecnt(0L);
        summaryDeptBed.setArtificalcallwakecnt(0L);
        summaryDeptBed.setWarncnt(0L);
    }

    /**
     * @param
     * @return
     * @Description 构建key
     * @date 2019/6/16
     * @auther zvbb
     */
    public static String activeBedKey(SumType sumType, long deptId, Date date, String serialNo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date activeDate = null;
        if (sumType.equals(SumType.MONTH)) {
            activeDate = DateUtils.getMonthFirstDay(date);
        } else if (sumType.equals(SumType.DAY)) {
            activeDate = DateUtils.getDayZeroHour(date);
        }
        String format = sdf.format(activeDate);
        return sumType.getMark() + "-" + deptId + "-" + format + "-" + serialNo;
    }

    public static String deptActiveBedSumKey(SumType sumType, long deptId, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date activeTypeDate = null;
        if (SumType.MONTH.equals(sumType)) {
            activeTypeDate = DateUtils.getMonthFirstDay(date);
        } else {
            activeTypeDate = DateUtils.getDayZeroHour(date);
        }
        String format = sdf.format(activeTypeDate);
        return sumType.getMark() + "-" + deptId + "-" + format;
    }

    private static String bedSumKey(SumType sumType, Long deptId, String dateStr) {
        return "DeptBedSummary-" + sumType.getMark() + "-" + deptId + "-" + dateStr;
    }

    private static String perSumKey(SumType sumType, Long deptId, String dateStr) {
        return "DeptPersonSummary-" + sumType.getMark() + "-" + deptId + "-" + dateStr;
    }

}
