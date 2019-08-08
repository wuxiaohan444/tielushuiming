package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.BedInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.BedInfo;
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
public interface BedInfoMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<BedInfo> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BedInfoResult")
    BedInfo selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BedInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="Manu", property="manu", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Model", property="model", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Version", property="version", jdbcType=JdbcType.NVARCHAR),
        @Result(column="SerialNo", property="serialno", jdbcType=JdbcType.NVARCHAR),
        @Result(column="IP", property="ip", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Rest_site", property="restSite", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Rest_room", property="restRoom", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Rest_bed", property="restBed", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Rest_building", property="restBuilding", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Rest_floor", property="restFloor", jdbcType=JdbcType.NVARCHAR),
        @Result(column="BY_bedid", property="byBedid", jdbcType=JdbcType.BIGINT),
        @Result(column="data_source_id", property="dataSourceId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.BIGINT)
    })
    List<BedInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(bedInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, bedInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, bedInfo)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(BedInfo record) {
        return insert(SqlBuilder.insert(record)
                .into(bedInfo)
                .map(manu).toProperty("manu")
                .map(model).toProperty("model")
                .map(version).toProperty("version")
                .map(serialno).toProperty("serialno")
                .map(ip).toProperty("ip")
                .map(restSite).toProperty("restSite")
                .map(restRoom).toProperty("restRoom")
                .map(restBed).toProperty("restBed")
                .map(restBuilding).toProperty("restBuilding")
                .map(restFloor).toProperty("restFloor")
                .map(byBedid).toProperty("byBedid")
                .map(dataSourceId).toProperty("dataSourceId")
                .map(createUser).toProperty("createUser")
                .map(createTime).toProperty("createTime")
                .map(updateUser).toProperty("updateUser")
                .map(updateTime).toProperty("updateTime")
                .map(status).toProperty("status")
                .map(deptId).toProperty("deptId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(BedInfo record) {
        return insert(SqlBuilder.insert(record)
                .into(bedInfo)
                .map(manu).toPropertyWhenPresent("manu", record::getManu)
                .map(model).toPropertyWhenPresent("model", record::getModel)
                .map(version).toPropertyWhenPresent("version", record::getVersion)
                .map(serialno).toPropertyWhenPresent("serialno", record::getSerialno)
                .map(ip).toPropertyWhenPresent("ip", record::getIp)
                .map(restSite).toPropertyWhenPresent("restSite", record::getRestSite)
                .map(restRoom).toPropertyWhenPresent("restRoom", record::getRestRoom)
                .map(restBed).toPropertyWhenPresent("restBed", record::getRestBed)
                .map(restBuilding).toPropertyWhenPresent("restBuilding", record::getRestBuilding)
                .map(restFloor).toPropertyWhenPresent("restFloor", record::getRestFloor)
                .map(byBedid).toPropertyWhenPresent("byBedid", record::getByBedid)
                .map(dataSourceId).toPropertyWhenPresent("dataSourceId", record::getDataSourceId)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(deptId).toPropertyWhenPresent("deptId", record::getDeptId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedInfo>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, manu, model, version, serialno, ip, restSite, restRoom, restBed, restBuilding, restFloor, byBedid, dataSourceId, createUser, createTime, updateUser, updateTime, status, deptId)
                .from(bedInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedInfo>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, manu, model, version, serialno, ip, restSite, restRoom, restBed, restBuilding, restFloor, byBedid, dataSourceId, createUser, createTime, updateUser, updateTime, status, deptId)
                .from(bedInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default BedInfo selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, manu, model, version, serialno, ip, restSite, restRoom, restBed, restBuilding, restFloor, byBedid, dataSourceId, createUser, createTime, updateUser, updateTime, status, deptId)
                .from(bedInfo)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(BedInfo record) {
        return UpdateDSL.updateWithMapper(this::update, bedInfo)
                .set(manu).equalTo(record::getManu)
                .set(model).equalTo(record::getModel)
                .set(version).equalTo(record::getVersion)
                .set(serialno).equalTo(record::getSerialno)
                .set(ip).equalTo(record::getIp)
                .set(restSite).equalTo(record::getRestSite)
                .set(restRoom).equalTo(record::getRestRoom)
                .set(restBed).equalTo(record::getRestBed)
                .set(restBuilding).equalTo(record::getRestBuilding)
                .set(restFloor).equalTo(record::getRestFloor)
                .set(byBedid).equalTo(record::getByBedid)
                .set(dataSourceId).equalTo(record::getDataSourceId)
                .set(createUser).equalTo(record::getCreateUser)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(status).equalTo(record::getStatus)
                .set(deptId).equalTo(record::getDeptId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(BedInfo record) {
        return UpdateDSL.updateWithMapper(this::update, bedInfo)
                .set(manu).equalToWhenPresent(record::getManu)
                .set(model).equalToWhenPresent(record::getModel)
                .set(version).equalToWhenPresent(record::getVersion)
                .set(serialno).equalToWhenPresent(record::getSerialno)
                .set(ip).equalToWhenPresent(record::getIp)
                .set(restSite).equalToWhenPresent(record::getRestSite)
                .set(restRoom).equalToWhenPresent(record::getRestRoom)
                .set(restBed).equalToWhenPresent(record::getRestBed)
                .set(restBuilding).equalToWhenPresent(record::getRestBuilding)
                .set(restFloor).equalToWhenPresent(record::getRestFloor)
                .set(byBedid).equalToWhenPresent(record::getByBedid)
                .set(dataSourceId).equalToWhenPresent(record::getDataSourceId)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(deptId).equalToWhenPresent(record::getDeptId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(BedInfo record) {
        return UpdateDSL.updateWithMapper(this::update, bedInfo)
                .set(manu).equalTo(record::getManu)
                .set(model).equalTo(record::getModel)
                .set(version).equalTo(record::getVersion)
                .set(serialno).equalTo(record::getSerialno)
                .set(ip).equalTo(record::getIp)
                .set(restSite).equalTo(record::getRestSite)
                .set(restRoom).equalTo(record::getRestRoom)
                .set(restBed).equalTo(record::getRestBed)
                .set(restBuilding).equalTo(record::getRestBuilding)
                .set(restFloor).equalTo(record::getRestFloor)
                .set(byBedid).equalTo(record::getByBedid)
                .set(dataSourceId).equalTo(record::getDataSourceId)
                .set(createUser).equalTo(record::getCreateUser)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(status).equalTo(record::getStatus)
                .set(deptId).equalTo(record::getDeptId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(BedInfo record) {
        return UpdateDSL.updateWithMapper(this::update, bedInfo)
                .set(manu).equalToWhenPresent(record::getManu)
                .set(model).equalToWhenPresent(record::getModel)
                .set(version).equalToWhenPresent(record::getVersion)
                .set(serialno).equalToWhenPresent(record::getSerialno)
                .set(ip).equalToWhenPresent(record::getIp)
                .set(restSite).equalToWhenPresent(record::getRestSite)
                .set(restRoom).equalToWhenPresent(record::getRestRoom)
                .set(restBed).equalToWhenPresent(record::getRestBed)
                .set(restBuilding).equalToWhenPresent(record::getRestBuilding)
                .set(restFloor).equalToWhenPresent(record::getRestFloor)
                .set(byBedid).equalToWhenPresent(record::getByBedid)
                .set(dataSourceId).equalToWhenPresent(record::getDataSourceId)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(deptId).equalToWhenPresent(record::getDeptId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}