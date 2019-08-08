package com.jiangkai.framework.source.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SummaryDeptBedDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SummaryDeptBed summaryDeptBed = new SummaryDeptBed();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = summaryDeptBed.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> deptid = summaryDeptBed.deptid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> date = summaryDeptBed.date;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> totaldriver = summaryDeptBed.totaldriver;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> totalactivebed = summaryDeptBed.totalactivebed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> totalbed = summaryDeptBed.totalbed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> totalusedriver = summaryDeptBed.totalusedriver;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> totalusetime = summaryDeptBed.totalusetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgusetime = summaryDeptBed.avgusetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgsleept = summaryDeptBed.avgsleept;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgsleepeff = summaryDeptBed.avgsleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sleeptroublecnt = summaryDeptBed.sleeptroublecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> autocallwakecnt = summaryDeptBed.autocallwakecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> artificalcallwakecnt = summaryDeptBed.artificalcallwakecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> ratioautocallwake = summaryDeptBed.ratioautocallwake;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> warncnt = summaryDeptBed.warncnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgqian = summaryDeptBed.avgqian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> ratioqian = summaryDeptBed.ratioqian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgshen = summaryDeptBed.avgshen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> ratioshen = summaryDeptBed.ratioshen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgsleeptime = summaryDeptBed.avgsleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> ratiosleeptime = summaryDeptBed.ratiosleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> lowsleepeffcnt = summaryDeptBed.lowsleepeffcnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> middlesleepeffcnt = summaryDeptBed.middlesleepeffcnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> hightsleepeffcnt = summaryDeptBed.hightsleepeffcnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> lowsleeptimecnt = summaryDeptBed.lowsleeptimecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> middlesleeptimecnt = summaryDeptBed.middlesleeptimecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> hightsleeptimecnt = summaryDeptBed.hightsleeptimecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> level1sleepeff = summaryDeptBed.level1sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> level2sleepeff = summaryDeptBed.level2sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> level3sleepeff = summaryDeptBed.level3sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> level4sleepeff = summaryDeptBed.level4sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> level5sleepeff = summaryDeptBed.level5sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sumtotaltime = summaryDeptBed.sumtotaltime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sumtotalsleept = summaryDeptBed.sumtotalsleept;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> sumsleepeff = summaryDeptBed.sumsleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sumqian = summaryDeptBed.sumqian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sumshen = summaryDeptBed.sumshen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sumsleeptime = summaryDeptBed.sumsleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> mainid = summaryDeptBed.mainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> unfinishrecords = summaryDeptBed.unfinishrecords;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> sumtype = summaryDeptBed.sumtype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = summaryDeptBed.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> haveedit = summaryDeptBed.haveedit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class SummaryDeptBed extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> deptid = column("deptid", JDBCType.BIGINT);

        public final SqlColumn<Date> date = column("\"date\"", JDBCType.DATE);

        public final SqlColumn<Long> totaldriver = column("totalDriver", JDBCType.BIGINT);

        public final SqlColumn<Long> totalactivebed = column("totalActiveBed", JDBCType.BIGINT);

        public final SqlColumn<Long> totalbed = column("totalBed", JDBCType.BIGINT);

        public final SqlColumn<Long> totalusedriver = column("totalUseDriver", JDBCType.BIGINT);

        public final SqlColumn<Long> totalusetime = column("totalUseTime", JDBCType.BIGINT);

        public final SqlColumn<BigDecimal> avgusetime = column("avgUseTime", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> avgsleept = column("avgSleepT", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> avgsleepeff = column("avgSleepEff", JDBCType.DECIMAL);

        public final SqlColumn<Long> sleeptroublecnt = column("sleepTroubleCnt", JDBCType.BIGINT);

        public final SqlColumn<Long> autocallwakecnt = column("autoCallWakeCnt", JDBCType.BIGINT);

        public final SqlColumn<Long> artificalcallwakecnt = column("artificalCallWakeCnt", JDBCType.BIGINT);

        public final SqlColumn<BigDecimal> ratioautocallwake = column("ratioAutoCallWake", JDBCType.DECIMAL);

        public final SqlColumn<Long> warncnt = column("warnCnt", JDBCType.BIGINT);

        public final SqlColumn<BigDecimal> avgqian = column("avgQian", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> ratioqian = column("ratioQian", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> avgshen = column("avgShen", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> ratioshen = column("ratioShen", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> avgsleeptime = column("avgSleepTime", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> ratiosleeptime = column("ratioSleepTime", JDBCType.DECIMAL);

        public final SqlColumn<Long> lowsleepeffcnt = column("lowSleepEffCnt", JDBCType.BIGINT);

        public final SqlColumn<Long> middlesleepeffcnt = column("middleSleepEffCnt", JDBCType.BIGINT);

        public final SqlColumn<Long> hightsleepeffcnt = column("hightSleepEffCnt", JDBCType.BIGINT);

        public final SqlColumn<Long> lowsleeptimecnt = column("lowSleepTimeCnt", JDBCType.BIGINT);

        public final SqlColumn<Long> middlesleeptimecnt = column("middleSleepTimeCnt", JDBCType.BIGINT);

        public final SqlColumn<Long> hightsleeptimecnt = column("hightSleepTimeCnt", JDBCType.BIGINT);

        public final SqlColumn<Long> level1sleepeff = column("level1SleepEff", JDBCType.BIGINT);

        public final SqlColumn<Long> level2sleepeff = column("level2SleepEff", JDBCType.BIGINT);

        public final SqlColumn<Long> level3sleepeff = column("level3SleepEff", JDBCType.BIGINT);

        public final SqlColumn<Long> level4sleepeff = column("level4SleepEff", JDBCType.BIGINT);

        public final SqlColumn<Long> level5sleepeff = column("level5SleepEff", JDBCType.BIGINT);

        public final SqlColumn<Long> sumtotaltime = column("SumTotalTime", JDBCType.BIGINT);

        public final SqlColumn<Long> sumtotalsleept = column("SumTotalSleepT", JDBCType.BIGINT);

        public final SqlColumn<BigDecimal> sumsleepeff = column("SumSleepEff", JDBCType.DECIMAL);

        public final SqlColumn<Long> sumqian = column("SumQian", JDBCType.BIGINT);

        public final SqlColumn<Long> sumshen = column("SumShen", JDBCType.BIGINT);

        public final SqlColumn<Long> sumsleeptime = column("SumSleepTime", JDBCType.BIGINT);

        public final SqlColumn<Integer> mainid = column("MainID", JDBCType.INTEGER);

        public final SqlColumn<String> unfinishrecords = column("UnFinishRecords", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> sumtype = column("SumType", JDBCType.INTEGER);

        public final SqlColumn<Date> updateTime = column("Update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> haveedit = column("HaveEdit", JDBCType.INTEGER);

        public SummaryDeptBed() {
            super("\"summary_dept_bed\"");
        }
    }
}