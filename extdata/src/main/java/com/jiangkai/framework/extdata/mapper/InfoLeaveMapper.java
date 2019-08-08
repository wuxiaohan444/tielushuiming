package com.jiangkai.framework.extdata.mapper;

import static com.jiangkai.framework.extdata.mapper.InfoLeaveDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.extdata.model.InfoLeave;
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
public interface InfoLeaveMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<InfoLeave> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("InfoLeaveResult")
    InfoLeave selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="InfoLeaveResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="MainId", property="mainid", jdbcType=JdbcType.INTEGER),
        @Result(column="OnBedDate", property="onbeddate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LeaveBedDate", property="leavebeddate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LeaveTime", property="leavetime", jdbcType=JdbcType.TIME)
    })
    List<InfoLeave> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(infoLeave);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, infoLeave);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, infoLeave)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(InfoLeave record) {
        return insert(SqlBuilder.insert(record)
                .into(infoLeave)
                .map(mainid).toProperty("mainid")
                .map(onbeddate).toProperty("onbeddate")
                .map(leavebeddate).toProperty("leavebeddate")
                .map(leavetime).toProperty("leavetime")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(InfoLeave record) {
        return insert(SqlBuilder.insert(record)
                .into(infoLeave)
                .map(mainid).toPropertyWhenPresent("mainid", record::getMainid)
                .map(onbeddate).toPropertyWhenPresent("onbeddate", record::getOnbeddate)
                .map(leavebeddate).toPropertyWhenPresent("leavebeddate", record::getLeavebeddate)
                .map(leavetime).toPropertyWhenPresent("leavetime", record::getLeavetime)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoLeave>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, mainid, onbeddate, leavebeddate, leavetime)
                .from(infoLeave);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoLeave>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, mainid, onbeddate, leavebeddate, leavetime)
                .from(infoLeave);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default InfoLeave selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, mainid, onbeddate, leavebeddate, leavetime)
                .from(infoLeave)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(InfoLeave record) {
        return UpdateDSL.updateWithMapper(this::update, infoLeave)
                .set(mainid).equalTo(record::getMainid)
                .set(onbeddate).equalTo(record::getOnbeddate)
                .set(leavebeddate).equalTo(record::getLeavebeddate)
                .set(leavetime).equalTo(record::getLeavetime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(InfoLeave record) {
        return UpdateDSL.updateWithMapper(this::update, infoLeave)
                .set(mainid).equalToWhenPresent(record::getMainid)
                .set(onbeddate).equalToWhenPresent(record::getOnbeddate)
                .set(leavebeddate).equalToWhenPresent(record::getLeavebeddate)
                .set(leavetime).equalToWhenPresent(record::getLeavetime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(InfoLeave record) {
        return UpdateDSL.updateWithMapper(this::update, infoLeave)
                .set(mainid).equalTo(record::getMainid)
                .set(onbeddate).equalTo(record::getOnbeddate)
                .set(leavebeddate).equalTo(record::getLeavebeddate)
                .set(leavetime).equalTo(record::getLeavetime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(InfoLeave record) {
        return UpdateDSL.updateWithMapper(this::update, infoLeave)
                .set(mainid).equalToWhenPresent(record::getMainid)
                .set(onbeddate).equalToWhenPresent(record::getOnbeddate)
                .set(leavebeddate).equalToWhenPresent(record::getLeavebeddate)
                .set(leavetime).equalToWhenPresent(record::getLeavetime)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}