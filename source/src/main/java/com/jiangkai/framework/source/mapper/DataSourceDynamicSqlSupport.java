package com.jiangkai.framework.source.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DataSourceDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final DataSource dataSource = new DataSource();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> id = dataSource.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> deptId = dataSource.deptId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> nickname = dataSource.nickname;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> ip = dataSource.ip;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> port = dataSource.port;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> username = dataSource.username;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> password = dataSource.password;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = dataSource.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createUser = dataSource.createUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = dataSource.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> updateUser = dataSource.updateUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> status = dataSource.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> path = dataSource.path;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> databaseName = dataSource.databaseName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> lastErrorTime = dataSource.lastErrorTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> errorMsg = dataSource.errorMsg;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class DataSource extends SqlTable {
        public final SqlColumn<Long> id = column("id", JDBCType.BIGINT);

        public final SqlColumn<Long> deptId = column("dept_id", JDBCType.BIGINT);

        public final SqlColumn<String> nickname = column("nickname", JDBCType.NVARCHAR);

        public final SqlColumn<String> ip = column("ip", JDBCType.NVARCHAR);

        public final SqlColumn<Integer> port = column("port", JDBCType.INTEGER);

        public final SqlColumn<String> username = column("username", JDBCType.NVARCHAR);

        public final SqlColumn<String> password = column("\"password\"", JDBCType.NVARCHAR);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createUser = column("create_user", JDBCType.BIGINT);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updateUser = column("update_user", JDBCType.BIGINT);

        public final SqlColumn<Integer> status = column("\"status\"", JDBCType.INTEGER);

        public final SqlColumn<String> path = column("\"path\"", JDBCType.VARCHAR);

        public final SqlColumn<String> databaseName = column("database_name", JDBCType.VARCHAR);

        public final SqlColumn<Date> lastErrorTime = column("last_error_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> errorMsg = column("error_msg", JDBCType.VARCHAR);

        public DataSource() {
            super("\"data_source\"");
        }
    }
}