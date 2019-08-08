package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.SummaryDeptPersonDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.SummaryDeptPerson;
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
public interface SummaryDeptPersonMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<SummaryDeptPerson> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SummaryDeptPersonResult")
    SummaryDeptPerson selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SummaryDeptPersonResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="deptid", property="deptid", jdbcType=JdbcType.BIGINT),
        @Result(column="date", property="date", jdbcType=JdbcType.DATE),
        @Result(column="totalDriver", property="totaldriver", jdbcType=JdbcType.BIGINT),
        @Result(column="totalActiveBed", property="totalactivebed", jdbcType=JdbcType.BIGINT),
        @Result(column="totalBed", property="totalbed", jdbcType=JdbcType.BIGINT),
        @Result(column="totalUseDriver", property="totalusedriver", jdbcType=JdbcType.BIGINT),
        @Result(column="totalUseTime", property="totalusetime", jdbcType=JdbcType.BIGINT),
        @Result(column="avgUseTime", property="avgusetime", jdbcType=JdbcType.DECIMAL),
        @Result(column="avgSleepT", property="avgsleept", jdbcType=JdbcType.DECIMAL),
        @Result(column="avgSleepEff", property="avgsleepeff", jdbcType=JdbcType.DECIMAL),
        @Result(column="sleepTroubleCnt", property="sleeptroublecnt", jdbcType=JdbcType.BIGINT),
        @Result(column="autoCallWakeCnt", property="autocallwakecnt", jdbcType=JdbcType.BIGINT),
        @Result(column="artificalCallWakeCnt", property="artificalcallwakecnt", jdbcType=JdbcType.BIGINT),
        @Result(column="ratioAutoCallWake", property="ratioautocallwake", jdbcType=JdbcType.DECIMAL),
        @Result(column="warnCnt", property="warncnt", jdbcType=JdbcType.BIGINT),
        @Result(column="avgQian", property="avgqian", jdbcType=JdbcType.DECIMAL),
        @Result(column="ratioQian", property="ratioqian", jdbcType=JdbcType.DECIMAL),
        @Result(column="avgShen", property="avgshen", jdbcType=JdbcType.DECIMAL),
        @Result(column="ratioShen", property="ratioshen", jdbcType=JdbcType.DECIMAL),
        @Result(column="avgSleepTime", property="avgsleeptime", jdbcType=JdbcType.DECIMAL),
        @Result(column="ratioSleepTime", property="ratiosleeptime", jdbcType=JdbcType.DECIMAL),
        @Result(column="lowSleepEffCnt", property="lowsleepeffcnt", jdbcType=JdbcType.BIGINT),
        @Result(column="middleSleepEffCnt", property="middlesleepeffcnt", jdbcType=JdbcType.BIGINT),
        @Result(column="hightSleepEffCnt", property="hightsleepeffcnt", jdbcType=JdbcType.BIGINT),
        @Result(column="lowSleepTimeCnt", property="lowsleeptimecnt", jdbcType=JdbcType.BIGINT),
        @Result(column="middleSleepTimeCnt", property="middlesleeptimecnt", jdbcType=JdbcType.BIGINT),
        @Result(column="hightSleepTimeCnt", property="hightsleeptimecnt", jdbcType=JdbcType.BIGINT),
        @Result(column="level1SleepEff", property="level1sleepeff", jdbcType=JdbcType.BIGINT),
        @Result(column="level2SleepEff", property="level2sleepeff", jdbcType=JdbcType.BIGINT),
        @Result(column="level3SleepEff", property="level3sleepeff", jdbcType=JdbcType.BIGINT),
        @Result(column="level4SleepEff", property="level4sleepeff", jdbcType=JdbcType.BIGINT),
        @Result(column="level5SleepEff", property="level5sleepeff", jdbcType=JdbcType.BIGINT),
        @Result(column="SumTotalTime", property="sumtotaltime", jdbcType=JdbcType.BIGINT),
        @Result(column="SumTotalSleepT", property="sumtotalsleept", jdbcType=JdbcType.BIGINT),
        @Result(column="SumSleepEff", property="sumsleepeff", jdbcType=JdbcType.DECIMAL),
        @Result(column="SumQian", property="sumqian", jdbcType=JdbcType.BIGINT),
        @Result(column="SumShen", property="sumshen", jdbcType=JdbcType.BIGINT),
        @Result(column="SumSleepTime", property="sumsleeptime", jdbcType=JdbcType.BIGINT),
        @Result(column="MainID", property="mainid", jdbcType=JdbcType.INTEGER),
        @Result(column="UnFinishRecords", property="unfinishrecords", jdbcType=JdbcType.NVARCHAR),
        @Result(column="SumType", property="sumtype", jdbcType=JdbcType.INTEGER),
        @Result(column="Update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="HaveEdit", property="haveedit", jdbcType=JdbcType.INTEGER)
    })
    List<SummaryDeptPerson> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(summaryDeptPerson);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, summaryDeptPerson);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, summaryDeptPerson)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(SummaryDeptPerson record) {
        return insert(SqlBuilder.insert(record)
                .into(summaryDeptPerson)
                .map(deptid).toProperty("deptid")
                .map(date).toProperty("date")
                .map(totaldriver).toProperty("totaldriver")
                .map(totalactivebed).toProperty("totalactivebed")
                .map(totalbed).toProperty("totalbed")
                .map(totalusedriver).toProperty("totalusedriver")
                .map(totalusetime).toProperty("totalusetime")
                .map(avgusetime).toProperty("avgusetime")
                .map(avgsleept).toProperty("avgsleept")
                .map(avgsleepeff).toProperty("avgsleepeff")
                .map(sleeptroublecnt).toProperty("sleeptroublecnt")
                .map(autocallwakecnt).toProperty("autocallwakecnt")
                .map(artificalcallwakecnt).toProperty("artificalcallwakecnt")
                .map(ratioautocallwake).toProperty("ratioautocallwake")
                .map(warncnt).toProperty("warncnt")
                .map(avgqian).toProperty("avgqian")
                .map(ratioqian).toProperty("ratioqian")
                .map(avgshen).toProperty("avgshen")
                .map(ratioshen).toProperty("ratioshen")
                .map(avgsleeptime).toProperty("avgsleeptime")
                .map(ratiosleeptime).toProperty("ratiosleeptime")
                .map(lowsleepeffcnt).toProperty("lowsleepeffcnt")
                .map(middlesleepeffcnt).toProperty("middlesleepeffcnt")
                .map(hightsleepeffcnt).toProperty("hightsleepeffcnt")
                .map(lowsleeptimecnt).toProperty("lowsleeptimecnt")
                .map(middlesleeptimecnt).toProperty("middlesleeptimecnt")
                .map(hightsleeptimecnt).toProperty("hightsleeptimecnt")
                .map(level1sleepeff).toProperty("level1sleepeff")
                .map(level2sleepeff).toProperty("level2sleepeff")
                .map(level3sleepeff).toProperty("level3sleepeff")
                .map(level4sleepeff).toProperty("level4sleepeff")
                .map(level5sleepeff).toProperty("level5sleepeff")
                .map(sumtotaltime).toProperty("sumtotaltime")
                .map(sumtotalsleept).toProperty("sumtotalsleept")
                .map(sumsleepeff).toProperty("sumsleepeff")
                .map(sumqian).toProperty("sumqian")
                .map(sumshen).toProperty("sumshen")
                .map(sumsleeptime).toProperty("sumsleeptime")
                .map(mainid).toProperty("mainid")
                .map(unfinishrecords).toProperty("unfinishrecords")
                .map(sumtype).toProperty("sumtype")
                .map(updateTime).toProperty("updateTime")
                .map(haveedit).toProperty("haveedit")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(SummaryDeptPerson record) {
        return insert(SqlBuilder.insert(record)
                .into(summaryDeptPerson)
                .map(deptid).toPropertyWhenPresent("deptid", record::getDeptid)
                .map(date).toPropertyWhenPresent("date", record::getDate)
                .map(totaldriver).toPropertyWhenPresent("totaldriver", record::getTotaldriver)
                .map(totalactivebed).toPropertyWhenPresent("totalactivebed", record::getTotalactivebed)
                .map(totalbed).toPropertyWhenPresent("totalbed", record::getTotalbed)
                .map(totalusedriver).toPropertyWhenPresent("totalusedriver", record::getTotalusedriver)
                .map(totalusetime).toPropertyWhenPresent("totalusetime", record::getTotalusetime)
                .map(avgusetime).toPropertyWhenPresent("avgusetime", record::getAvgusetime)
                .map(avgsleept).toPropertyWhenPresent("avgsleept", record::getAvgsleept)
                .map(avgsleepeff).toPropertyWhenPresent("avgsleepeff", record::getAvgsleepeff)
                .map(sleeptroublecnt).toPropertyWhenPresent("sleeptroublecnt", record::getSleeptroublecnt)
                .map(autocallwakecnt).toPropertyWhenPresent("autocallwakecnt", record::getAutocallwakecnt)
                .map(artificalcallwakecnt).toPropertyWhenPresent("artificalcallwakecnt", record::getArtificalcallwakecnt)
                .map(ratioautocallwake).toPropertyWhenPresent("ratioautocallwake", record::getRatioautocallwake)
                .map(warncnt).toPropertyWhenPresent("warncnt", record::getWarncnt)
                .map(avgqian).toPropertyWhenPresent("avgqian", record::getAvgqian)
                .map(ratioqian).toPropertyWhenPresent("ratioqian", record::getRatioqian)
                .map(avgshen).toPropertyWhenPresent("avgshen", record::getAvgshen)
                .map(ratioshen).toPropertyWhenPresent("ratioshen", record::getRatioshen)
                .map(avgsleeptime).toPropertyWhenPresent("avgsleeptime", record::getAvgsleeptime)
                .map(ratiosleeptime).toPropertyWhenPresent("ratiosleeptime", record::getRatiosleeptime)
                .map(lowsleepeffcnt).toPropertyWhenPresent("lowsleepeffcnt", record::getLowsleepeffcnt)
                .map(middlesleepeffcnt).toPropertyWhenPresent("middlesleepeffcnt", record::getMiddlesleepeffcnt)
                .map(hightsleepeffcnt).toPropertyWhenPresent("hightsleepeffcnt", record::getHightsleepeffcnt)
                .map(lowsleeptimecnt).toPropertyWhenPresent("lowsleeptimecnt", record::getLowsleeptimecnt)
                .map(middlesleeptimecnt).toPropertyWhenPresent("middlesleeptimecnt", record::getMiddlesleeptimecnt)
                .map(hightsleeptimecnt).toPropertyWhenPresent("hightsleeptimecnt", record::getHightsleeptimecnt)
                .map(level1sleepeff).toPropertyWhenPresent("level1sleepeff", record::getLevel1sleepeff)
                .map(level2sleepeff).toPropertyWhenPresent("level2sleepeff", record::getLevel2sleepeff)
                .map(level3sleepeff).toPropertyWhenPresent("level3sleepeff", record::getLevel3sleepeff)
                .map(level4sleepeff).toPropertyWhenPresent("level4sleepeff", record::getLevel4sleepeff)
                .map(level5sleepeff).toPropertyWhenPresent("level5sleepeff", record::getLevel5sleepeff)
                .map(sumtotaltime).toPropertyWhenPresent("sumtotaltime", record::getSumtotaltime)
                .map(sumtotalsleept).toPropertyWhenPresent("sumtotalsleept", record::getSumtotalsleept)
                .map(sumsleepeff).toPropertyWhenPresent("sumsleepeff", record::getSumsleepeff)
                .map(sumqian).toPropertyWhenPresent("sumqian", record::getSumqian)
                .map(sumshen).toPropertyWhenPresent("sumshen", record::getSumshen)
                .map(sumsleeptime).toPropertyWhenPresent("sumsleeptime", record::getSumsleeptime)
                .map(mainid).toPropertyWhenPresent("mainid", record::getMainid)
                .map(unfinishrecords).toPropertyWhenPresent("unfinishrecords", record::getUnfinishrecords)
                .map(sumtype).toPropertyWhenPresent("sumtype", record::getSumtype)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(haveedit).toPropertyWhenPresent("haveedit", record::getHaveedit)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SummaryDeptPerson>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, deptid, date, totaldriver, totalactivebed, totalbed, totalusedriver, totalusetime, avgusetime, avgsleept, avgsleepeff, sleeptroublecnt, autocallwakecnt, artificalcallwakecnt, ratioautocallwake, warncnt, avgqian, ratioqian, avgshen, ratioshen, avgsleeptime, ratiosleeptime, lowsleepeffcnt, middlesleepeffcnt, hightsleepeffcnt, lowsleeptimecnt, middlesleeptimecnt, hightsleeptimecnt, level1sleepeff, level2sleepeff, level3sleepeff, level4sleepeff, level5sleepeff, sumtotaltime, sumtotalsleept, sumsleepeff, sumqian, sumshen, sumsleeptime, mainid, unfinishrecords, sumtype, updateTime, haveedit)
                .from(summaryDeptPerson);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SummaryDeptPerson>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, deptid, date, totaldriver, totalactivebed, totalbed, totalusedriver, totalusetime, avgusetime, avgsleept, avgsleepeff, sleeptroublecnt, autocallwakecnt, artificalcallwakecnt, ratioautocallwake, warncnt, avgqian, ratioqian, avgshen, ratioshen, avgsleeptime, ratiosleeptime, lowsleepeffcnt, middlesleepeffcnt, hightsleepeffcnt, lowsleeptimecnt, middlesleeptimecnt, hightsleeptimecnt, level1sleepeff, level2sleepeff, level3sleepeff, level4sleepeff, level5sleepeff, sumtotaltime, sumtotalsleept, sumsleepeff, sumqian, sumshen, sumsleeptime, mainid, unfinishrecords, sumtype, updateTime, haveedit)
                .from(summaryDeptPerson);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default SummaryDeptPerson selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, deptid, date, totaldriver, totalactivebed, totalbed, totalusedriver, totalusetime, avgusetime, avgsleept, avgsleepeff, sleeptroublecnt, autocallwakecnt, artificalcallwakecnt, ratioautocallwake, warncnt, avgqian, ratioqian, avgshen, ratioshen, avgsleeptime, ratiosleeptime, lowsleepeffcnt, middlesleepeffcnt, hightsleepeffcnt, lowsleeptimecnt, middlesleeptimecnt, hightsleeptimecnt, level1sleepeff, level2sleepeff, level3sleepeff, level4sleepeff, level5sleepeff, sumtotaltime, sumtotalsleept, sumsleepeff, sumqian, sumshen, sumsleeptime, mainid, unfinishrecords, sumtype, updateTime, haveedit)
                .from(summaryDeptPerson)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(SummaryDeptPerson record) {
        return UpdateDSL.updateWithMapper(this::update, summaryDeptPerson)
                .set(deptid).equalTo(record::getDeptid)
                .set(date).equalTo(record::getDate)
                .set(totaldriver).equalTo(record::getTotaldriver)
                .set(totalactivebed).equalTo(record::getTotalactivebed)
                .set(totalbed).equalTo(record::getTotalbed)
                .set(totalusedriver).equalTo(record::getTotalusedriver)
                .set(totalusetime).equalTo(record::getTotalusetime)
                .set(avgusetime).equalTo(record::getAvgusetime)
                .set(avgsleept).equalTo(record::getAvgsleept)
                .set(avgsleepeff).equalTo(record::getAvgsleepeff)
                .set(sleeptroublecnt).equalTo(record::getSleeptroublecnt)
                .set(autocallwakecnt).equalTo(record::getAutocallwakecnt)
                .set(artificalcallwakecnt).equalTo(record::getArtificalcallwakecnt)
                .set(ratioautocallwake).equalTo(record::getRatioautocallwake)
                .set(warncnt).equalTo(record::getWarncnt)
                .set(avgqian).equalTo(record::getAvgqian)
                .set(ratioqian).equalTo(record::getRatioqian)
                .set(avgshen).equalTo(record::getAvgshen)
                .set(ratioshen).equalTo(record::getRatioshen)
                .set(avgsleeptime).equalTo(record::getAvgsleeptime)
                .set(ratiosleeptime).equalTo(record::getRatiosleeptime)
                .set(lowsleepeffcnt).equalTo(record::getLowsleepeffcnt)
                .set(middlesleepeffcnt).equalTo(record::getMiddlesleepeffcnt)
                .set(hightsleepeffcnt).equalTo(record::getHightsleepeffcnt)
                .set(lowsleeptimecnt).equalTo(record::getLowsleeptimecnt)
                .set(middlesleeptimecnt).equalTo(record::getMiddlesleeptimecnt)
                .set(hightsleeptimecnt).equalTo(record::getHightsleeptimecnt)
                .set(level1sleepeff).equalTo(record::getLevel1sleepeff)
                .set(level2sleepeff).equalTo(record::getLevel2sleepeff)
                .set(level3sleepeff).equalTo(record::getLevel3sleepeff)
                .set(level4sleepeff).equalTo(record::getLevel4sleepeff)
                .set(level5sleepeff).equalTo(record::getLevel5sleepeff)
                .set(sumtotaltime).equalTo(record::getSumtotaltime)
                .set(sumtotalsleept).equalTo(record::getSumtotalsleept)
                .set(sumsleepeff).equalTo(record::getSumsleepeff)
                .set(sumqian).equalTo(record::getSumqian)
                .set(sumshen).equalTo(record::getSumshen)
                .set(sumsleeptime).equalTo(record::getSumsleeptime)
                .set(mainid).equalTo(record::getMainid)
                .set(unfinishrecords).equalTo(record::getUnfinishrecords)
                .set(sumtype).equalTo(record::getSumtype)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(haveedit).equalTo(record::getHaveedit);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(SummaryDeptPerson record) {
        return UpdateDSL.updateWithMapper(this::update, summaryDeptPerson)
                .set(deptid).equalToWhenPresent(record::getDeptid)
                .set(date).equalToWhenPresent(record::getDate)
                .set(totaldriver).equalToWhenPresent(record::getTotaldriver)
                .set(totalactivebed).equalToWhenPresent(record::getTotalactivebed)
                .set(totalbed).equalToWhenPresent(record::getTotalbed)
                .set(totalusedriver).equalToWhenPresent(record::getTotalusedriver)
                .set(totalusetime).equalToWhenPresent(record::getTotalusetime)
                .set(avgusetime).equalToWhenPresent(record::getAvgusetime)
                .set(avgsleept).equalToWhenPresent(record::getAvgsleept)
                .set(avgsleepeff).equalToWhenPresent(record::getAvgsleepeff)
                .set(sleeptroublecnt).equalToWhenPresent(record::getSleeptroublecnt)
                .set(autocallwakecnt).equalToWhenPresent(record::getAutocallwakecnt)
                .set(artificalcallwakecnt).equalToWhenPresent(record::getArtificalcallwakecnt)
                .set(ratioautocallwake).equalToWhenPresent(record::getRatioautocallwake)
                .set(warncnt).equalToWhenPresent(record::getWarncnt)
                .set(avgqian).equalToWhenPresent(record::getAvgqian)
                .set(ratioqian).equalToWhenPresent(record::getRatioqian)
                .set(avgshen).equalToWhenPresent(record::getAvgshen)
                .set(ratioshen).equalToWhenPresent(record::getRatioshen)
                .set(avgsleeptime).equalToWhenPresent(record::getAvgsleeptime)
                .set(ratiosleeptime).equalToWhenPresent(record::getRatiosleeptime)
                .set(lowsleepeffcnt).equalToWhenPresent(record::getLowsleepeffcnt)
                .set(middlesleepeffcnt).equalToWhenPresent(record::getMiddlesleepeffcnt)
                .set(hightsleepeffcnt).equalToWhenPresent(record::getHightsleepeffcnt)
                .set(lowsleeptimecnt).equalToWhenPresent(record::getLowsleeptimecnt)
                .set(middlesleeptimecnt).equalToWhenPresent(record::getMiddlesleeptimecnt)
                .set(hightsleeptimecnt).equalToWhenPresent(record::getHightsleeptimecnt)
                .set(level1sleepeff).equalToWhenPresent(record::getLevel1sleepeff)
                .set(level2sleepeff).equalToWhenPresent(record::getLevel2sleepeff)
                .set(level3sleepeff).equalToWhenPresent(record::getLevel3sleepeff)
                .set(level4sleepeff).equalToWhenPresent(record::getLevel4sleepeff)
                .set(level5sleepeff).equalToWhenPresent(record::getLevel5sleepeff)
                .set(sumtotaltime).equalToWhenPresent(record::getSumtotaltime)
                .set(sumtotalsleept).equalToWhenPresent(record::getSumtotalsleept)
                .set(sumsleepeff).equalToWhenPresent(record::getSumsleepeff)
                .set(sumqian).equalToWhenPresent(record::getSumqian)
                .set(sumshen).equalToWhenPresent(record::getSumshen)
                .set(sumsleeptime).equalToWhenPresent(record::getSumsleeptime)
                .set(mainid).equalToWhenPresent(record::getMainid)
                .set(unfinishrecords).equalToWhenPresent(record::getUnfinishrecords)
                .set(sumtype).equalToWhenPresent(record::getSumtype)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(haveedit).equalToWhenPresent(record::getHaveedit);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(SummaryDeptPerson record) {
        return UpdateDSL.updateWithMapper(this::update, summaryDeptPerson)
                .set(deptid).equalTo(record::getDeptid)
                .set(date).equalTo(record::getDate)
                .set(totaldriver).equalTo(record::getTotaldriver)
                .set(totalactivebed).equalTo(record::getTotalactivebed)
                .set(totalbed).equalTo(record::getTotalbed)
                .set(totalusedriver).equalTo(record::getTotalusedriver)
                .set(totalusetime).equalTo(record::getTotalusetime)
                .set(avgusetime).equalTo(record::getAvgusetime)
                .set(avgsleept).equalTo(record::getAvgsleept)
                .set(avgsleepeff).equalTo(record::getAvgsleepeff)
                .set(sleeptroublecnt).equalTo(record::getSleeptroublecnt)
                .set(autocallwakecnt).equalTo(record::getAutocallwakecnt)
                .set(artificalcallwakecnt).equalTo(record::getArtificalcallwakecnt)
                .set(ratioautocallwake).equalTo(record::getRatioautocallwake)
                .set(warncnt).equalTo(record::getWarncnt)
                .set(avgqian).equalTo(record::getAvgqian)
                .set(ratioqian).equalTo(record::getRatioqian)
                .set(avgshen).equalTo(record::getAvgshen)
                .set(ratioshen).equalTo(record::getRatioshen)
                .set(avgsleeptime).equalTo(record::getAvgsleeptime)
                .set(ratiosleeptime).equalTo(record::getRatiosleeptime)
                .set(lowsleepeffcnt).equalTo(record::getLowsleepeffcnt)
                .set(middlesleepeffcnt).equalTo(record::getMiddlesleepeffcnt)
                .set(hightsleepeffcnt).equalTo(record::getHightsleepeffcnt)
                .set(lowsleeptimecnt).equalTo(record::getLowsleeptimecnt)
                .set(middlesleeptimecnt).equalTo(record::getMiddlesleeptimecnt)
                .set(hightsleeptimecnt).equalTo(record::getHightsleeptimecnt)
                .set(level1sleepeff).equalTo(record::getLevel1sleepeff)
                .set(level2sleepeff).equalTo(record::getLevel2sleepeff)
                .set(level3sleepeff).equalTo(record::getLevel3sleepeff)
                .set(level4sleepeff).equalTo(record::getLevel4sleepeff)
                .set(level5sleepeff).equalTo(record::getLevel5sleepeff)
                .set(sumtotaltime).equalTo(record::getSumtotaltime)
                .set(sumtotalsleept).equalTo(record::getSumtotalsleept)
                .set(sumsleepeff).equalTo(record::getSumsleepeff)
                .set(sumqian).equalTo(record::getSumqian)
                .set(sumshen).equalTo(record::getSumshen)
                .set(sumsleeptime).equalTo(record::getSumsleeptime)
                .set(mainid).equalTo(record::getMainid)
                .set(unfinishrecords).equalTo(record::getUnfinishrecords)
                .set(sumtype).equalTo(record::getSumtype)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(haveedit).equalTo(record::getHaveedit)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(SummaryDeptPerson record) {
        return UpdateDSL.updateWithMapper(this::update, summaryDeptPerson)
                .set(deptid).equalToWhenPresent(record::getDeptid)
                .set(date).equalToWhenPresent(record::getDate)
                .set(totaldriver).equalToWhenPresent(record::getTotaldriver)
                .set(totalactivebed).equalToWhenPresent(record::getTotalactivebed)
                .set(totalbed).equalToWhenPresent(record::getTotalbed)
                .set(totalusedriver).equalToWhenPresent(record::getTotalusedriver)
                .set(totalusetime).equalToWhenPresent(record::getTotalusetime)
                .set(avgusetime).equalToWhenPresent(record::getAvgusetime)
                .set(avgsleept).equalToWhenPresent(record::getAvgsleept)
                .set(avgsleepeff).equalToWhenPresent(record::getAvgsleepeff)
                .set(sleeptroublecnt).equalToWhenPresent(record::getSleeptroublecnt)
                .set(autocallwakecnt).equalToWhenPresent(record::getAutocallwakecnt)
                .set(artificalcallwakecnt).equalToWhenPresent(record::getArtificalcallwakecnt)
                .set(ratioautocallwake).equalToWhenPresent(record::getRatioautocallwake)
                .set(warncnt).equalToWhenPresent(record::getWarncnt)
                .set(avgqian).equalToWhenPresent(record::getAvgqian)
                .set(ratioqian).equalToWhenPresent(record::getRatioqian)
                .set(avgshen).equalToWhenPresent(record::getAvgshen)
                .set(ratioshen).equalToWhenPresent(record::getRatioshen)
                .set(avgsleeptime).equalToWhenPresent(record::getAvgsleeptime)
                .set(ratiosleeptime).equalToWhenPresent(record::getRatiosleeptime)
                .set(lowsleepeffcnt).equalToWhenPresent(record::getLowsleepeffcnt)
                .set(middlesleepeffcnt).equalToWhenPresent(record::getMiddlesleepeffcnt)
                .set(hightsleepeffcnt).equalToWhenPresent(record::getHightsleepeffcnt)
                .set(lowsleeptimecnt).equalToWhenPresent(record::getLowsleeptimecnt)
                .set(middlesleeptimecnt).equalToWhenPresent(record::getMiddlesleeptimecnt)
                .set(hightsleeptimecnt).equalToWhenPresent(record::getHightsleeptimecnt)
                .set(level1sleepeff).equalToWhenPresent(record::getLevel1sleepeff)
                .set(level2sleepeff).equalToWhenPresent(record::getLevel2sleepeff)
                .set(level3sleepeff).equalToWhenPresent(record::getLevel3sleepeff)
                .set(level4sleepeff).equalToWhenPresent(record::getLevel4sleepeff)
                .set(level5sleepeff).equalToWhenPresent(record::getLevel5sleepeff)
                .set(sumtotaltime).equalToWhenPresent(record::getSumtotaltime)
                .set(sumtotalsleept).equalToWhenPresent(record::getSumtotalsleept)
                .set(sumsleepeff).equalToWhenPresent(record::getSumsleepeff)
                .set(sumqian).equalToWhenPresent(record::getSumqian)
                .set(sumshen).equalToWhenPresent(record::getSumshen)
                .set(sumsleeptime).equalToWhenPresent(record::getSumsleeptime)
                .set(mainid).equalToWhenPresent(record::getMainid)
                .set(unfinishrecords).equalToWhenPresent(record::getUnfinishrecords)
                .set(sumtype).equalToWhenPresent(record::getSumtype)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(haveedit).equalToWhenPresent(record::getHaveedit)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}