package com.jiangkai.framework.admin.service;

import com.jiangkai.framework.admin.bean.HomeRes;
import com.jiangkai.framework.admin.bean.HomeSleepRes;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.util.DateUtils;
import com.jiangkai.framework.source.mapper.SummaryDeptBedDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.SummaryDeptBedMapper;
import com.jiangkai.framework.source.model.SummaryDeptBed;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * User: zvbb
 * Date: 2019/6/16
 * Description:
 */
@Component
@RequiredArgsConstructor
public class HomeService {
    public final SummaryDeptBedMapper summaryDeptBedMapper;

    public Result totalAnalysis(Long id) {
        if (Objects.isNull(id)) {
            return Result.businessError("参数错误!");
        }
        Date monthFirstDay = DateUtils.getMonthFirstDay(new Date());
        SummaryDeptBedDynamicSqlSupport.SummaryDeptBed summaryDeptBed = SummaryDeptBedDynamicSqlSupport.summaryDeptBed;

        //查询条件
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SummaryDeptBed>>>.QueryExpressionWhereBuilder builder
                = summaryDeptBedMapper.selectByExample().where(summaryDeptBed.deptid, IsEqualTo.of(() -> id));
        builder.and(summaryDeptBed.date, IsEqualTo.of(() -> monthFirstDay));

        //查询
        List<SummaryDeptBed> summaryDeptBedList = builder.build().execute();

        HomeRes homeRes = new HomeRes();
        if (!CollectionUtils.isEmpty(summaryDeptBedList)) {
            SummaryDeptBed record = summaryDeptBedList.get(0);
            BeanUtils.copyProperties(record, homeRes);
        }
        return Result.success(homeRes);
    }

    /**
     * @param
     * @return
     * @Description 睡眠质量分析
     * @date 2019/6/18
     * @auther zvbb
     */
    public Result sleepQualityAnalysis(Long id) {
        if (Objects.isNull(id)) {
            return Result.businessError("参数错误!");
        }
        Date monthFirstDay = DateUtils.getMonthFirstDay(new Date());
        SummaryDeptBedDynamicSqlSupport.SummaryDeptBed summaryDeptBed = SummaryDeptBedDynamicSqlSupport.summaryDeptBed;

        //查询条件
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SummaryDeptBed>>>.QueryExpressionWhereBuilder builder
                = summaryDeptBedMapper.selectByExample().where(summaryDeptBed.deptid, IsEqualTo.of(() -> id));
        builder.and(summaryDeptBed.date, IsEqualTo.of(() -> monthFirstDay));

        //查询
        List<SummaryDeptBed> summaryDeptBedList = builder.build().execute();
        HomeSleepRes homeSleepRes = new HomeSleepRes();
        for (int i = 0; i < summaryDeptBedList.size(); i++) {
            SummaryDeptBed record = summaryDeptBedList.get(0);
            //浅睡时长
            Long sumqian = record.getSumqian() * 5;
            //深睡时长
            Long sumshen = record.getSumshen() * 5;
            //入睡时长
            Long sumsleeptime = record.getSumsleeptime();
            //睡眠时长
            Long sumtotaltime = record.getSumtotaltime();
            HomeSleepRes.SleepTimeAnalysis sleepTimeAnalysis = homeSleepRes.getSleepTimeAnalysis();
            if (Objects.isNull(sumtotaltime) || sumtotaltime == 0L) {

            } else {
                if (Objects.nonNull(sumqian)) {
                    sleepTimeAnalysis.setQian(BigDecimal.valueOf(sumqian).divide(BigDecimal.valueOf(sumtotaltime), 4, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100)));
                }
                if (Objects.nonNull(sumshen)) {
                    sleepTimeAnalysis.setShen(BigDecimal.valueOf(sumshen).divide(BigDecimal.valueOf(sumtotaltime), 4, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100)));
                }
                if (Objects.nonNull(sumsleeptime)) {
                    sleepTimeAnalysis.setSleepTime(BigDecimal.valueOf(sumsleeptime).divide(BigDecimal.valueOf(sumtotaltime), 4, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100)));
                }
                if (Objects.nonNull(record.getRatioqian()) && Objects.nonNull(record.getRatioshen()) && Objects.nonNull(record.getRatiosleeptime())) {
                    sleepTimeAnalysis.setOther(BigDecimal.valueOf(100).subtract(sleepTimeAnalysis.getQian().add(sleepTimeAnalysis.getShen()).add(sleepTimeAnalysis.getSleepTime())));
                }
            }

            HomeSleepRes.SleepEffAnalysis sleepEffAnalysis = homeSleepRes.getSleepEffAnalysis();
            Long level1sleepeff = record.getLevel1sleepeff() == null ? 0 : record.getLevel1sleepeff();
            Long level2sleepeff = record.getLevel2sleepeff() == null ? 0 : record.getLevel2sleepeff();
            Long level3sleepeff = record.getLevel3sleepeff() == null ? 0 : record.getLevel3sleepeff();
            Long level4sleepeff = record.getLevel4sleepeff() == null ? 0 : record.getLevel4sleepeff();
            Long level5sleepeff = record.getLevel5sleepeff() == null ? 0 : record.getLevel5sleepeff();
            long sumSleepEff = level1sleepeff + level2sleepeff + level3sleepeff + level4sleepeff + level5sleepeff;
            sleepEffAnalysis.setLevel1SleepEff(BigDecimal.valueOf(level1sleepeff).divide(BigDecimal.valueOf(sumSleepEff), 2, RoundingMode.HALF_UP));
            sleepEffAnalysis.setLevel2SleepEff(BigDecimal.valueOf(level2sleepeff).divide(BigDecimal.valueOf(sumSleepEff), 2, RoundingMode.HALF_UP));
            sleepEffAnalysis.setLevel3SleepEff(BigDecimal.valueOf(level3sleepeff).divide(BigDecimal.valueOf(sumSleepEff), 2, RoundingMode.HALF_UP));
            sleepEffAnalysis.setLevel4SleepEff(BigDecimal.valueOf(level4sleepeff).divide(BigDecimal.valueOf(sumSleepEff), 2, RoundingMode.HALF_UP));
            sleepEffAnalysis.setLevel5SleepEff(BigDecimal.valueOf(level5sleepeff).divide(BigDecimal.valueOf(sumSleepEff), 2, RoundingMode.HALF_UP));
        }
        return Result.success(homeSleepRes);
    }
}
