package com.jiangkai.framework.source.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class LeaveBedInfoDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final LeaveBedInfo leaveBedInfo = new LeaveBedInfo();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> key = leaveBedInfo.key;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> mainid = leaveBedInfo.mainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> onbeddate = leaveBedInfo.onbeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> leavebeddate = leaveBedInfo.leavebeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = leaveBedInfo.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> leavetime = leaveBedInfo.leavetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> dataSourceId = leaveBedInfo.dataSourceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createTime = leaveBedInfo.createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> createUser = leaveBedInfo.createUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updateTime = leaveBedInfo.updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> updateUser = leaveBedInfo.updateUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> status = leaveBedInfo.status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> bedInfoId = leaveBedInfo.bedInfoId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> file = leaveBedInfo.file;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> fileType = leaveBedInfo.fileType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> deptId = leaveBedInfo.deptId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class LeaveBedInfo extends SqlTable {
        public final SqlColumn<Long> key = column("\"key\"", JDBCType.BIGINT);

        public final SqlColumn<Integer> mainid = column("MainId", JDBCType.INTEGER);

        public final SqlColumn<Date> onbeddate = column("OnBedDate", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> leavebeddate = column("LeaveBedDate", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> id = column("ID", JDBCType.INTEGER);

        public final SqlColumn<Date> leavetime = column("LeaveTime", JDBCType.TIME);

        public final SqlColumn<Long> dataSourceId = column("data_source_id", JDBCType.BIGINT);

        public final SqlColumn<Date> createTime = column("create_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> createUser = column("create_user", JDBCType.BIGINT);

        public final SqlColumn<Date> updateTime = column("update_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Long> updateUser = column("update_user", JDBCType.BIGINT);

        public final SqlColumn<Integer> status = column("\"status\"", JDBCType.INTEGER);

        public final SqlColumn<Long> bedInfoId = column("bed_info_id", JDBCType.BIGINT);

        public final SqlColumn<String> file = column("\"file\"", JDBCType.VARCHAR);

        public final SqlColumn<Integer> fileType = column("file_type", JDBCType.INTEGER);

        public final SqlColumn<Long> deptId = column("dept_id", JDBCType.BIGINT);

        public LeaveBedInfo() {
            super("\"leave_bed_info\"");
        }
    }
}