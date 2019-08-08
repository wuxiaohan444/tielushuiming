package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.MethodDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.Method;
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
public interface MethodMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<Method> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MethodResult")
    Method selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MethodResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="superior_id", property="superiorId", jdbcType=JdbcType.BIGINT),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.NVARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="is_menu", property="isMenu", jdbcType=JdbcType.INTEGER)
    })
    List<Method> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(method);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, method);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, method)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Method record) {
        return insert(SqlBuilder.insert(record)
                .into(method)
                .map(superiorId).toProperty("superiorId")
                .map(path).toProperty("path")
                .map(name).toProperty("name")
                .map(url).toProperty("url")
                .map(createTime).toProperty("createTime")
                .map(createUser).toProperty("createUser")
                .map(updateTime).toProperty("updateTime")
                .map(updateUser).toProperty("updateUser")
                .map(status).toProperty("status")
                .map(isMenu).toProperty("isMenu")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Method record) {
        return insert(SqlBuilder.insert(record)
                .into(method)
                .map(superiorId).toPropertyWhenPresent("superiorId", record::getSuperiorId)
                .map(path).toPropertyWhenPresent("path", record::getPath)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(url).toPropertyWhenPresent("url", record::getUrl)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(isMenu).toPropertyWhenPresent("isMenu", record::getIsMenu)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Method>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, superiorId, path, name, url, createTime, createUser, updateTime, updateUser, status, isMenu)
                .from(method);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Method>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, superiorId, path, name, url, createTime, createUser, updateTime, updateUser, status, isMenu)
                .from(method);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Method selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, superiorId, path, name, url, createTime, createUser, updateTime, updateUser, status, isMenu)
                .from(method)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Method record) {
        return UpdateDSL.updateWithMapper(this::update, method)
                .set(superiorId).equalTo(record::getSuperiorId)
                .set(path).equalTo(record::getPath)
                .set(name).equalTo(record::getName)
                .set(url).equalTo(record::getUrl)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(isMenu).equalTo(record::getIsMenu);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Method record) {
        return UpdateDSL.updateWithMapper(this::update, method)
                .set(superiorId).equalToWhenPresent(record::getSuperiorId)
                .set(path).equalToWhenPresent(record::getPath)
                .set(name).equalToWhenPresent(record::getName)
                .set(url).equalToWhenPresent(record::getUrl)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(isMenu).equalToWhenPresent(record::getIsMenu);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Method record) {
        return UpdateDSL.updateWithMapper(this::update, method)
                .set(superiorId).equalTo(record::getSuperiorId)
                .set(path).equalTo(record::getPath)
                .set(name).equalTo(record::getName)
                .set(url).equalTo(record::getUrl)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(isMenu).equalTo(record::getIsMenu)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Method record) {
        return UpdateDSL.updateWithMapper(this::update, method)
                .set(superiorId).equalToWhenPresent(record::getSuperiorId)
                .set(path).equalToWhenPresent(record::getPath)
                .set(name).equalToWhenPresent(record::getName)
                .set(url).equalToWhenPresent(record::getUrl)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(isMenu).equalToWhenPresent(record::getIsMenu)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}