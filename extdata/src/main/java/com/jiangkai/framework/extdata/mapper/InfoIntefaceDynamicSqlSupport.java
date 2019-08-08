package com.jiangkai.framework.extdata.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class InfoIntefaceDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final InfoInteface infoInteface = new InfoInteface();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> mainid = infoInteface.mainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> bedSerialno = infoInteface.bedSerialno;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> driverWorkno = infoInteface.driverWorkno;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> checkindate = infoInteface.checkindate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> checkoutdate = infoInteface.checkoutdate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> wakeupdate = infoInteface.wakeupdate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> yunanSid = infoInteface.yunanSid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> driverName = infoInteface.driverName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> finish = infoInteface.finish;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> avgbr = infoInteface.avgbr;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> avghb = infoInteface.avghb;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Double> sleepeff = infoInteface.sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> firstonbeddate = infoInteface.firstonbeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> sleeptime = infoInteface.sleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> lastleavebeddate = infoInteface.lastleavebeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> sleepscore = infoInteface.sleepscore;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> leavecount = infoInteface.leavecount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> autocallwake = infoInteface.autocallwake;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> leavealltime = infoInteface.leavealltime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> maxhb = infoInteface.maxhb;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> maxbr = infoInteface.maxbr;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> minhb = infoInteface.minhb;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> minbr = infoInteface.minbr;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> totalsleept = infoInteface.totalsleept;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> totaltime = infoInteface.totaltime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> qian = infoInteface.qian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> shen = infoInteface.shen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cloudguid = infoInteface.cloudguid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> warn = infoInteface.warn;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> reportguid = infoInteface.reportguid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> isupload = infoInteface.isupload;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class InfoInteface extends SqlTable {
        public final SqlColumn<Integer> mainid = column("MainID", JDBCType.INTEGER);

        public final SqlColumn<String> bedSerialno = column("Bed_SerialNo", JDBCType.NVARCHAR);

        public final SqlColumn<String> driverWorkno = column("Driver_WorkNo", JDBCType.NVARCHAR);

        public final SqlColumn<Date> checkindate = column("CheckInDate", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> checkoutdate = column("CheckOutDate", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> wakeupdate = column("WakeUpDate", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> yunanSid = column("YunAn_sid", JDBCType.INTEGER);

        public final SqlColumn<String> driverName = column("Driver_Name", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> finish = column("Finish", JDBCType.INTEGER);

        public final SqlColumn<Integer> avgbr = column("AvgBr", JDBCType.INTEGER);

        public final SqlColumn<Integer> avghb = column("AvgHb", JDBCType.INTEGER);

        public final SqlColumn<Double> sleepeff = column("SleepEff", JDBCType.DOUBLE);

        public final SqlColumn<Date> firstonbeddate = column("FirstOnbedDate", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> sleeptime = column("SleepTime", JDBCType.TIME);

        public final SqlColumn<Date> lastleavebeddate = column("LastLeaveBedDate", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> sleepscore = column("SleepScore", JDBCType.INTEGER);

        public final SqlColumn<Integer> leavecount = column("LeaveCount", JDBCType.INTEGER);

        public final SqlColumn<Integer> autocallwake = column("AutoCallWake", JDBCType.INTEGER);

        public final SqlColumn<Date> leavealltime = column("LeaveAllTime", JDBCType.TIME);

        public final SqlColumn<Integer> maxhb = column("MaxHb", JDBCType.INTEGER);

        public final SqlColumn<Integer> maxbr = column("MaxBr", JDBCType.INTEGER);

        public final SqlColumn<Integer> minhb = column("MinHb", JDBCType.INTEGER);

        public final SqlColumn<Integer> minbr = column("MinBr", JDBCType.INTEGER);

        public final SqlColumn<Integer> totalsleept = column("TotalsleepT", JDBCType.INTEGER);

        public final SqlColumn<Integer> totaltime = column("TotalTime", JDBCType.INTEGER);

        public final SqlColumn<Integer> qian = column("qian", JDBCType.INTEGER);

        public final SqlColumn<Integer> shen = column("shen", JDBCType.INTEGER);

        public final SqlColumn<String> cloudguid = column("CloudGuid", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> warn = column("Warn", JDBCType.INTEGER);

        public final SqlColumn<String> reportguid = column("ReportGUID", JDBCType.CHAR);

        public final SqlColumn<Integer> isupload = column("IsUpload", JDBCType.INTEGER);

        public InfoInteface() {
            super("\"Info_Inteface\"");
        }
    }
}