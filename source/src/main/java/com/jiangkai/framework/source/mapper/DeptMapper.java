package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.DeptDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.Dept;
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
public interface DeptMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<Dept> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DeptResult")
    Dept selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DeptResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="superior_id", property="superiorId", jdbcType=JdbcType.BIGINT),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="deptType", property="depttype", jdbcType=JdbcType.BIGINT),
        @Result(column="no", property="no", jdbcType=JdbcType.NVARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.NVARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="longitude", property="longitude", jdbcType=JdbcType.DECIMAL),
        @Result(column="latitude", property="latitude", jdbcType=JdbcType.DECIMAL),
        @Result(column="level", property="level", jdbcType=JdbcType.INTEGER)
    })
    List<Dept> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(dept);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, dept);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, dept)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Dept record) {
        return insert(SqlBuilder.insert(record)
                .into(dept)
                .map(superiorId).toProperty("superiorId")
                .map(path).toProperty("path")
                .map(depttype).toProperty("depttype")
                .map(no).toProperty("no")
                .map(name).toProperty("name")
                .map(createTime).toProperty("createTime")
                .map(createUser).toProperty("createUser")
                .map(updateTime).toProperty("updateTime")
                .map(updateUser).toProperty("updateUser")
                .map(status).toProperty("status")
                .map(longitude).toProperty("longitude")
                .map(latitude).toProperty("latitude")
                .map(level).toProperty("level")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Dept record) {
        return insert(SqlBuilder.insert(record)
                .into(dept)
                .map(superiorId).toPropertyWhenPresent("superiorId", record::getSuperiorId)
                .map(path).toPropertyWhenPresent("path", record::getPath)
                .map(depttype).toPropertyWhenPresent("depttype", record::getDepttype)
                .map(no).toPropertyWhenPresent("no", record::getNo)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(longitude).toPropertyWhenPresent("longitude", record::getLongitude)
                .map(latitude).toPropertyWhenPresent("latitude", record::getLatitude)
                .map(level).toPropertyWhenPresent("level", record::getLevel)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Dept>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, superiorId, path, depttype, no, name, createTime, createUser, updateTime, updateUser, status, longitude, latitude, level)
                .from(dept);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Dept>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, superiorId, path, depttype, no, name, createTime, createUser, updateTime, updateUser, status, longitude, latitude, level)
                .from(dept);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Dept selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, superiorId, path, depttype, no, name, createTime, createUser, updateTime, updateUser, status, longitude, latitude, level)
                .from(dept)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Dept record) {
        return UpdateDSL.updateWithMapper(this::update, dept)
                .set(superiorId).equalTo(record::getSuperiorId)
                .set(path).equalTo(record::getPath)
                .set(depttype).equalTo(record::getDepttype)
                .set(no).equalTo(record::getNo)
                .set(name).equalTo(record::getName)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(longitude).equalTo(record::getLongitude)
                .set(latitude).equalTo(record::getLatitude)
                .set(level).equalTo(record::getLevel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Dept record) {
        return UpdateDSL.updateWithMapper(this::update, dept)
                .set(superiorId).equalToWhenPresent(record::getSuperiorId)
                .set(path).equalToWhenPresent(record::getPath)
                .set(depttype).equalToWhenPresent(record::getDepttype)
                .set(no).equalToWhenPresent(record::getNo)
                .set(name).equalToWhenPresent(record::getName)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(longitude).equalToWhenPresent(record::getLongitude)
                .set(latitude).equalToWhenPresent(record::getLatitude)
                .set(level).equalToWhenPresent(record::getLevel);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Dept record) {
        return UpdateDSL.updateWithMapper(this::update, dept)
                .set(superiorId).equalTo(record::getSuperiorId)
                .set(path).equalTo(record::getPath)
                .set(depttype).equalTo(record::getDepttype)
                .set(no).equalTo(record::getNo)
                .set(name).equalTo(record::getName)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(longitude).equalTo(record::getLongitude)
                .set(latitude).equalTo(record::getLatitude)
                .set(level).equalTo(record::getLevel)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Dept record) {
        return UpdateDSL.updateWithMapper(this::update, dept)
                .set(superiorId).equalToWhenPresent(record::getSuperiorId)
                .set(path).equalToWhenPresent(record::getPath)
                .set(depttype).equalToWhenPresent(record::getDepttype)
                .set(no).equalToWhenPresent(record::getNo)
                .set(name).equalToWhenPresent(record::getName)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(longitude).equalToWhenPresent(record::getLongitude)
                .set(latitude).equalToWhenPresent(record::getLatitude)
                .set(level).equalToWhenPresent(record::getLevel)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}