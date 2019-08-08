package com.jiangkai.framework.extdata.mapper;

import static com.jiangkai.framework.extdata.mapper.InfoIntefaceDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.extdata.model.InfoInteface;
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
public interface InfoIntefaceMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.mainid")
    int insert(InsertStatementProvider<InfoInteface> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("InfoIntefaceResult")
    InfoInteface selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="InfoIntefaceResult", value = {
        @Result(column="MainID", property="mainid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Bed_SerialNo", property="bedSerialno", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Driver_WorkNo", property="driverWorkno", jdbcType=JdbcType.NVARCHAR),
        @Result(column="CheckInDate", property="checkindate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CheckOutDate", property="checkoutdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="WakeUpDate", property="wakeupdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="YunAn_sid", property="yunanSid", jdbcType=JdbcType.INTEGER),
        @Result(column="Driver_Name", property="driverName", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Finish", property="finish", jdbcType=JdbcType.INTEGER),
        @Result(column="AvgBr", property="avgbr", jdbcType=JdbcType.INTEGER),
        @Result(column="AvgHb", property="avghb", jdbcType=JdbcType.INTEGER),
        @Result(column="SleepEff", property="sleepeff", jdbcType=JdbcType.DOUBLE),
        @Result(column="FirstOnbedDate", property="firstonbeddate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SleepTime", property="sleeptime", jdbcType=JdbcType.TIME),
        @Result(column="LastLeaveBedDate", property="lastleavebeddate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SleepScore", property="sleepscore", jdbcType=JdbcType.INTEGER),
        @Result(column="LeaveCount", property="leavecount", jdbcType=JdbcType.INTEGER),
        @Result(column="AutoCallWake", property="autocallwake", jdbcType=JdbcType.INTEGER),
        @Result(column="LeaveAllTime", property="leavealltime", jdbcType=JdbcType.TIME),
        @Result(column="MaxHb", property="maxhb", jdbcType=JdbcType.INTEGER),
        @Result(column="MaxBr", property="maxbr", jdbcType=JdbcType.INTEGER),
        @Result(column="MinHb", property="minhb", jdbcType=JdbcType.INTEGER),
        @Result(column="MinBr", property="minbr", jdbcType=JdbcType.INTEGER),
        @Result(column="TotalsleepT", property="totalsleept", jdbcType=JdbcType.INTEGER),
        @Result(column="TotalTime", property="totaltime", jdbcType=JdbcType.INTEGER),
        @Result(column="qian", property="qian", jdbcType=JdbcType.INTEGER),
        @Result(column="shen", property="shen", jdbcType=JdbcType.INTEGER),
        @Result(column="CloudGuid", property="cloudguid", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Warn", property="warn", jdbcType=JdbcType.INTEGER),
        @Result(column="ReportGUID", property="reportguid", jdbcType=JdbcType.CHAR),
        @Result(column="IsUpload", property="isupload", jdbcType=JdbcType.INTEGER)
    })
    List<InfoInteface> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(infoInteface);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, infoInteface);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer mainid_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, infoInteface)
                .where(mainid, isEqualTo(mainid_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(InfoInteface record) {
        return insert(SqlBuilder.insert(record)
                .into(infoInteface)
                .map(bedSerialno).toProperty("bedSerialno")
                .map(driverWorkno).toProperty("driverWorkno")
                .map(checkindate).toProperty("checkindate")
                .map(checkoutdate).toProperty("checkoutdate")
                .map(wakeupdate).toProperty("wakeupdate")
                .map(yunanSid).toProperty("yunanSid")
                .map(driverName).toProperty("driverName")
                .map(finish).toProperty("finish")
                .map(avgbr).toProperty("avgbr")
                .map(avghb).toProperty("avghb")
                .map(sleepeff).toProperty("sleepeff")
                .map(firstonbeddate).toProperty("firstonbeddate")
                .map(sleeptime).toProperty("sleeptime")
                .map(lastleavebeddate).toProperty("lastleavebeddate")
                .map(sleepscore).toProperty("sleepscore")
                .map(leavecount).toProperty("leavecount")
                .map(autocallwake).toProperty("autocallwake")
                .map(leavealltime).toProperty("leavealltime")
                .map(maxhb).toProperty("maxhb")
                .map(maxbr).toProperty("maxbr")
                .map(minhb).toProperty("minhb")
                .map(minbr).toProperty("minbr")
                .map(totalsleept).toProperty("totalsleept")
                .map(totaltime).toProperty("totaltime")
                .map(qian).toProperty("qian")
                .map(shen).toProperty("shen")
                .map(cloudguid).toProperty("cloudguid")
                .map(warn).toProperty("warn")
                .map(reportguid).toProperty("reportguid")
                .map(isupload).toProperty("isupload")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(InfoInteface record) {
        return insert(SqlBuilder.insert(record)
                .into(infoInteface)
                .map(bedSerialno).toPropertyWhenPresent("bedSerialno", record::getBedSerialno)
                .map(driverWorkno).toPropertyWhenPresent("driverWorkno", record::getDriverWorkno)
                .map(checkindate).toPropertyWhenPresent("checkindate", record::getCheckindate)
                .map(checkoutdate).toPropertyWhenPresent("checkoutdate", record::getCheckoutdate)
                .map(wakeupdate).toPropertyWhenPresent("wakeupdate", record::getWakeupdate)
                .map(yunanSid).toPropertyWhenPresent("yunanSid", record::getYunanSid)
                .map(driverName).toPropertyWhenPresent("driverName", record::getDriverName)
                .map(finish).toPropertyWhenPresent("finish", record::getFinish)
                .map(avgbr).toPropertyWhenPresent("avgbr", record::getAvgbr)
                .map(avghb).toPropertyWhenPresent("avghb", record::getAvghb)
                .map(sleepeff).toPropertyWhenPresent("sleepeff", record::getSleepeff)
                .map(firstonbeddate).toPropertyWhenPresent("firstonbeddate", record::getFirstonbeddate)
                .map(sleeptime).toPropertyWhenPresent("sleeptime", record::getSleeptime)
                .map(lastleavebeddate).toPropertyWhenPresent("lastleavebeddate", record::getLastleavebeddate)
                .map(sleepscore).toPropertyWhenPresent("sleepscore", record::getSleepscore)
                .map(leavecount).toPropertyWhenPresent("leavecount", record::getLeavecount)
                .map(autocallwake).toPropertyWhenPresent("autocallwake", record::getAutocallwake)
                .map(leavealltime).toPropertyWhenPresent("leavealltime", record::getLeavealltime)
                .map(maxhb).toPropertyWhenPresent("maxhb", record::getMaxhb)
                .map(maxbr).toPropertyWhenPresent("maxbr", record::getMaxbr)
                .map(minhb).toPropertyWhenPresent("minhb", record::getMinhb)
                .map(minbr).toPropertyWhenPresent("minbr", record::getMinbr)
                .map(totalsleept).toPropertyWhenPresent("totalsleept", record::getTotalsleept)
                .map(totaltime).toPropertyWhenPresent("totaltime", record::getTotaltime)
                .map(qian).toPropertyWhenPresent("qian", record::getQian)
                .map(shen).toPropertyWhenPresent("shen", record::getShen)
                .map(cloudguid).toPropertyWhenPresent("cloudguid", record::getCloudguid)
                .map(warn).toPropertyWhenPresent("warn", record::getWarn)
                .map(reportguid).toPropertyWhenPresent("reportguid", record::getReportguid)
                .map(isupload).toPropertyWhenPresent("isupload", record::getIsupload)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoInteface>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, mainid, bedSerialno, driverWorkno, checkindate, checkoutdate, wakeupdate, yunanSid, driverName, finish, avgbr, avghb, sleepeff, firstonbeddate, sleeptime, lastleavebeddate, sleepscore, leavecount, autocallwake, leavealltime, maxhb, maxbr, minhb, minbr, totalsleept, totaltime, qian, shen, cloudguid, warn, reportguid, isupload)
                .from(infoInteface);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<InfoInteface>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, mainid, bedSerialno, driverWorkno, checkindate, checkoutdate, wakeupdate, yunanSid, driverName, finish, avgbr, avghb, sleepeff, firstonbeddate, sleeptime, lastleavebeddate, sleepscore, leavecount, autocallwake, leavealltime, maxhb, maxbr, minhb, minbr, totalsleept, totaltime, qian, shen, cloudguid, warn, reportguid, isupload)
                .from(infoInteface);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default InfoInteface selectByPrimaryKey(Integer mainid_) {
        return SelectDSL.selectWithMapper(this::selectOne, mainid, bedSerialno, driverWorkno, checkindate, checkoutdate, wakeupdate, yunanSid, driverName, finish, avgbr, avghb, sleepeff, firstonbeddate, sleeptime, lastleavebeddate, sleepscore, leavecount, autocallwake, leavealltime, maxhb, maxbr, minhb, minbr, totalsleept, totaltime, qian, shen, cloudguid, warn, reportguid, isupload)
                .from(infoInteface)
                .where(mainid, isEqualTo(mainid_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(InfoInteface record) {
        return UpdateDSL.updateWithMapper(this::update, infoInteface)
                .set(bedSerialno).equalTo(record::getBedSerialno)
                .set(driverWorkno).equalTo(record::getDriverWorkno)
                .set(checkindate).equalTo(record::getCheckindate)
                .set(checkoutdate).equalTo(record::getCheckoutdate)
                .set(wakeupdate).equalTo(record::getWakeupdate)
                .set(yunanSid).equalTo(record::getYunanSid)
                .set(driverName).equalTo(record::getDriverName)
                .set(finish).equalTo(record::getFinish)
                .set(avgbr).equalTo(record::getAvgbr)
                .set(avghb).equalTo(record::getAvghb)
                .set(sleepeff).equalTo(record::getSleepeff)
                .set(firstonbeddate).equalTo(record::getFirstonbeddate)
                .set(sleeptime).equalTo(record::getSleeptime)
                .set(lastleavebeddate).equalTo(record::getLastleavebeddate)
                .set(sleepscore).equalTo(record::getSleepscore)
                .set(leavecount).equalTo(record::getLeavecount)
                .set(autocallwake).equalTo(record::getAutocallwake)
                .set(leavealltime).equalTo(record::getLeavealltime)
                .set(maxhb).equalTo(record::getMaxhb)
                .set(maxbr).equalTo(record::getMaxbr)
                .set(minhb).equalTo(record::getMinhb)
                .set(minbr).equalTo(record::getMinbr)
                .set(totalsleept).equalTo(record::getTotalsleept)
                .set(totaltime).equalTo(record::getTotaltime)
                .set(qian).equalTo(record::getQian)
                .set(shen).equalTo(record::getShen)
                .set(cloudguid).equalTo(record::getCloudguid)
                .set(warn).equalTo(record::getWarn)
                .set(reportguid).equalTo(record::getReportguid)
                .set(isupload).equalTo(record::getIsupload);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(InfoInteface record) {
        return UpdateDSL.updateWithMapper(this::update, infoInteface)
                .set(bedSerialno).equalToWhenPresent(record::getBedSerialno)
                .set(driverWorkno).equalToWhenPresent(record::getDriverWorkno)
                .set(checkindate).equalToWhenPresent(record::getCheckindate)
                .set(checkoutdate).equalToWhenPresent(record::getCheckoutdate)
                .set(wakeupdate).equalToWhenPresent(record::getWakeupdate)
                .set(yunanSid).equalToWhenPresent(record::getYunanSid)
                .set(driverName).equalToWhenPresent(record::getDriverName)
                .set(finish).equalToWhenPresent(record::getFinish)
                .set(avgbr).equalToWhenPresent(record::getAvgbr)
                .set(avghb).equalToWhenPresent(record::getAvghb)
                .set(sleepeff).equalToWhenPresent(record::getSleepeff)
                .set(firstonbeddate).equalToWhenPresent(record::getFirstonbeddate)
                .set(sleeptime).equalToWhenPresent(record::getSleeptime)
                .set(lastleavebeddate).equalToWhenPresent(record::getLastleavebeddate)
                .set(sleepscore).equalToWhenPresent(record::getSleepscore)
                .set(leavecount).equalToWhenPresent(record::getLeavecount)
                .set(autocallwake).equalToWhenPresent(record::getAutocallwake)
                .set(leavealltime).equalToWhenPresent(record::getLeavealltime)
                .set(maxhb).equalToWhenPresent(record::getMaxhb)
                .set(maxbr).equalToWhenPresent(record::getMaxbr)
                .set(minhb).equalToWhenPresent(record::getMinhb)
                .set(minbr).equalToWhenPresent(record::getMinbr)
                .set(totalsleept).equalToWhenPresent(record::getTotalsleept)
                .set(totaltime).equalToWhenPresent(record::getTotaltime)
                .set(qian).equalToWhenPresent(record::getQian)
                .set(shen).equalToWhenPresent(record::getShen)
                .set(cloudguid).equalToWhenPresent(record::getCloudguid)
                .set(warn).equalToWhenPresent(record::getWarn)
                .set(reportguid).equalToWhenPresent(record::getReportguid)
                .set(isupload).equalToWhenPresent(record::getIsupload);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(InfoInteface record) {
        return UpdateDSL.updateWithMapper(this::update, infoInteface)
                .set(bedSerialno).equalTo(record::getBedSerialno)
                .set(driverWorkno).equalTo(record::getDriverWorkno)
                .set(checkindate).equalTo(record::getCheckindate)
                .set(checkoutdate).equalTo(record::getCheckoutdate)
                .set(wakeupdate).equalTo(record::getWakeupdate)
                .set(yunanSid).equalTo(record::getYunanSid)
                .set(driverName).equalTo(record::getDriverName)
                .set(finish).equalTo(record::getFinish)
                .set(avgbr).equalTo(record::getAvgbr)
                .set(avghb).equalTo(record::getAvghb)
                .set(sleepeff).equalTo(record::getSleepeff)
                .set(firstonbeddate).equalTo(record::getFirstonbeddate)
                .set(sleeptime).equalTo(record::getSleeptime)
                .set(lastleavebeddate).equalTo(record::getLastleavebeddate)
                .set(sleepscore).equalTo(record::getSleepscore)
                .set(leavecount).equalTo(record::getLeavecount)
                .set(autocallwake).equalTo(record::getAutocallwake)
                .set(leavealltime).equalTo(record::getLeavealltime)
                .set(maxhb).equalTo(record::getMaxhb)
                .set(maxbr).equalTo(record::getMaxbr)
                .set(minhb).equalTo(record::getMinhb)
                .set(minbr).equalTo(record::getMinbr)
                .set(totalsleept).equalTo(record::getTotalsleept)
                .set(totaltime).equalTo(record::getTotaltime)
                .set(qian).equalTo(record::getQian)
                .set(shen).equalTo(record::getShen)
                .set(cloudguid).equalTo(record::getCloudguid)
                .set(warn).equalTo(record::getWarn)
                .set(reportguid).equalTo(record::getReportguid)
                .set(isupload).equalTo(record::getIsupload)
                .where(mainid, isEqualTo(record::getMainid))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(InfoInteface record) {
        return UpdateDSL.updateWithMapper(this::update, infoInteface)
                .set(bedSerialno).equalToWhenPresent(record::getBedSerialno)
                .set(driverWorkno).equalToWhenPresent(record::getDriverWorkno)
                .set(checkindate).equalToWhenPresent(record::getCheckindate)
                .set(checkoutdate).equalToWhenPresent(record::getCheckoutdate)
                .set(wakeupdate).equalToWhenPresent(record::getWakeupdate)
                .set(yunanSid).equalToWhenPresent(record::getYunanSid)
                .set(driverName).equalToWhenPresent(record::getDriverName)
                .set(finish).equalToWhenPresent(record::getFinish)
                .set(avgbr).equalToWhenPresent(record::getAvgbr)
                .set(avghb).equalToWhenPresent(record::getAvghb)
                .set(sleepeff).equalToWhenPresent(record::getSleepeff)
                .set(firstonbeddate).equalToWhenPresent(record::getFirstonbeddate)
                .set(sleeptime).equalToWhenPresent(record::getSleeptime)
                .set(lastleavebeddate).equalToWhenPresent(record::getLastleavebeddate)
                .set(sleepscore).equalToWhenPresent(record::getSleepscore)
                .set(leavecount).equalToWhenPresent(record::getLeavecount)
                .set(autocallwake).equalToWhenPresent(record::getAutocallwake)
                .set(leavealltime).equalToWhenPresent(record::getLeavealltime)
                .set(maxhb).equalToWhenPresent(record::getMaxhb)
                .set(maxbr).equalToWhenPresent(record::getMaxbr)
                .set(minhb).equalToWhenPresent(record::getMinhb)
                .set(minbr).equalToWhenPresent(record::getMinbr)
                .set(totalsleept).equalToWhenPresent(record::getTotalsleept)
                .set(totaltime).equalToWhenPresent(record::getTotaltime)
                .set(qian).equalToWhenPresent(record::getQian)
                .set(shen).equalToWhenPresent(record::getShen)
                .set(cloudguid).equalToWhenPresent(record::getCloudguid)
                .set(warn).equalToWhenPresent(record::getWarn)
                .set(reportguid).equalToWhenPresent(record::getReportguid)
                .set(isupload).equalToWhenPresent(record::getIsupload)
                .where(mainid, isEqualTo(record::getMainid))
                .build()
                .execute();
    }
}