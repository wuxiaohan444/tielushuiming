package com.jiangkai.framework.source.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class BedInfoDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final BedInfo bedInfo = new BedInfo();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = bedInfo.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> manu = bedInfo.manu;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> model = bedInfo.model;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> version = bedInfo.version;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> serialno = bedInfo.serialno;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> ip = bedInfo.ip;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restSite = bedInfo.restSite;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restRoom = bedInfo.restRoom;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restBed = bedInfo.restBed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restBuilding = bedInfo.restBuilding;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restFloor = bedInfo.restFloor;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> byBedid = bedInfo.byBedid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> dataSourceId = bedInfo.dataSourceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createUser = bedInfo.createUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = bedInfo.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> updateUser = bedInfo.updateUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = bedInfo.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> status = bedInfo.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> deptId = bedInfo.deptId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class BedInfo extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<String> manu = column("Manu", JDBCType.NVARCHAR);

        public final SqlColumn<String> model = column("Model", JDBCType.NVARCHAR);

        public final SqlColumn<String> version = column("Version", JDBCType.NVARCHAR);

        public final SqlColumn<String> serialno = column("SerialNo", JDBCType.NVARCHAR);

        public final SqlColumn<String> ip = column("IP", JDBCType.NVARCHAR);

        public final SqlColumn<String> restSite = column("Rest_site", JDBCType.NVARCHAR);

        public final SqlColumn<String> restRoom = column("Rest_room", JDBCType.NVARCHAR);

        public final SqlColumn<String> restBed = column("Rest_bed", JDBCType.NVARCHAR);

        public final SqlColumn<String> restBuilding = column("Rest_building", JDBCType.NVARCHAR);

        public final SqlColumn<String> restFloor = column("Rest_floor", JDBCType.NVARCHAR);

        public final SqlColumn<Long> byBedid = column("BY_bedid", JDBCType.BIGINT);

        public final SqlColumn<Long> dataSourceId = column("data_source_id", JDBCType.BIGINT);

        public final SqlColumn<Long> createUser = column("create_user", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updateUser = column("update_user", JDBCType.BIGINT);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> status = column("\"status\"", JDBCType.INTEGER);

        public final SqlColumn<Long> deptId = column("dept_id", JDBCType.BIGINT);

        public BedInfo() {
            super("\"bed_info\"");
        }
    }
}