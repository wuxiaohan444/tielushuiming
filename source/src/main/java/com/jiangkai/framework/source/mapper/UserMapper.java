package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.User;
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
public interface UserMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserResult")
    User selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="no", property="no", jdbcType=JdbcType.NVARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.NVARCHAR),
        @Result(column="head_img", property="headImg", jdbcType=JdbcType.NVARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.NVARCHAR),
        @Result(column="id_card", property="idCard", jdbcType=JdbcType.NVARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.NVARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.INTEGER),
        @Result(column="addr", property="addr", jdbcType=JdbcType.NVARCHAR),
        @Result(column="mark", property="mark", jdbcType=JdbcType.NVARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, user)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(User record) {
        return insert(SqlBuilder.insert(record)
                .into(user)
                .map(no).toProperty("no")
                .map(name).toProperty("name")
                .map(headImg).toProperty("headImg")
                .map(password).toProperty("password")
                .map(idCard).toProperty("idCard")
                .map(phone).toProperty("phone")
                .map(sex).toProperty("sex")
                .map(addr).toProperty("addr")
                .map(mark).toProperty("mark")
                .map(createTime).toProperty("createTime")
                .map(createUser).toProperty("createUser")
                .map(updateTime).toProperty("updateTime")
                .map(updateUser).toProperty("updateUser")
                .map(status).toProperty("status")
                .map(code).toProperty("code")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(User record) {
        return insert(SqlBuilder.insert(record)
                .into(user)
                .map(no).toPropertyWhenPresent("no", record::getNo)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(headImg).toPropertyWhenPresent("headImg", record::getHeadImg)
                .map(password).toPropertyWhenPresent("password", record::getPassword)
                .map(idCard).toPropertyWhenPresent("idCard", record::getIdCard)
                .map(phone).toPropertyWhenPresent("phone", record::getPhone)
                .map(sex).toPropertyWhenPresent("sex", record::getSex)
                .map(addr).toPropertyWhenPresent("addr", record::getAddr)
                .map(mark).toPropertyWhenPresent("mark", record::getMark)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(code).toPropertyWhenPresent("code", record::getCode)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, no, name, headImg, password, idCard, phone, sex, addr, mark, createTime, createUser, updateTime, updateUser, status, code)
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, no, name, headImg, password, idCard, phone, sex, addr, mark, createTime, createUser, updateTime, updateUser, status, code)
                .from(user);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default User selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, no, name, headImg, password, idCard, phone, sex, addr, mark, createTime, createUser, updateTime, updateUser, status, code)
                .from(user)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(no).equalTo(record::getNo)
                .set(name).equalTo(record::getName)
                .set(headImg).equalTo(record::getHeadImg)
                .set(password).equalTo(record::getPassword)
                .set(idCard).equalTo(record::getIdCard)
                .set(phone).equalTo(record::getPhone)
                .set(sex).equalTo(record::getSex)
                .set(addr).equalTo(record::getAddr)
                .set(mark).equalTo(record::getMark)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(code).equalTo(record::getCode);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(no).equalToWhenPresent(record::getNo)
                .set(name).equalToWhenPresent(record::getName)
                .set(headImg).equalToWhenPresent(record::getHeadImg)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(idCard).equalToWhenPresent(record::getIdCard)
                .set(phone).equalToWhenPresent(record::getPhone)
                .set(sex).equalToWhenPresent(record::getSex)
                .set(addr).equalToWhenPresent(record::getAddr)
                .set(mark).equalToWhenPresent(record::getMark)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(code).equalToWhenPresent(record::getCode);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(no).equalTo(record::getNo)
                .set(name).equalTo(record::getName)
                .set(headImg).equalTo(record::getHeadImg)
                .set(password).equalTo(record::getPassword)
                .set(idCard).equalTo(record::getIdCard)
                .set(phone).equalTo(record::getPhone)
                .set(sex).equalTo(record::getSex)
                .set(addr).equalTo(record::getAddr)
                .set(mark).equalTo(record::getMark)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(code).equalTo(record::getCode)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(no).equalToWhenPresent(record::getNo)
                .set(name).equalToWhenPresent(record::getName)
                .set(headImg).equalToWhenPresent(record::getHeadImg)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(idCard).equalToWhenPresent(record::getIdCard)
                .set(phone).equalToWhenPresent(record::getPhone)
                .set(sex).equalToWhenPresent(record::getSex)
                .set(addr).equalToWhenPresent(record::getAddr)
                .set(mark).equalToWhenPresent(record::getMark)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(code).equalToWhenPresent(record::getCode)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}