package com.jiangkai.framework.source.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BedRecordDetailDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final BedRecordDetail bedRecordDetail = new BedRecordDetail();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = bedRecordDetail.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> interfaceMainid = bedRecordDetail.interfaceMainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> recordNo = bedRecordDetail.recordNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> state = bedRecordDetail.state;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> heartbeat = bedRecordDetail.heartbeat;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> breath = bedRecordDetail.breath;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> wet = bedRecordDetail.wet;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> weight = bedRecordDetail.weight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> position = bedRecordDetail.position;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> od = bedRecordDetail.od;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatetime = bedRecordDetail.updatetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> sleepstatus = bedRecordDetail.sleepstatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> dataSourceId = bedRecordDetail.dataSourceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = bedRecordDetail.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createUser = bedRecordDetail.createUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> updateUser = bedRecordDetail.updateUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> status = bedRecordDetail.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> bedRecordId = bedRecordDetail.bedRecordId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class BedRecordDetail extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Integer> interfaceMainid = column("Interface_MainID", JDBCType.INTEGER);

        public final SqlColumn<Integer> recordNo = column("Record_No", JDBCType.INTEGER);

        public final SqlColumn<String> state = column("\"State\"", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> heartbeat = column("HeartBeat", JDBCType.INTEGER);

        public final SqlColumn<Integer> breath = column("Breath", JDBCType.INTEGER);

        public final SqlColumn<String> wet = column("Wet", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> weight = column("Weight", JDBCType.INTEGER);

        public final SqlColumn<String> position = column("\"Position\"", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> od = column("Od", JDBCType.INTEGER);

        public final SqlColumn<Date> updatetime = column("UpDateTime", JDBCType.TIMESTAMP);

        public final SqlColumn<String> sleepstatus = column("SleepStatus", JDBCType.NVARCHAR);

        public final SqlColumn<Long> dataSourceId = column("data_source_id", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createUser = column("create_user", JDBCType.BIGINT);

        public final SqlColumn<Long> updateUser = column("update_user", JDBCType.BIGINT);

        public final SqlColumn<Integer> status = column("\"status\"", JDBCType.INTEGER);

        public final SqlColumn<Long> bedRecordId = column("bed_record_id", JDBCType.BIGINT);

        public BedRecordDetail() {
            super("\"bed_record_detail\"");
        }
    }
}