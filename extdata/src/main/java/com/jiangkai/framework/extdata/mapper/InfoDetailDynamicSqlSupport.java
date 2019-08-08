package com.jiangkai.framework.extdata.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class InfoDetailDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final InfoDetail infoDetail = new InfoDetail();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> recordNo = infoDetail.recordNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> interfaceMainid = infoDetail.interfaceMainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> state = infoDetail.state;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> heartbeat = infoDetail.heartbeat;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> breath = infoDetail.breath;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> wet = infoDetail.wet;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> weight = infoDetail.weight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> position = infoDetail.position;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> od = infoDetail.od;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatetime = infoDetail.updatetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> sleepstatus = infoDetail.sleepstatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class InfoDetail extends SqlTable {
        public final SqlColumn<Integer> recordNo = column("Record_No", JDBCType.INTEGER);

        public final SqlColumn<Integer> interfaceMainid = column("Interface_MainID", JDBCType.INTEGER);

        public final SqlColumn<String> state = column("\"State\"", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> heartbeat = column("HeartBeat", JDBCType.INTEGER);

        public final SqlColumn<Integer> breath = column("Breath", JDBCType.INTEGER);

        public final SqlColumn<String> wet = column("Wet", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> weight = column("Weight", JDBCType.INTEGER);

        public final SqlColumn<String> position = column("\"Position\"", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> od = column("Od", JDBCType.INTEGER);

        public final SqlColumn<Date> updatetime = column("UpDatetime", JDBCType.TIMESTAMP);

        public final SqlColumn<String> sleepstatus = column("SleepStatus", JDBCType.NVARCHAR);

        public InfoDetail() {
            super("\"Info_Detail\"");
        }
    }
}