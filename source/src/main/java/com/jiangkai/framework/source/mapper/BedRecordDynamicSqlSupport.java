package com.jiangkai.framework.source.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BedRecordDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final BedRecord bedRecord = new BedRecord();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = bedRecord.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> mainid = bedRecord.mainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> bedSerialno = bedRecord.bedSerialno;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> driverWorkno = bedRecord.driverWorkno;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> checkindate = bedRecord.checkindate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> checkoutdate = bedRecord.checkoutdate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> wakeupdate = bedRecord.wakeupdate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> yunanSid = bedRecord.yunanSid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> driverName = bedRecord.driverName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> finish = bedRecord.finish;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> avgbr = bedRecord.avgbr;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> avghb = bedRecord.avghb;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Double> sleepeff = bedRecord.sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> firstonbeddate = bedRecord.firstonbeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> sleeptime = bedRecord.sleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> lastleavebeddate = bedRecord.lastleavebeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> sleepscore = bedRecord.sleepscore;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> leavecount = bedRecord.leavecount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> autocallwake = bedRecord.autocallwake;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> leavealltime = bedRecord.leavealltime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> maxhb = bedRecord.maxhb;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> minbr = bedRecord.minbr;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> totalsleept = bedRecord.totalsleept;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> totaltime = bedRecord.totaltime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> qian = bedRecord.qian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> shen = bedRecord.shen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> cloudguid = bedRecord.cloudguid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> warn = bedRecord.warn;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> dataSourceId = bedRecord.dataSourceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = bedRecord.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createUser = bedRecord.createUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = bedRecord.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> updateUser = bedRecord.updateUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> status = bedRecord.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> bedInfoId = bedRecord.bedInfoId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> maxbr = bedRecord.maxbr;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> minhb = bedRecord.minhb;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> driverId = bedRecord.driverId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> reportguid = bedRecord.reportguid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> isupload = bedRecord.isupload;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> deptId = bedRecord.deptId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class BedRecord extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

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

        public final SqlColumn<Integer> minbr = column("MinBr", JDBCType.INTEGER);

        public final SqlColumn<Integer> totalsleept = column("TotalsleepT", JDBCType.INTEGER);

        public final SqlColumn<Integer> totaltime = column("TotalTime", JDBCType.INTEGER);

        public final SqlColumn<Integer> qian = column("qian", JDBCType.INTEGER);

        public final SqlColumn<Integer> shen = column("shen", JDBCType.INTEGER);

        public final SqlColumn<String> cloudguid = column("CloudGuid", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> warn = column("Warn", JDBCType.INTEGER);

        public final SqlColumn<Long> dataSourceId = column("data_source_id", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createUser = column("create_user", JDBCType.BIGINT);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updateUser = column("update_user", JDBCType.BIGINT);

        public final SqlColumn<Integer> status = column("\"status\"", JDBCType.INTEGER);

        public final SqlColumn<Long> bedInfoId = column("bed_info_id", JDBCType.BIGINT);

        public final SqlColumn<Integer> maxbr = column("MaxBr", JDBCType.INTEGER);

        public final SqlColumn<Integer> minhb = column("MinHb", JDBCType.INTEGER);

        public final SqlColumn<Long> driverId = column("driver_id", JDBCType.BIGINT);

        public final SqlColumn<String> reportguid = column("ReportGUID", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> isupload = column("IsUpload", JDBCType.INTEGER);

        public final SqlColumn<Long> deptId = column("dept_id", JDBCType.BIGINT);

        public BedRecord() {
            super("\"bed_record\"");
        }
    }
}