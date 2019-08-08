package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.DataSourceDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.DataSource;
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
public interface DataSourceMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<DataSource> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DataSourceResult")
    DataSource selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DataSourceResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.BIGINT),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.NVARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.NVARCHAR),
        @Result(column="port", property="port", jdbcType=JdbcType.INTEGER),
        @Result(column="username", property="username", jdbcType=JdbcType.NVARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.NVARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="database_name", property="databaseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_error_time", property="lastErrorTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="error_msg", property="errorMsg", jdbcType=JdbcType.VARCHAR)
    })
    List<DataSource> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(dataSource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, dataSource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, dataSource)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(DataSource record) {
        return insert(SqlBuilder.insert(record)
                .into(dataSource)
                .map(deptId).toProperty("deptId")
                .map(nickname).toProperty("nickname")
                .map(ip).toProperty("ip")
                .map(port).toProperty("port")
                .map(username).toProperty("username")
                .map(password).toProperty("password")
                .map(createTime).toProperty("createTime")
                .map(createUser).toProperty("createUser")
                .map(updateTime).toProperty("updateTime")
                .map(updateUser).toProperty("updateUser")
                .map(status).toProperty("status")
                .map(path).toProperty("path")
                .map(databaseName).toProperty("databaseName")
                .map(lastErrorTime).toProperty("lastErrorTime")
                .map(errorMsg).toProperty("errorMsg")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(DataSource record) {
        return insert(SqlBuilder.insert(record)
                .into(dataSource)
                .map(deptId).toPropertyWhenPresent("deptId", record::getDeptId)
                .map(nickname).toPropertyWhenPresent("nickname", record::getNickname)
                .map(ip).toPropertyWhenPresent("ip", record::getIp)
                .map(port).toPropertyWhenPresent("port", record::getPort)
                .map(username).toPropertyWhenPresent("username", record::getUsername)
                .map(password).toPropertyWhenPresent("password", record::getPassword)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(path).toPropertyWhenPresent("path", record::getPath)
                .map(databaseName).toPropertyWhenPresent("databaseName", record::getDatabaseName)
                .map(lastErrorTime).toPropertyWhenPresent("lastErrorTime", record::getLastErrorTime)
                .map(errorMsg).toPropertyWhenPresent("errorMsg", record::getErrorMsg)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<DataSource>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, deptId, nickname, ip, port, username, password, createTime, createUser, updateTime, updateUser, status, path, databaseName, lastErrorTime, errorMsg)
                .from(dataSource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<DataSource>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, deptId, nickname, ip, port, username, password, createTime, createUser, updateTime, updateUser, status, path, databaseName, lastErrorTime, errorMsg)
                .from(dataSource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DataSource selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, deptId, nickname, ip, port, username, password, createTime, createUser, updateTime, updateUser, status, path, databaseName, lastErrorTime, errorMsg)
                .from(dataSource)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(DataSource record) {
        return UpdateDSL.updateWithMapper(this::update, dataSource)
                .set(deptId).equalTo(record::getDeptId)
                .set(nickname).equalTo(record::getNickname)
                .set(ip).equalTo(record::getIp)
                .set(port).equalTo(record::getPort)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(path).equalTo(record::getPath)
                .set(databaseName).equalTo(record::getDatabaseName)
                .set(lastErrorTime).equalTo(record::getLastErrorTime)
                .set(errorMsg).equalTo(record::getErrorMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(DataSource record) {
        return UpdateDSL.updateWithMapper(this::update, dataSource)
                .set(deptId).equalToWhenPresent(record::getDeptId)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(ip).equalToWhenPresent(record::getIp)
                .set(port).equalToWhenPresent(record::getPort)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(path).equalToWhenPresent(record::getPath)
                .set(databaseName).equalToWhenPresent(record::getDatabaseName)
                .set(lastErrorTime).equalToWhenPresent(record::getLastErrorTime)
                .set(errorMsg).equalToWhenPresent(record::getErrorMsg);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(DataSource record) {
        return UpdateDSL.updateWithMapper(this::update, dataSource)
                .set(deptId).equalTo(record::getDeptId)
                .set(nickname).equalTo(record::getNickname)
                .set(ip).equalTo(record::getIp)
                .set(port).equalTo(record::getPort)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(path).equalTo(record::getPath)
                .set(databaseName).equalTo(record::getDatabaseName)
                .set(lastErrorTime).equalTo(record::getLastErrorTime)
                .set(errorMsg).equalTo(record::getErrorMsg)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(DataSource record) {
        return UpdateDSL.updateWithMapper(this::update, dataSource)
                .set(deptId).equalToWhenPresent(record::getDeptId)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(ip).equalToWhenPresent(record::getIp)
                .set(port).equalToWhenPresent(record::getPort)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(path).equalToWhenPresent(record::getPath)
                .set(databaseName).equalToWhenPresent(record::getDatabaseName)
                .set(lastErrorTime).equalToWhenPresent(record::getLastErrorTime)
                .set(errorMsg).equalToWhenPresent(record::getErrorMsg)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}