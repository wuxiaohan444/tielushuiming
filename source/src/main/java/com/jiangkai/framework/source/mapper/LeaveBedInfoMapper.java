package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.LeaveBedInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.LeaveBedInfo;
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
public interface LeaveBedInfoMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<LeaveBedInfo> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("LeaveBedInfoResult")
    LeaveBedInfo selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="LeaveBedInfoResult", value = {
        @Result(column="key", property="key", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="MainId", property="mainid", jdbcType=JdbcType.INTEGER),
        @Result(column="OnBedDate", property="onbeddate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LeaveBedDate", property="leavebeddate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="LeaveTime", property="leavetime", jdbcType=JdbcType.TIME),
        @Result(column="data_source_id", property="dataSourceId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="bed_info_id", property="bedInfoId", jdbcType=JdbcType.BIGINT),
        @Result(column="file", property="file", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_type", property="fileType", jdbcType=JdbcType.INTEGER),
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.BIGINT)
    })
    List<LeaveBedInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(leaveBedInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, leaveBedInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long key_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, leaveBedInfo)
                .where(key, isEqualTo(key_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(LeaveBedInfo record) {
        return insert(SqlBuilder.insert(record)
                .into(leaveBedInfo)
                .map(key).toProperty("key")
                .map(mainid).toProperty("mainid")
                .map(onbeddate).toProperty("onbeddate")
                .map(leavebeddate).toProperty("leavebeddate")
                .map(leavetime).toProperty("leavetime")
                .map(dataSourceId).toProperty("dataSourceId")
                .map(createTime).toProperty("createTime")
                .map(createUser).toProperty("createUser")
                .map(updateTime).toProperty("updateTime")
                .map(updateUser).toProperty("updateUser")
                .map(status).toProperty("status")
                .map(bedInfoId).toProperty("bedInfoId")
                .map(file).toProperty("file")
                .map(fileType).toProperty("fileType")
                .map(deptId).toProperty("deptId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(LeaveBedInfo record) {
        return insert(SqlBuilder.insert(record)
                .into(leaveBedInfo)
                .map(key).toPropertyWhenPresent("key", record::getKey)
                .map(mainid).toPropertyWhenPresent("mainid", record::getMainid)
                .map(onbeddate).toPropertyWhenPresent("onbeddate", record::getOnbeddate)
                .map(leavebeddate).toPropertyWhenPresent("leavebeddate", record::getLeavebeddate)
                .map(leavetime).toPropertyWhenPresent("leavetime", record::getLeavetime)
                .map(dataSourceId).toPropertyWhenPresent("dataSourceId", record::getDataSourceId)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(bedInfoId).toPropertyWhenPresent("bedInfoId", record::getBedInfoId)
                .map(file).toPropertyWhenPresent("file", record::getFile)
                .map(fileType).toPropertyWhenPresent("fileType", record::getFileType)
                .map(deptId).toPropertyWhenPresent("deptId", record::getDeptId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<LeaveBedInfo>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, key, mainid, onbeddate, leavebeddate, id, leavetime, dataSourceId, createTime, createUser, updateTime, updateUser, status, bedInfoId, file, fileType, deptId)
                .from(leaveBedInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<LeaveBedInfo>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, key, mainid, onbeddate, leavebeddate, id, leavetime, dataSourceId, createTime, createUser, updateTime, updateUser, status, bedInfoId, file, fileType, deptId)
                .from(leaveBedInfo);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default LeaveBedInfo selectByPrimaryKey(Long key_) {
        return SelectDSL.selectWithMapper(this::selectOne, key, mainid, onbeddate, leavebeddate, id, leavetime, dataSourceId, createTime, createUser, updateTime, updateUser, status, bedInfoId, file, fileType, deptId)
                .from(leaveBedInfo)
                .where(key, isEqualTo(key_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(LeaveBedInfo record) {
        return UpdateDSL.updateWithMapper(this::update, leaveBedInfo)
                .set(key).equalTo(record::getKey)
                .set(mainid).equalTo(record::getMainid)
                .set(onbeddate).equalTo(record::getOnbeddate)
                .set(leavebeddate).equalTo(record::getLeavebeddate)
                .set(leavetime).equalTo(record::getLeavetime)
                .set(dataSourceId).equalTo(record::getDataSourceId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(bedInfoId).equalTo(record::getBedInfoId)
                .set(file).equalTo(record::getFile)
                .set(fileType).equalTo(record::getFileType)
                .set(deptId).equalTo(record::getDeptId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(LeaveBedInfo record) {
        return UpdateDSL.updateWithMapper(this::update, leaveBedInfo)
                .set(key).equalToWhenPresent(record::getKey)
                .set(mainid).equalToWhenPresent(record::getMainid)
                .set(onbeddate).equalToWhenPresent(record::getOnbeddate)
                .set(leavebeddate).equalToWhenPresent(record::getLeavebeddate)
                .set(leavetime).equalToWhenPresent(record::getLeavetime)
                .set(dataSourceId).equalToWhenPresent(record::getDataSourceId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(bedInfoId).equalToWhenPresent(record::getBedInfoId)
                .set(file).equalToWhenPresent(record::getFile)
                .set(fileType).equalToWhenPresent(record::getFileType)
                .set(deptId).equalToWhenPresent(record::getDeptId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(LeaveBedInfo record) {
        return UpdateDSL.updateWithMapper(this::update, leaveBedInfo)
                .set(mainid).equalTo(record::getMainid)
                .set(onbeddate).equalTo(record::getOnbeddate)
                .set(leavebeddate).equalTo(record::getLeavebeddate)
                .set(leavetime).equalTo(record::getLeavetime)
                .set(dataSourceId).equalTo(record::getDataSourceId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(bedInfoId).equalTo(record::getBedInfoId)
                .set(file).equalTo(record::getFile)
                .set(fileType).equalTo(record::getFileType)
                .set(deptId).equalTo(record::getDeptId)
                .where(key, isEqualTo(record::getKey))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(LeaveBedInfo record) {
        return UpdateDSL.updateWithMapper(this::update, leaveBedInfo)
                .set(mainid).equalToWhenPresent(record::getMainid)
                .set(onbeddate).equalToWhenPresent(record::getOnbeddate)
                .set(leavebeddate).equalToWhenPresent(record::getLeavebeddate)
                .set(leavetime).equalToWhenPresent(record::getLeavetime)
                .set(dataSourceId).equalToWhenPresent(record::getDataSourceId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(bedInfoId).equalToWhenPresent(record::getBedInfoId)
                .set(file).equalToWhenPresent(record::getFile)
                .set(fileType).equalToWhenPresent(record::getFileType)
                .set(deptId).equalToWhenPresent(record::getDeptId)
                .where(key, isEqualTo(record::getKey))
                .build()
                .execute();
    }
}