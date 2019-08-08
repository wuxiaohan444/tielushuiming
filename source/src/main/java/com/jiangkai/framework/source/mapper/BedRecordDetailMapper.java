package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.BedRecordDetailDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.BedRecordDetail;
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
public interface BedRecordDetailMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<BedRecordDetail> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BedRecordDetailResult")
    BedRecordDetail selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BedRecordDetailResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="Interface_MainID", property="interfaceMainid", jdbcType=JdbcType.INTEGER),
        @Result(column="Record_No", property="recordNo", jdbcType=JdbcType.INTEGER),
        @Result(column="State", property="state", jdbcType=JdbcType.NVARCHAR),
        @Result(column="HeartBeat", property="heartbeat", jdbcType=JdbcType.INTEGER),
        @Result(column="Breath", property="breath", jdbcType=JdbcType.INTEGER),
        @Result(column="Wet", property="wet", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Weight", property="weight", jdbcType=JdbcType.INTEGER),
        @Result(column="Position", property="position", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Od", property="od", jdbcType=JdbcType.INTEGER),
        @Result(column="UpDateTime", property="updatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SleepStatus", property="sleepstatus", jdbcType=JdbcType.NVARCHAR),
        @Result(column="data_source_id", property="dataSourceId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="bed_record_id", property="bedRecordId", jdbcType=JdbcType.BIGINT)
    })
    List<BedRecordDetail> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(bedRecordDetail);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, bedRecordDetail);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, bedRecordDetail)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(BedRecordDetail record) {
        return insert(SqlBuilder.insert(record)
                .into(bedRecordDetail)
                .map(interfaceMainid).toProperty("interfaceMainid")
                .map(recordNo).toProperty("recordNo")
                .map(state).toProperty("state")
                .map(heartbeat).toProperty("heartbeat")
                .map(breath).toProperty("breath")
                .map(wet).toProperty("wet")
                .map(weight).toProperty("weight")
                .map(position).toProperty("position")
                .map(od).toProperty("od")
                .map(updatetime).toProperty("updatetime")
                .map(sleepstatus).toProperty("sleepstatus")
                .map(dataSourceId).toProperty("dataSourceId")
                .map(createTime).toProperty("createTime")
                .map(createUser).toProperty("createUser")
                .map(updateUser).toProperty("updateUser")
                .map(status).toProperty("status")
                .map(bedRecordId).toProperty("bedRecordId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(BedRecordDetail record) {
        return insert(SqlBuilder.insert(record)
                .into(bedRecordDetail)
                .map(interfaceMainid).toPropertyWhenPresent("interfaceMainid", record::getInterfaceMainid)
                .map(recordNo).toPropertyWhenPresent("recordNo", record::getRecordNo)
                .map(state).toPropertyWhenPresent("state", record::getState)
                .map(heartbeat).toPropertyWhenPresent("heartbeat", record::getHeartbeat)
                .map(breath).toPropertyWhenPresent("breath", record::getBreath)
                .map(wet).toPropertyWhenPresent("wet", record::getWet)
                .map(weight).toPropertyWhenPresent("weight", record::getWeight)
                .map(position).toPropertyWhenPresent("position", record::getPosition)
                .map(od).toPropertyWhenPresent("od", record::getOd)
                .map(updatetime).toPropertyWhenPresent("updatetime", record::getUpdatetime)
                .map(sleepstatus).toPropertyWhenPresent("sleepstatus", record::getSleepstatus)
                .map(dataSourceId).toPropertyWhenPresent("dataSourceId", record::getDataSourceId)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(bedRecordId).toPropertyWhenPresent("bedRecordId", record::getBedRecordId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecordDetail>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, interfaceMainid, recordNo, state, heartbeat, breath, wet, weight, position, od, updatetime, sleepstatus, dataSourceId, createTime, createUser, updateUser, status, bedRecordId)
                .from(bedRecordDetail);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecordDetail>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, interfaceMainid, recordNo, state, heartbeat, breath, wet, weight, position, od, updatetime, sleepstatus, dataSourceId, createTime, createUser, updateUser, status, bedRecordId)
                .from(bedRecordDetail);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default BedRecordDetail selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, interfaceMainid, recordNo, state, heartbeat, breath, wet, weight, position, od, updatetime, sleepstatus, dataSourceId, createTime, createUser, updateUser, status, bedRecordId)
                .from(bedRecordDetail)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(BedRecordDetail record) {
        return UpdateDSL.updateWithMapper(this::update, bedRecordDetail)
                .set(interfaceMainid).equalTo(record::getInterfaceMainid)
                .set(recordNo).equalTo(record::getRecordNo)
                .set(state).equalTo(record::getState)
                .set(heartbeat).equalTo(record::getHeartbeat)
                .set(breath).equalTo(record::getBreath)
                .set(wet).equalTo(record::getWet)
                .set(weight).equalTo(record::getWeight)
                .set(position).equalTo(record::getPosition)
                .set(od).equalTo(record::getOd)
                .set(updatetime).equalTo(record::getUpdatetime)
                .set(sleepstatus).equalTo(record::getSleepstatus)
                .set(dataSourceId).equalTo(record::getDataSourceId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(bedRecordId).equalTo(record::getBedRecordId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(BedRecordDetail record) {
        return UpdateDSL.updateWithMapper(this::update, bedRecordDetail)
                .set(interfaceMainid).equalToWhenPresent(record::getInterfaceMainid)
                .set(recordNo).equalToWhenPresent(record::getRecordNo)
                .set(state).equalToWhenPresent(record::getState)
                .set(heartbeat).equalToWhenPresent(record::getHeartbeat)
                .set(breath).equalToWhenPresent(record::getBreath)
                .set(wet).equalToWhenPresent(record::getWet)
                .set(weight).equalToWhenPresent(record::getWeight)
                .set(position).equalToWhenPresent(record::getPosition)
                .set(od).equalToWhenPresent(record::getOd)
                .set(updatetime).equalToWhenPresent(record::getUpdatetime)
                .set(sleepstatus).equalToWhenPresent(record::getSleepstatus)
                .set(dataSourceId).equalToWhenPresent(record::getDataSourceId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(bedRecordId).equalToWhenPresent(record::getBedRecordId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(BedRecordDetail record) {
        return UpdateDSL.updateWithMapper(this::update, bedRecordDetail)
                .set(interfaceMainid).equalTo(record::getInterfaceMainid)
                .set(recordNo).equalTo(record::getRecordNo)
                .set(state).equalTo(record::getState)
                .set(heartbeat).equalTo(record::getHeartbeat)
                .set(breath).equalTo(record::getBreath)
                .set(wet).equalTo(record::getWet)
                .set(weight).equalTo(record::getWeight)
                .set(position).equalTo(record::getPosition)
                .set(od).equalTo(record::getOd)
                .set(updatetime).equalTo(record::getUpdatetime)
                .set(sleepstatus).equalTo(record::getSleepstatus)
                .set(dataSourceId).equalTo(record::getDataSourceId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(bedRecordId).equalTo(record::getBedRecordId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(BedRecordDetail record) {
        return UpdateDSL.updateWithMapper(this::update, bedRecordDetail)
                .set(interfaceMainid).equalToWhenPresent(record::getInterfaceMainid)
                .set(recordNo).equalToWhenPresent(record::getRecordNo)
                .set(state).equalToWhenPresent(record::getState)
                .set(heartbeat).equalToWhenPresent(record::getHeartbeat)
                .set(breath).equalToWhenPresent(record::getBreath)
                .set(wet).equalToWhenPresent(record::getWet)
                .set(weight).equalToWhenPresent(record::getWeight)
                .set(position).equalToWhenPresent(record::getPosition)
                .set(od).equalToWhenPresent(record::getOd)
                .set(updatetime).equalToWhenPresent(record::getUpdatetime)
                .set(sleepstatus).equalToWhenPresent(record::getSleepstatus)
                .set(dataSourceId).equalToWhenPresent(record::getDataSourceId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(bedRecordId).equalToWhenPresent(record::getBedRecordId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}