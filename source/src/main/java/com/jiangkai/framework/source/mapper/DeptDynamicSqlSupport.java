package com.jiangkai.framework.source.mapper;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DeptDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Dept dept = new Dept();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = dept.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> superiorId = dept.superiorId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> path = dept.path;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> depttype = dept.depttype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> no = dept.no;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> name = dept.name;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = dept.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createUser = dept.createUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = dept.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> updateUser = dept.updateUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> status = dept.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> longitude = dept.longitude;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<BigDecimal> latitude = dept.latitude;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> level = dept.level;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Dept extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> superiorId = column("superior_id", JDBCType.BIGINT);

        public final SqlColumn<String> path = column("\"path\"", JDBCType.VARCHAR);

        public final SqlColumn<Long> depttype = column("deptType", JDBCType.BIGINT);

        public final SqlColumn<String> no = column("\"no\"", JDBCType.NVARCHAR);

        public final SqlColumn<String> name = column("\"name\"", JDBCType.NVARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createUser = column("create_user", JDBCType.BIGINT);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updateUser = column("update_user", JDBCType.BIGINT);

        public final SqlColumn<Integer> status = column("\"status\"", JDBCType.INTEGER);

        public final SqlColumn<BigDecimal> longitude = column("longitude", JDBCType.DECIMAL);

        public final SqlColumn<BigDecimal> latitude = column("latitude", JDBCType.DECIMAL);

        public final SqlColumn<Integer> level = column("\"level\"", JDBCType.INTEGER);

        public Dept() {
            super("\"dept\"");
        }
    }
}