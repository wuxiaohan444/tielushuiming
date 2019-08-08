package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.BaseDataDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.BaseData;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

@Mapper
public interface BaseDataMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<BaseData> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BaseDataResult")
    BaseData selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BaseDataResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="value", property="value", jdbcType=JdbcType.INTEGER),
        @Result(column="sleep_status", property="sleepStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="monitor_dim", property="monitorDim", jdbcType=JdbcType.VARCHAR),
        @Result(column="sleep_advise", property="sleepAdvise", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<BaseData> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(baseData);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, baseData);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, baseData)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(BaseData record) {
        return insert(SqlBuilder.insert(record)
                .into(baseData)
                .map(value).toProperty("value")
                .map(sleepStatus).toProperty("sleepStatus")
                .map(monitorDim).toProperty("monitorDim")
                .map(sleepAdvise).toProperty("sleepAdvise")
                .map(createUser).toProperty("createUser")
                .map(createTime).toProperty("createTime")
                .map(updateUser).toProperty("updateUser")
                .map(updateTime).toProperty("updateTime")
                .map(status).toProperty("status")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(BaseData record) {
        return insert(SqlBuilder.insert(record)
                .into(baseData)
                .map(value).toPropertyWhenPresent("value", record::getValue)
                .map(sleepStatus).toPropertyWhenPresent("sleepStatus", record::getSleepStatus)
                .map(monitorDim).toPropertyWhenPresent("monitorDim", record::getMonitorDim)
                .map(sleepAdvise).toPropertyWhenPresent("sleepAdvise", record::getSleepAdvise)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BaseData>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, value, sleepStatus, monitorDim, sleepAdvise, createUser, createTime, updateUser, updateTime, status)
                .from(baseData);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BaseData>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, value, sleepStatus, monitorDim, sleepAdvise, createUser, createTime, updateUser, updateTime, status)
                .from(baseData);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default BaseData selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, value, sleepStatus, monitorDim, sleepAdvise, createUser, createTime, updateUser, updateTime, status)
                .from(baseData)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(BaseData record) {
        return UpdateDSL.updateWithMapper(this::update, baseData)
                .set(value).equalTo(record::getValue)
                .set(sleepStatus).equalTo(record::getSleepStatus)
                .set(monitorDim).equalTo(record::getMonitorDim)
                .set(sleepAdvise).equalTo(record::getSleepAdvise)
                .set(createUser).equalTo(record::getCreateUser)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(status).equalTo(record::getStatus);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(BaseData record) {
        return UpdateDSL.updateWithMapper(this::update, baseData)
                .set(value).equalToWhenPresent(record::getValue)
                .set(sleepStatus).equalToWhenPresent(record::getSleepStatus)
                .set(monitorDim).equalToWhenPresent(record::getMonitorDim)
                .set(sleepAdvise).equalToWhenPresent(record::getSleepAdvise)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(status).equalToWhenPresent(record::getStatus);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(BaseData record) {
        return UpdateDSL.updateWithMapper(this::update, baseData)
                .set(value).equalTo(record::getValue)
                .set(sleepStatus).equalTo(record::getSleepStatus)
                .set(monitorDim).equalTo(record::getMonitorDim)
                .set(sleepAdvise).equalTo(record::getSleepAdvise)
                .set(createUser).equalTo(record::getCreateUser)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(status).equalTo(record::getStatus)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(BaseData record) {
        return UpdateDSL.updateWithMapper(this::update, baseData)
                .set(value).equalToWhenPresent(record::getValue)
                .set(sleepStatus).equalToWhenPresent(record::getSleepStatus)
                .set(monitorDim).equalToWhenPresent(record::getMonitorDim)
                .set(sleepAdvise).equalToWhenPresent(record::getSleepAdvise)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(status).equalToWhenPresent(record::getStatus)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}