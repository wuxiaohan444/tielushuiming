package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.DriverDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.Driver;
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
public interface DriverMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<Driver> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DriverResult")
    Driver selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DriverResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="driver_no", property="driverNo", jdbcType=JdbcType.NVARCHAR),
        @Result(column="driver_name", property="driverName", jdbcType=JdbcType.NVARCHAR),
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<Driver> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(driver);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, driver);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, driver)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Driver record) {
        return insert(SqlBuilder.insert(record)
                .into(driver)
                .map(driverNo).toProperty("driverNo")
                .map(driverName).toProperty("driverName")
                .map(deptId).toProperty("deptId")
                .map(createTime).toProperty("createTime")
                .map(createUser).toProperty("createUser")
                .map(updateTime).toProperty("updateTime")
                .map(updateUser).toProperty("updateUser")
                .map(status).toProperty("status")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Driver record) {
        return insert(SqlBuilder.insert(record)
                .into(driver)
                .map(driverNo).toPropertyWhenPresent("driverNo", record::getDriverNo)
                .map(driverName).toPropertyWhenPresent("driverName", record::getDriverName)
                .map(deptId).toPropertyWhenPresent("deptId", record::getDeptId)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Driver>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, driverNo, driverName, deptId, createTime, createUser, updateTime, updateUser, status)
                .from(driver);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Driver>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, driverNo, driverName, deptId, createTime, createUser, updateTime, updateUser, status)
                .from(driver);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Driver selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, driverNo, driverName, deptId, createTime, createUser, updateTime, updateUser, status)
                .from(driver)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Driver record) {
        return UpdateDSL.updateWithMapper(this::update, driver)
                .set(driverNo).equalTo(record::getDriverNo)
                .set(driverName).equalTo(record::getDriverName)
                .set(deptId).equalTo(record::getDeptId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Driver record) {
        return UpdateDSL.updateWithMapper(this::update, driver)
                .set(driverNo).equalToWhenPresent(record::getDriverNo)
                .set(driverName).equalToWhenPresent(record::getDriverName)
                .set(deptId).equalToWhenPresent(record::getDeptId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Driver record) {
        return UpdateDSL.updateWithMapper(this::update, driver)
                .set(driverNo).equalTo(record::getDriverNo)
                .set(driverName).equalTo(record::getDriverName)
                .set(deptId).equalTo(record::getDeptId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Driver record) {
        return UpdateDSL.updateWithMapper(this::update, driver)
                .set(driverNo).equalToWhenPresent(record::getDriverNo)
                .set(driverName).equalToWhenPresent(record::getDriverName)
                .set(deptId).equalToWhenPresent(record::getDeptId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}