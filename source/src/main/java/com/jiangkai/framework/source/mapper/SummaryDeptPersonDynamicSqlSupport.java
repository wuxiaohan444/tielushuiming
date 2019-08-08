package com.jiangkai.framework.source.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SummaryDeptPersonDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SummaryDeptPerson summaryDeptPerson = new SummaryDeptPerson();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = summaryDeptPerson.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> deptid = summaryDeptPerson.deptid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> date = summaryDeptPerson.date;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> totaldriver = summaryDeptPerson.totaldriver;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> totalactivebed = summaryDeptPerson.totalactivebed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> totalbed = summaryDeptPerson.totalbed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> totalusedriver = summaryDeptPerson.totalusedriver;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> totalusetime = summaryDeptPerson.totalusetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgusetime = summaryDeptPerson.avgusetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgsleept = summaryDeptPerson.avgsleept;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgsleepeff = summaryDeptPerson.avgsleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sleeptroublecnt = summaryDeptPerson.sleeptroublecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> autocallwakecnt = summaryDeptPerson.autocallwakecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> artificalcallwakecnt = summaryDeptPerson.artificalcallwakecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> ratioautocallwake = summaryDeptPerson.ratioautocallwake;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> warncnt = summaryDeptPerson.warncnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgqian = summaryDeptPerson.avgqian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> ratioqian = summaryDeptPerson.ratioqian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgshen = summaryDeptPerson.avgshen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> ratioshen = summaryDeptPerson.ratioshen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> avgsleeptime = summaryDeptPerson.avgsleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> ratiosleeptime = summaryDeptPerson.ratiosleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> lowsleepeffcnt = summaryDeptPerson.lowsleepeffcnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> middlesleepeffcnt = summaryDeptPerson.middlesleepeffcnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> hightsleepeffcnt = summaryDeptPerson.hightsleepeffcnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> lowsleeptimecnt = summaryDeptPerson.lowsleeptimecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> middlesleeptimecnt = summaryDeptPerson.middlesleeptimecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> hightsleeptimecnt = summaryDeptPerson.hightsleeptimecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> level1sleepeff = summaryDeptPerson.level1sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> level2sleepeff = summaryDeptPerson.level2sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> level3sleepeff = summaryDeptPerson.level3sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> level4sleepeff = summaryDeptPerson.level4sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> level5sleepeff = summaryDeptPerson.level5sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sumtotaltime = summaryDeptPerson.sumtotaltime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sumtotalsleept = summaryDeptPerson.sumtotalsleept;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> sumsleepeff = summaryDeptPerson.sumsleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sumqian = summaryDeptPerson.sumqian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sumshen = summaryDeptPerson.sumshen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> sumsleeptime = summaryDeptPerson.sumsleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> mainid = summaryDeptPerson.mainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> unfinishrecords = summaryDeptPerson.unfinishrecords;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> sumtype = summaryDeptPerson.sumtype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = summaryDeptPerson.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> haveedit = summaryDeptPerson.haveedit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class SummaryDeptPerson extends SqlTable {
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

        public SummaryDeptPerson() {
            super("\"summary_dept_person\"");
        }
    }
}