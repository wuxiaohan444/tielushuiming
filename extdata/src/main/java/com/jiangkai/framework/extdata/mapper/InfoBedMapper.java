package com.jiangkai.framework.extdata.mapper;

import com.jiangkai.framework.extdata.model.InfoBed;
import org.apache.ibatis.annotations.*;
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

import javax.annotation.Generated;
import java.util.List;

import static com.jiangkai.framework.extdata.mapper.InfoBedDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface InfoBedMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.serialno")
    int insert(InsertStatementProvider<InfoBed> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("InfoBedResult")
    InfoBed selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="InfoBedResult", value = {
        @Result(column="SerialNo", property="serialno", jdbcType=JdbcType.NVARCHAR, id=true),
        @Result(column="Manu", property="manu", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Model", property="model", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Version", property="version", jdbcType=JdbcType.NVARCHAR),
        @Result(column="IP", property="ip", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Rest_site", property="restSite", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Rest_room", property="restRoom", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Rest_bed", property="restBed", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Rest_building", property="restBuilding", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Rest_floor", property="restFloor", jdbcType=JdbcType.NVARCHAR),
        @Result(column="BY_bedid", property="byBedid", jdbcType=JdbcType.BIGINT)
    })
    List<InfoBed> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(infoBed);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, infoBed);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String serialno_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, infoBed)
                .where(serialno, isEqualTo(serialno_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(InfoBed record) {
        return insert(SqlBuilder.insert(record)
                .into(infoBed)
                .map(manu).toProperty("manu")
                .map(model).toProperty("model")
                .map(version).toProperty("version")
                .map(ip).toProperty("ip")
                .map(restSite).toProperty("restSite")
                .map(restRoom).toProperty("restRoom")
                .map(restBed).toProperty("restBed")
                .map(restBuilding).toProperty("restBuilding")
                .map(restFloor).toProperty("restFloor")
                .map(byBedid).toProperty("byBedid")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(InfoBed record) {
        return insert(SqlBuilder.insert(record)
                .into(infoBed)
                .map(manu).toPropertyWhenPresent("manu", record::getManu)
                .map(model).toPropertyWhenPresent("model", record::getModel)
                .map(version).toPropertyWhenPresent("version", record::getVersion)
                .map(ip).toPropertyWhenPresent("ip", record::getIp)
                .map(restSite).toPropertyWhenPresent("restSite", record::getRestSite)
                .map(restRoom).toPropertyWhenPresent("restRoom", record::getRestRoom)
                .map(restBed).toPropertyWhenPresent("restBed", record::getRestBed)
                .map(restBuilding).toPropertyWhenPresent("restBuilding", record::getRestBuilding)
                .map(restFloor).toPropertyWhenPresent("restFloor", record::getRestFloor)
                .map(byBedid).toPropertyWhenPresent("byBedid", record::getByBedid)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoBed>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, serialno, manu, model, version, ip, restSite, restRoom, restBed, restBuilding, restFloor, byBedid)
                .from(infoBed);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoBed>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, serialno, manu, model, version, ip, restSite, restRoom, restBed, restBuilding, restFloor, byBedid)
                .from(infoBed);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default InfoBed selectByPrimaryKey(String serialno_) {
        return SelectDSL.selectWithMapper(this::selectOne, serialno, manu, model, version, ip, restSite, restRoom, restBed, restBuilding, restFloor, byBedid)
                .from(infoBed)
                .where(serialno, isEqualTo(serialno_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(InfoBed record) {
        return UpdateDSL.updateWithMapper(this::update, infoBed)
                .set(manu).equalTo(record::getManu)
                .set(model).equalTo(record::getModel)
                .set(version).equalTo(record::getVersion)
                .set(ip).equalTo(record::getIp)
                .set(restSite).equalTo(record::getRestSite)
                .set(restRoom).equalTo(record::getRestRoom)
                .set(restBed).equalTo(record::getRestBed)
                .set(restBuilding).equalTo(record::getRestBuilding)
                .set(restFloor).equalTo(record::getRestFloor)
                .set(byBedid).equalTo(record::getByBedid);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(InfoBed record) {
        return UpdateDSL.updateWithMapper(this::update, infoBed)
                .set(manu).equalToWhenPresent(record::getManu)
                .set(model).equalToWhenPresent(record::getModel)
                .set(version).equalToWhenPresent(record::getVersion)
                .set(ip).equalToWhenPresent(record::getIp)
                .set(restSite).equalToWhenPresent(record::getRestSite)
                .set(restRoom).equalToWhenPresent(record::getRestRoom)
                .set(restBed).equalToWhenPresent(record::getRestBed)
                .set(restBuilding).equalToWhenPresent(record::getRestBuilding)
                .set(restFloor).equalToWhenPresent(record::getRestFloor)
                .set(byBedid).equalToWhenPresent(record::getByBedid);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(InfoBed record) {
        return UpdateDSL.updateWithMapper(this::update, infoBed)
                .set(manu).equalTo(record::getManu)
                .set(model).equalTo(record::getModel)
                .set(version).equalTo(record::getVersion)
                .set(ip).equalTo(record::getIp)
                .set(restSite).equalTo(record::getRestSite)
                .set(restRoom).equalTo(record::getRestRoom)
                .set(restBed).equalTo(record::getRestBed)
                .set(restBuilding).equalTo(record::getRestBuilding)
                .set(restFloor).equalTo(record::getRestFloor)
                .set(byBedid).equalTo(record::getByBedid)
                .where(serialno, isEqualTo(record::getSerialno))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(InfoBed record) {
        return UpdateDSL.updateWithMapper(this::update, infoBed)
                .set(manu).equalToWhenPresent(record::getManu)
                .set(model).equalToWhenPresent(record::getModel)
                .set(version).equalToWhenPresent(record::getVersion)
                .set(ip).equalToWhenPresent(record::getIp)
                .set(restSite).equalToWhenPresent(record::getRestSite)
                .set(restRoom).equalToWhenPresent(record::getRestRoom)
                .set(restBed).equalToWhenPresent(record::getRestBed)
                .set(restBuilding).equalToWhenPresent(record::getRestBuilding)
                .set(restFloor).equalToWhenPresent(record::getRestFloor)
                .set(byBedid).equalToWhenPresent(record::getByBedid)
                .where(serialno, isEqualTo(record::getSerialno))
                .build()
                .execute();
    }
}