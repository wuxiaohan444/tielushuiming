package com.jiangkai.framework.admin.service;

import com.jiangkai.framework.admin.bean.TeamBean;
import com.jiangkai.framework.admin.bean.TeamPieBean;
import com.jiangkai.framework.admin.bean.TeamRes;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.common.enums.SumType;
import com.jiangkai.framework.admin.util.DateUtils;
import com.jiangkai.framework.admin.util.FormatUtils;
import com.jiangkai.framework.source.mapper.SummaryDeptBedDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.SummaryDeptBedMapper;
import com.jiangkai.framework.source.model.SummaryDeptBed;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SimpleSortSpecification;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanOrEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsLessThanOrEqualTo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: zvbb
 * Date: 2019/6/13
 * Description:
 */
@Component
@RequiredArgsConstructor
public class TeamService {
    private final SummaryDeptBedMapper summaryDeptBedMapper;

    public Result baseInfo(TeamBean teamBean) {
        Result checkResult = dataStandard(teamBean);
        if (0 != checkResult.getCode()) {
            return checkResult;
        }
        SummaryDeptBedDynamicSqlSupport.SummaryDeptBed summaryDeptBed = SummaryDeptBedDynamicSqlSupport.summaryDeptBed;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SummaryDeptBed>>>.QueryExpressionWhereBuilder builder
                = summaryDeptBedMapper.selectByExample()
                .where(summaryDeptBed.deptid, IsEqualTo.of(() -> teamBean.getId()))
                .and(summaryDeptBed.sumtype, IsEqualTo.of(SumType.MONTH::getCode));

        //时间
        builder.and(summaryDeptBed.date, IsLessThanOrEqualTo.of(() -> DateUtils.getSepcialEndMonth(teamBean.getYear(), teamBean.getHalfYear(),
                teamBean.getQuarter(), teamBean.getMonth())))
                .and(summaryDeptBed.date, IsGreaterThanOrEqualTo.of(() -> DateUtils.getSpecialMonth(teamBean.getYear(), teamBean.getHalfYear(),
                        teamBean.getQuarter(), teamBean.getMonth())))
                .orderBy(SimpleSortSpecification.of(summaryDeptBed.date.name()).descending());

        List<SummaryDeptBed> summaryDeptBedList = builder.build().execute();
        TeamRes teamRes = new TeamRes();
        if (!CollectionUtils.isEmpty(summaryDeptBedList)) {
            SummaryDeptBed record = summaryDeptBedList.get(0);
            BeanUtils.copyProperties(record, teamRes);
            //人均使用时长
            teamRes.setAvgusetime(FormatUtils.secondTime2HMS(record.getAvgusetime().longValue()));
            //人均睡眠时长
            teamRes.setAvgsleept(FormatUtils.secondTime2HMS(record.getAvgsleept().longValue()));
            //人均浅睡时长
            teamRes.setAvgqian(FormatUtils.secondTime2HMS(record.getAvgqian().longValue()));
            //人均深睡时长
            teamRes.setAvgShen(FormatUtils.secondTime2HMS(record.getAvgshen().longValue()));
        }
        return Result.success(teamRes);
    }

