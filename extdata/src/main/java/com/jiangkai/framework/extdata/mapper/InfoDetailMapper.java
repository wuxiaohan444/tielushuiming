package com.jiangkai.framework.extdata.mapper;

import static com.jiangkai.framework.extdata.mapper.InfoDetailDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.extdata.model.InfoDetail;
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
public interface InfoDetailMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.recordNo")
    int insert(InsertStatementProvider<InfoDetail> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("InfoDetailResult")
    InfoDetail selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="InfoDetailResult", value = {
        @Result(column="Record_No", property="recordNo", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Interface_MainID", property="interfaceMainid", jdbcType=JdbcType.INTEGER),
        @Result(column="State", property="state", jdbcType=JdbcType.NVARCHAR),
        @Result(column="HeartBeat", property="heartbeat", jdbcType=JdbcType.INTEGER),
        @Result(column="Breath", property="breath", jdbcType=JdbcType.INTEGER),
        @Result(column="Wet", property="wet", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Weight", property="weight", jdbcType=JdbcType.INTEGER),
        @Result(column="Position", property="position", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Od", property="od", jdbcType=JdbcType.INTEGER),
        @Result(column="UpDatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SleepStatus", property="sleepstatus", jdbcType=JdbcType.NVARCHAR)
    })
    List<InfoDetail> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(infoDetail);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, infoDetail);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer recordNo_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, infoDetail)
                .where(recordNo, isEqualTo(recordNo_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(InfoDetail record) {
        return insert(SqlBuilder.insert(record)
                .into(infoDetail)
                .map(interfaceMainid).toProperty("interfaceMainid")
                .map(state).toProperty("state")
                .map(heartbeat).toProperty("heartbeat")
                .map(breath).toProperty("breath")
                .map(wet).toProperty("wet")
                .map(weight).toProperty("weight")
                .map(position).toProperty("position")
                .map(od).toProperty("od")
                .map(updatetime).toProperty("updatetime")
                .map(sleepstatus).toProperty("sleepstatus")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(InfoDetail record) {
        return insert(SqlBuilder.insert(record)
                .into(infoDetail)
                .map(interfaceMainid).toPropertyWhenPresent("interfaceMainid", record::getInterfaceMainid)
                .map(state).toPropertyWhenPresent("state", record::getState)
                .map(heartbeat).toPropertyWhenPresent("heartbeat", record::getHeartbeat)
                .map(breath).toPropertyWhenPresent("breath", record::getBreath)
                .map(wet).toPropertyWhenPresent("wet", record::getWet)
                .map(weight).toPropertyWhenPresent("weight", record::getWeight)
                .map(position).toPropertyWhenPresent("position", record::getPosition)
                .map(od).toPropertyWhenPresent("od", record::getOd)
                .map(updatetime).toPropertyWhenPresent("updatetime", record::getUpdatetime)
                .map(sleepstatus).toPropertyWhenPresent("sleepstatus", record::getSleepstatus)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoDetail>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, recordNo, interfaceMainid, state, heartbeat, breath, wet, weight, position, od, updatetime, sleepstatus)
                .from(infoDetail);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoDetail>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, recordNo, interfaceMainid, state, heartbeat, breath, wet, weight, position, od, updatetime, sleepstatus)
                .from(infoDetail);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default InfoDetail selectByPrimaryKey(Integer recordNo_) {
        return SelectDSL.selectWithMapper(this::selectOne, recordNo, interfaceMainid, state, heartbeat, breath, wet, weight, position, od, updatetime, sleepstatus)
                .from(infoDetail)
                .where(recordNo, isEqualTo(recordNo_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(InfoDetail record) {
        return UpdateDSL.updateWithMapper(this::update, infoDetail)
                .set(interfaceMainid).equalTo(record::getInterfaceMainid)
                .set(state).equalTo(record::getState)
                .set(heartbeat).equalTo(record::getHeartbeat)
                .set(breath).equalTo(record::getBreath)
                .set(wet).equalTo(record::getWet)
                .set(weight).equalTo(record::getWeight)
                .set(position).equalTo(record::getPosition)
                .set(od).equalTo(record::getOd)
                .set(updatetime).equalTo(record::getUpdatetime)
                .set(sleepstatus).equalTo(record::getSleepstatus);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(InfoDetail record) {
        return UpdateDSL.updateWithMapper(this::update, infoDetail)
                .set(interfaceMainid).equalToWhenPresent(record::getInterfaceMainid)
                .set(state).equalToWhenPresent(record::getState)
                .set(heartbeat).equalToWhenPresent(record::getHeartbeat)
                .set(breath).equalToWhenPresent(record::getBreath)
                .set(wet).equalToWhenPresent(record::getWet)
                .set(weight).equalToWhenPresent(record::getWeight)
                .set(position).equalToWhenPresent(record::getPosition)
                .set(od).equalToWhenPresent(record::getOd)
                .set(updatetime).equalToWhenPresent(record::getUpdatetime)
                .set(sleepstatus).equalToWhenPresent(record::getSleepstatus);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(InfoDetail record) {
        return UpdateDSL.updateWithMapper(this::update, infoDetail)
                .set(interfaceMainid).equalTo(record::getInterfaceMainid)
                .set(state).equalTo(record::getState)
                .set(heartbeat).equalTo(record::getHeartbeat)
                .set(breath).equalTo(record::getBreath)
                .set(wet).equalTo(record::getWet)
                .set(weight).equalTo(record::getWeight)
                .set(position).equalTo(record::getPosition)
                .set(od).equalTo(record::getOd)
                .set(updatetime).equalTo(record::getUpdatetime)
                .set(sleepstatus).equalTo(record::getSleepstatus)
                .where(recordNo, isEqualTo(record::getRecordNo))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(InfoDetail record) {
        return UpdateDSL.updateWithMapper(this::update, infoDetail)
                .set(interfaceMainid).equalToWhenPresent(record::getInterfaceMainid)
                .set(state).equalToWhenPresent(record::getState)
                .set(heartbeat).equalToWhenPresent(record::getHeartbeat)
                .set(breath).equalToWhenPresent(record::getBreath)
                .set(wet).equalToWhenPresent(record::getWet)
                .set(weight).equalToWhenPresent(record::getWeight)
                .set(position).equalToWhenPresent(record::getPosition)
                .set(od).equalToWhenPresent(record::getOd)
                .set(updatetime).equalToWhenPresent(record::getUpdatetime)
                .set(sleepstatus).equalToWhenPresent(record::getSleepstatus)
                .where(recordNo, isEqualTo(record::getRecordNo))
                .build()
                .execute();
    }
}