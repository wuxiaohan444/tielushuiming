package com.jiangkai.framework.extdata.mapper;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class InfoBedDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final InfoBed infoBed = new InfoBed();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> serialno = infoBed.serialno;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> manu = infoBed.manu;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> model = infoBed.model;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> version = infoBed.version;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> ip = infoBed.ip;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restSite = infoBed.restSite;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restRoom = infoBed.restRoom;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restBed = infoBed.restBed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restBuilding = infoBed.restBuilding;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> restFloor = infoBed.restFloor;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> byBedid = infoBed.byBedid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class InfoBed extends SqlTable {
        public final SqlColumn<String> serialno = column("SerialNo", JDBCType.NVARCHAR);

        public final SqlColumn<String> manu = column("Manu", JDBCType.NVARCHAR);

        public final SqlColumn<String> model = column("Model", JDBCType.NVARCHAR);

        public final SqlColumn<String> version = column("Version", JDBCType.NVARCHAR);

        public final SqlColumn<String> ip = column("IP", JDBCType.NVARCHAR);

        public final SqlColumn<String> restSite = column("Rest_site", JDBCType.NVARCHAR);

        public final SqlColumn<String> restRoom = column("Rest_room", JDBCType.NVARCHAR);

        public final SqlColumn<String> restBed = column("Rest_bed", JDBCType.NVARCHAR);

        public final SqlColumn<String> restBuilding = column("Rest_building", JDBCType.NVARCHAR);

        public final SqlColumn<String> restFloor = column("Rest_floor", JDBCType.NVARCHAR);

        public final SqlColumn<Long> byBedid = column("BY_bedid", JDBCType.BIGINT);

        public InfoBed() {
            super("\"Info_Bed\"");
        }
    }
}