    /**
     * @param
     * @return
     * @Description 饼图数据
     * @date 2019/6/14
     * @auther zvbb
     */
    public Result pieInfo(TeamBean teamBean) {
        Result checkResult = dataStandard(teamBean);
        if (0 != checkResult.getCode()) {
            return checkResult;
        }
        SummaryDeptBedDynamicSqlSupport.SummaryDeptBed summaryDeptBed = SummaryDeptBedDynamicSqlSupport.summaryDeptBed;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SummaryDeptBed>>>.QueryExpressionWhereBuilder builder
                = summaryDeptBedMapper.selectByExample().where(summaryDeptBed.deptid, IsEqualTo.of(() -> teamBean.getId()));

        //统计维度设为月
        builder.and(summaryDeptBed.sumtype, IsEqualTo.of(SumType.MONTH::getCode));
        //时间
        builder.and(summaryDeptBed.date, IsLessThanOrEqualTo.of(() -> DateUtils.getSepcialEndMonth(teamBean.getYear(), teamBean.getHalfYear(),
                teamBean.getQuarter(), teamBean.getMonth())))
                .and(summaryDeptBed.date, IsGreaterThanOrEqualTo.of(() -> DateUtils.getSpecialMonth(teamBean.getYear(), teamBean.getHalfYear(),
                        teamBean.getQuarter(), teamBean.getMonth())));
        //查询
        List<SummaryDeptBed> summaryDeptBedList = builder.build().execute();

        TeamPieBean teamPieBean = new TeamPieBean();
        if (!CollectionUtils.isEmpty(summaryDeptBedList)) {
            SummaryDeptBed record = summaryDeptBedList.get(0);
            Long lowsleepeffcnt = record.getLowsleepeffcnt();
            Long middlesleepeffcnt = record.getMiddlesleepeffcnt();
            Long hightsleepeffcnt = record.getHightsleepeffcnt();
            TeamPieBean.PieSleepEff pieSleepEff = teamPieBean.getPieSleepEff();
            pieSleepEff.setLowSleepEffCnt(lowsleepeffcnt);
            pieSleepEff.setMiddleSleepEffCnt(middlesleepeffcnt);
            pieSleepEff.setHightSleepEffCnt(hightsleepeffcnt);

            Long lowsleeptimecnt = record.getLowsleeptimecnt();
            Long middlesleeptimecnt = record.getMiddlesleeptimecnt();
            Long hightsleeptimecnt = record.getHightsleeptimecnt();
            TeamPieBean.PieSleepTime pieSleepTime = teamPieBean.getPieSleepTime();
            pieSleepTime.setLowSleepTimeCnt(lowsleeptimecnt);
            pieSleepTime.setMiddleSleepTimeCnt(middlesleeptimecnt);
            pieSleepTime.setHightSleepTimeCnt(hightsleeptimecnt);
        }
        return Result.success(teamPieBean);
    }

    /**
     * @param
     * @return
     * @Description 同比
     * @date 2019/6/14
     * @auther zvbb
     */
    public Result tbTrend(TeamBean teamBean) {
        Result checkResult = dataStandard(teamBean);
        if (0 != checkResult.getCode()) {
            return checkResult;
        }
        SummaryDeptBedDynamicSqlSupport.SummaryDeptBed summaryDeptBed = SummaryDeptBedDynamicSqlSupport.summaryDeptBed;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SummaryDeptBed>>>.QueryExpressionWhereBuilder builder
                = summaryDeptBedMapper.selectByExample().where(summaryDeptBed.deptid, IsEqualTo.of(() -> teamBean.getId()));

        //某月第一天
        Date monthFirstDay = DateUtils.getSpecialMonth(teamBean.getYear(), teamBean.getHalfYear(), teamBean.getQuarter(), teamBean.getMonth());
        if (Objects.nonNull(teamBean.getMonth())) {
            //统计维度(天)
            builder.and(summaryDeptBed.sumtype, IsEqualTo.of(SumType.DAY::getCode));
            //某月最后一天
            Date monthLastDay = DateUtils.getMonthLastDay(monthFirstDay);
            builder.and(summaryDeptBed.date, IsLessThanOrEqualTo.of(() -> monthLastDay))
                    .and(summaryDeptBed.date, IsGreaterThanOrEqualTo.of(() -> monthFirstDay));
        } else {
            //统计维度(月)
            builder.and(summaryDeptBed.sumtype, IsEqualTo.of(SumType.MONTH::getCode));
            //时间
            builder.and(summaryDeptBed.date, IsLessThanOrEqualTo.of(() -> DateUtils.getSepcialEndMonth(teamBean.getYear(), teamBean.getHalfYear(),
                    teamBean.getQuarter(), teamBean.getMonth())))
                    .and(summaryDeptBed.date, IsGreaterThanOrEqualTo.of(() -> monthFirstDay));
        }


        List<SummaryDeptBed> summaryDeptBedList = builder.build().execute();
        //查询类型
        int selectType = judgeSelectType(teamBean.getYear(), teamBean.getHalfYear(),
                teamBean.getQuarter(), teamBean.getMonth());
        //获取时间在数组中的位置
        List<String> dateOrder = getDateOrder(teamBean.getYear(), teamBean.getHalfYear(),
                teamBean.getQuarter(), teamBean.getMonth());
        //获取存放结果的数组
        List<Map<String, Number>> result = getResList(selectType, monthFirstDay);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        summaryDeptBedList.stream().forEach(record -> {
            Date date = record.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            String format = simpleDateFormat.format(date);
            int i = dateOrder.indexOf(format);
            if (i < 0) {
                return;
            }
            BigDecimal avgsleepeff = record.getAvgsleepeff();
            Map<String, Number> node = new HashMap<>();
            String key = "";
            if (selectType == 30) {
                key = format;
            } else {
                key = calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月";
            }
            node.put(key, avgsleepeff);
            result.set(i, node);
        });
        return Result.success(result);
    }

