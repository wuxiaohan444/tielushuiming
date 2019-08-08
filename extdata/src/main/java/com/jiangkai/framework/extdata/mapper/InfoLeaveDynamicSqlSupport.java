package com.jiangkai.framework.extdata.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class InfoLeaveDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final InfoLeave infoLeave = new InfoLeave();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = infoLeave.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> mainid = infoLeave.mainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> onbeddate = infoLeave.onbeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> leavebeddate = infoLeave.leavebeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> leavetime = infoLeave.leavetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class InfoLeave extends SqlTable {
        public final SqlColumn<Integer> id = column("ID", JDBCType.INTEGER);

        public final SqlColumn<Integer> mainid = column("MainId", JDBCType.INTEGER);

        public final SqlColumn<Date> onbeddate = column("OnBedDate", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> leavebeddate = column("LeaveBedDate", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> leavetime = column("LeaveTime", JDBCType.TIME);

        public InfoLeave() {
            super("\"Info_Leave\"");
        }
    }
}