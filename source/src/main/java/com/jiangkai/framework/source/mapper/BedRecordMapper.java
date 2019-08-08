package com.jiangkai.framework.source.mapper;

import static com.jiangkai.framework.source.mapper.BedRecordDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.jiangkai.framework.source.model.BedRecord;
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
public interface BedRecordMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<BedRecord> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("BedRecordResult")
    BedRecord selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="BedRecordResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="MainID", property="mainid", jdbcType=JdbcType.INTEGER),
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
        @Result(column="MinBr", property="minbr", jdbcType=JdbcType.INTEGER),
        @Result(column="TotalsleepT", property="totalsleept", jdbcType=JdbcType.INTEGER),
        @Result(column="TotalTime", property="totaltime", jdbcType=JdbcType.INTEGER),
        @Result(column="qian", property="qian", jdbcType=JdbcType.INTEGER),
        @Result(column="shen", property="shen", jdbcType=JdbcType.INTEGER),
        @Result(column="CloudGuid", property="cloudguid", jdbcType=JdbcType.NVARCHAR),
        @Result(column="Warn", property="warn", jdbcType=JdbcType.INTEGER),
        @Result(column="data_source_id", property="dataSourceId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user", property="createUser", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_user", property="updateUser", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="bed_info_id", property="bedInfoId", jdbcType=JdbcType.BIGINT),
        @Result(column="MaxBr", property="maxbr", jdbcType=JdbcType.INTEGER),
        @Result(column="MinHb", property="minhb", jdbcType=JdbcType.INTEGER),
        @Result(column="driver_id", property="driverId", jdbcType=JdbcType.BIGINT),
        @Result(column="ReportGUID", property="reportguid", jdbcType=JdbcType.NVARCHAR),
        @Result(column="IsUpload", property="isupload", jdbcType=JdbcType.INTEGER),
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.BIGINT)
    })
    List<BedRecord> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(bedRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, bedRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, bedRecord)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(BedRecord record) {
        return insert(SqlBuilder.insert(record)
                .into(bedRecord)
                .map(mainid).toProperty("mainid")
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
                .map(minbr).toProperty("minbr")
                .map(totalsleept).toProperty("totalsleept")
                .map(totaltime).toProperty("totaltime")
                .map(qian).toProperty("qian")
                .map(shen).toProperty("shen")
                .map(cloudguid).toProperty("cloudguid")
                .map(warn).toProperty("warn")
                .map(dataSourceId).toProperty("dataSourceId")
                .map(createTime).toProperty("createTime")
                .map(createUser).toProperty("createUser")
                .map(updateTime).toProperty("updateTime")
                .map(updateUser).toProperty("updateUser")
                .map(status).toProperty("status")
                .map(bedInfoId).toProperty("bedInfoId")
                .map(maxbr).toProperty("maxbr")
                .map(minhb).toProperty("minhb")
                .map(driverId).toProperty("driverId")
                .map(reportguid).toProperty("reportguid")
                .map(isupload).toProperty("isupload")
                .map(deptId).toProperty("deptId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(BedRecord record) {
        return insert(SqlBuilder.insert(record)
                .into(bedRecord)
                .map(mainid).toPropertyWhenPresent("mainid", record::getMainid)
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
                .map(minbr).toPropertyWhenPresent("minbr", record::getMinbr)
                .map(totalsleept).toPropertyWhenPresent("totalsleept", record::getTotalsleept)
                .map(totaltime).toPropertyWhenPresent("totaltime", record::getTotaltime)
                .map(qian).toPropertyWhenPresent("qian", record::getQian)
                .map(shen).toPropertyWhenPresent("shen", record::getShen)
                .map(cloudguid).toPropertyWhenPresent("cloudguid", record::getCloudguid)
                .map(warn).toPropertyWhenPresent("warn", record::getWarn)
                .map(dataSourceId).toPropertyWhenPresent("dataSourceId", record::getDataSourceId)
                .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                .map(createUser).toPropertyWhenPresent("createUser", record::getCreateUser)
                .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                .map(updateUser).toPropertyWhenPresent("updateUser", record::getUpdateUser)
                .map(status).toPropertyWhenPresent("status", record::getStatus)
                .map(bedInfoId).toPropertyWhenPresent("bedInfoId", record::getBedInfoId)
                .map(maxbr).toPropertyWhenPresent("maxbr", record::getMaxbr)
                .map(minhb).toPropertyWhenPresent("minhb", record::getMinhb)
                .map(driverId).toPropertyWhenPresent("driverId", record::getDriverId)
                .map(reportguid).toPropertyWhenPresent("reportguid", record::getReportguid)
                .map(isupload).toPropertyWhenPresent("isupload", record::getIsupload)
                .map(deptId).toPropertyWhenPresent("deptId", record::getDeptId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecord>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, mainid, bedSerialno, driverWorkno, checkindate, checkoutdate, wakeupdate, yunanSid, driverName, finish, avgbr, avghb, sleepeff, firstonbeddate, sleeptime, lastleavebeddate, sleepscore, leavecount, autocallwake, leavealltime, maxhb, minbr, totalsleept, totaltime, qian, shen, cloudguid, warn, dataSourceId, createTime, createUser, updateTime, updateUser, status, bedInfoId, maxbr, minhb, driverId, reportguid, isupload, deptId)
                .from(bedRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecord>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, mainid, bedSerialno, driverWorkno, checkindate, checkoutdate, wakeupdate, yunanSid, driverName, finish, avgbr, avghb, sleepeff, firstonbeddate, sleeptime, lastleavebeddate, sleepscore, leavecount, autocallwake, leavealltime, maxhb, minbr, totalsleept, totaltime, qian, shen, cloudguid, warn, dataSourceId, createTime, createUser, updateTime, updateUser, status, bedInfoId, maxbr, minhb, driverId, reportguid, isupload, deptId)
                .from(bedRecord);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default BedRecord selectByPrimaryKey(Long id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, mainid, bedSerialno, driverWorkno, checkindate, checkoutdate, wakeupdate, yunanSid, driverName, finish, avgbr, avghb, sleepeff, firstonbeddate, sleeptime, lastleavebeddate, sleepscore, leavecount, autocallwake, leavealltime, maxhb, minbr, totalsleept, totaltime, qian, shen, cloudguid, warn, dataSourceId, createTime, createUser, updateTime, updateUser, status, bedInfoId, maxbr, minhb, driverId, reportguid, isupload, deptId)
                .from(bedRecord)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(BedRecord record) {
        return UpdateDSL.updateWithMapper(this::update, bedRecord)
                .set(mainid).equalTo(record::getMainid)
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
                .set(minbr).equalTo(record::getMinbr)
                .set(totalsleept).equalTo(record::getTotalsleept)
                .set(totaltime).equalTo(record::getTotaltime)
                .set(qian).equalTo(record::getQian)
                .set(shen).equalTo(record::getShen)
                .set(cloudguid).equalTo(record::getCloudguid)
                .set(warn).equalTo(record::getWarn)
                .set(dataSourceId).equalTo(record::getDataSourceId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(bedInfoId).equalTo(record::getBedInfoId)
                .set(maxbr).equalTo(record::getMaxbr)
                .set(minhb).equalTo(record::getMinhb)
                .set(driverId).equalTo(record::getDriverId)
                .set(reportguid).equalTo(record::getReportguid)
                .set(isupload).equalTo(record::getIsupload)
                .set(deptId).equalTo(record::getDeptId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(BedRecord record) {
        return UpdateDSL.updateWithMapper(this::update, bedRecord)
                .set(mainid).equalToWhenPresent(record::getMainid)
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
                .set(minbr).equalToWhenPresent(record::getMinbr)
                .set(totalsleept).equalToWhenPresent(record::getTotalsleept)
                .set(totaltime).equalToWhenPresent(record::getTotaltime)
                .set(qian).equalToWhenPresent(record::getQian)
                .set(shen).equalToWhenPresent(record::getShen)
                .set(cloudguid).equalToWhenPresent(record::getCloudguid)
                .set(warn).equalToWhenPresent(record::getWarn)
                .set(dataSourceId).equalToWhenPresent(record::getDataSourceId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(bedInfoId).equalToWhenPresent(record::getBedInfoId)
                .set(maxbr).equalToWhenPresent(record::getMaxbr)
                .set(minhb).equalToWhenPresent(record::getMinhb)
                .set(driverId).equalToWhenPresent(record::getDriverId)
                .set(reportguid).equalToWhenPresent(record::getReportguid)
                .set(isupload).equalToWhenPresent(record::getIsupload)
                .set(deptId).equalToWhenPresent(record::getDeptId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(BedRecord record) {
        return UpdateDSL.updateWithMapper(this::update, bedRecord)
                .set(mainid).equalTo(record::getMainid)
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
                .set(minbr).equalTo(record::getMinbr)
                .set(totalsleept).equalTo(record::getTotalsleept)
                .set(totaltime).equalTo(record::getTotaltime)
                .set(qian).equalTo(record::getQian)
                .set(shen).equalTo(record::getShen)
                .set(cloudguid).equalTo(record::getCloudguid)
                .set(warn).equalTo(record::getWarn)
                .set(dataSourceId).equalTo(record::getDataSourceId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(createUser).equalTo(record::getCreateUser)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(updateUser).equalTo(record::getUpdateUser)
                .set(status).equalTo(record::getStatus)
                .set(bedInfoId).equalTo(record::getBedInfoId)
                .set(maxbr).equalTo(record::getMaxbr)
                .set(minhb).equalTo(record::getMinhb)
                .set(driverId).equalTo(record::getDriverId)
                .set(reportguid).equalTo(record::getReportguid)
                .set(isupload).equalTo(record::getIsupload)
                .set(deptId).equalTo(record::getDeptId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(BedRecord record) {
        return UpdateDSL.updateWithMapper(this::update, bedRecord)
                .set(mainid).equalToWhenPresent(record::getMainid)
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
                .set(minbr).equalToWhenPresent(record::getMinbr)
                .set(totalsleept).equalToWhenPresent(record::getTotalsleept)
                .set(totaltime).equalToWhenPresent(record::getTotaltime)
                .set(qian).equalToWhenPresent(record::getQian)
                .set(shen).equalToWhenPresent(record::getShen)
                .set(cloudguid).equalToWhenPresent(record::getCloudguid)
                .set(warn).equalToWhenPresent(record::getWarn)
                .set(dataSourceId).equalToWhenPresent(record::getDataSourceId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(createUser).equalToWhenPresent(record::getCreateUser)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(updateUser).equalToWhenPresent(record::getUpdateUser)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(bedInfoId).equalToWhenPresent(record::getBedInfoId)
                .set(maxbr).equalToWhenPresent(record::getMaxbr)
                .set(minhb).equalToWhenPresent(record::getMinhb)
                .set(driverId).equalToWhenPresent(record::getDriverId)
                .set(reportguid).equalToWhenPresent(record::getReportguid)
                .set(isupload).equalToWhenPresent(record::getIsupload)
                .set(deptId).equalToWhenPresent(record::getDeptId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}