    /**
     * @param
     * @return
     * @Description 环比
     * @date 2019/6/14
     * @auther zvbb
     */
    public Result hbTrend(TeamBean teamBean) {
        Result checkResult = dataStandard(teamBean);
        if (0 != checkResult.getCode()) {
            return checkResult;
        }
        List<List<Map<String, Number>>> result = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            result.add(null);
        }
        Integer year = teamBean.getYear();
        for (int i = 0; i < 3; i++) {
            year = year - i;
            teamBean.setYear(year);
            Result node = this.tbTrend(teamBean);
            List<Map<String, Number>> data = (List<Map<String, Number>>) node.getData();
            result.set(i, data);
        }
        return Result.success(result);
    }

    /**
     * @param
     * @return
     * @Description 获取某个时间在数组中位置(借助List的下标)
     * @date 2019/6/14
     * @auther zvbb
     */
    private static List<String> getDateOrder(Integer year, Integer halfYear, Integer quarter, Integer month) {
        List<String> result = new ArrayList<>();
        int type = judgeSelectType(year, halfYear, quarter, month);
        Date specialMonth = DateUtils.getSpecialMonth(year, halfYear, quarter, month);
        Calendar instance = Calendar.getInstance();
        instance.setTime(specialMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //展示月的时候，显示这个月每天的统计数据
        if (type == 30) {
            int dayCnt = DateUtils.getMonthDayCnt(specialMonth);
            for (int i = 0; i < dayCnt; i++) {
                if (i != 0) {
                    instance.add(Calendar.DAY_OF_MONTH, 1);
                }
                Date tmp = instance.getTime();
                String format = simpleDateFormat.format(tmp);
                result.add(format);
            }
        } else {
            for (int i = 0; i < type; i++) {
                if (i != 0) {
                    instance.add(Calendar.MONTH, 1);
                }
                Date tmp = instance.getTime();
                String format = simpleDateFormat.format(tmp);
                result.add(format);
            }
        }
        return result;
    }

    private static List<Map<String, Number>> getResList(int selectType, Date date) {
        List<Map<String, Number>> result;
        //如果是月的话，需要知道这个月有多少天
        if (selectType == 30) {
            int dayCnt = DateUtils.getMonthDayCnt(date);
            result = new ArrayList<>(dayCnt);
            for (int i = 0; i < dayCnt; i++) {
                result.add(null);
            }
        } else {
            result = new ArrayList<>(selectType);
            for (int i = 0; i < selectType; i++) {
                result.add(null);
            }
        }
        return result;
    }

    /**
     * @param
     * @return
     * @Description 判断查询类型 30.月  3.季度  6.半年  12.全年
     * @date 2019/6/14
     * @auther zvbb
     */
    private static int judgeSelectType(Integer year, Integer halfYear, Integer quarter, Integer month) {
        int type = 12;
        if (!Objects.isNull(halfYear)) {
            type = 6;
        }
        if (!Objects.isNull(quarter)) {
            type = 3;
        }
        if (!Objects.isNull(month)) {
            type = 30;
        }
        return type;
    }

    /**
     * @Description 判断页面传来的参数是否合法
     */
    private static Result dataStandard(TeamBean teamBean) {
        if (Objects.isNull(teamBean.getYear())) {
            return Result.paramError("年份不能为空!");
        }

        int flat = 0;
        if (Objects.nonNull(teamBean.getHalfYear())) {
            flat++;
        }

        if (Objects.nonNull(teamBean.getQuarter())) {
            flat++;
        }

        if (Objects.nonNull(teamBean.getMonth())) {
            flat++;
        }

        if (flat > 1) {
            return Result.paramError("月份、季度、半年，三选一");
        }

        return Result.success();
    }
}
