package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.BedActiveDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.BedActive;
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
public interface BedActiveMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<BedActive> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BedActiveResult")
    BedActive selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BedActiveResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.BIGINT),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE),
        @Result(column="serial_no", property="serialNo", jdbcType=JdbcType.NVARCHAR),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    List<BedActive> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(bedActive);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, bedActive);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, bedActive)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(BedActive record) {
        return insert(SqlBuilder.insert(record)
                .into(bedActive)
                .map(deptId).toProperty("deptId")
                .map(date).toProperty("date")
                .map(serialNo).toProperty("serialNo")
                .map(createUser).toProperty("createUser")
                .map(createTime).toProperty("createTime")
                .map(updateUser).toProperty("updateUser")
                .map(updateTime).toProperty("updateTime")
                .map(status).toProperty("status")
                .map(type).toProperty("type")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(BedActive record) {
        return insert(SqlBuilder.insert(record)
                .into(bedActive)
                .map(deptId).toPropertyWhenPresent("deptId", record::getDeptId)
                .map(date).toPropertyWhenPresent("date", record::getDate)
                .map(serialNo).toPropertyWhenPresent("serialNo", record::getSerialNo)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(type).toPropertyWhenPresent("type", record::getType)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedActive>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, deptId, date, serialNo, createUser, createTime, updateUser, updateTime, status, type)
                .from(bedActive);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedActive>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, deptId, date, serialNo, createUser, createTime, updateUser, updateTime, status, type)
                .from(bedActive);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default BedActive selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, deptId, date, serialNo, createUser, createTime, updateUser, updateTime, status, type)
                .from(bedActive)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(BedActive record) {
        return UpdateDSL.updateWithMapper(this::update, bedActive)
                .set(deptId).equalTo(record::getDeptId)
                .set(date).equalTo(record::getDate)
                .set(serialNo).equalTo(record::getSerialNo)
                .set(createUser).equalTo(record::getCreateUser)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(status).equalTo(record::getStatus)
                .set(type).equalTo(record::getType);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(BedActive record) {
        return UpdateDSL.updateWithMapper(this::update, bedActive)
                .set(deptId).equalToWhenPresent(record::getDeptId)
                .set(date).equalToWhenPresent(record::getDate)
                .set(serialNo).equalToWhenPresent(record::getSerialNo)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(type).equalToWhenPresent(record::getType);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(BedActive record) {
        return UpdateDSL.updateWithMapper(this::update, bedActive)
                .set(deptId).equalTo(record::getDeptId)
                .set(date).equalTo(record::getDate)
                .set(serialNo).equalTo(record::getSerialNo)
                .set(createUser).equalTo(record::getCreateUser)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(status).equalTo(record::getStatus)
                .set(type).equalTo(record::getType)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(BedActive record) {
        return UpdateDSL.updateWithMapper(this::update, bedActive)
                .set(deptId).equalToWhenPresent(record::getDeptId)
                .set(date).equalToWhenPresent(record::getDate)
                .set(serialNo).equalToWhenPresent(record::getSerialNo)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(type).equalToWhenPresent(record::getType)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}