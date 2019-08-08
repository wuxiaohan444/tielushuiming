package com.jiangkai.framework.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangkai.framework.admin.bean.*;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.common.enums.Status;
import com.jiangkai.framework.admin.constant.Constant;
import com.jiangkai.framework.admin.mapper.CustomSQL;
import com.jiangkai.framework.admin.util.DateUtils;
import com.jiangkai.framework.admin.util.FormatUtils;
import com.jiangkai.framework.admin.util.LevelUtils;
import com.jiangkai.framework.source.mapper.*;
import com.jiangkai.framework.source.model.BaseData;
import com.jiangkai.framework.source.model.BedRecord;
import com.jiangkai.framework.source.model.Dept;
import com.jiangkai.framework.source.model.Driver;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SimpleSortSpecification;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanOrEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsLessThanOrEqualTo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: zvbb
 * Date: 2019/5/27
 * Description:
 */
@Component
@RequiredArgsConstructor
public class DriverService {
    private final DriverMapper driverMapper;
    private final BedRecordMapper bedRecordMapper;
    private final BaseDataMapper baseDataMapper;
    private final CustomSQL customSQL;
    private final DeptMapper deptMapper;

    public Result page(DriverBean driverBean) {
        if (Objects.isNull(driverBean.getPage())) {
            driverBean.setPage(Constant.PAGE);
        }
        if (Objects.isNull(driverBean.getLimit())) {
            driverBean.setLimit(Constant.LIMIT);
        }

        //分页
        PageHelper.startPage(driverBean.getPage(), driverBean.getLimit());

        //查询条件
        DriverDynamicSqlSupport.Driver driver = DriverDynamicSqlSupport.driver;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Driver>>> dsl = driverMapper.selectByExample();
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Driver>>>.QueryExpressionWhereBuilder whereBuilder = null;
        if (!Objects.isNull(driverBean.getNoOrName())) {
            whereBuilder = dsl.where(driver.driverNo, IsEqualTo.of(() -> driverBean.getNoOrName()))
                    .or(driver.driverName, IsEqualTo.of(() -> driverBean.getNoOrName()));
        }

        List<Driver> driverList;
        if (!Objects.isNull(whereBuilder)) {
            driverList = whereBuilder.build().execute();
        } else {
            driverList = dsl.build().execute();
        }

        return Result.success(driverList);
    }

    /**
     * @Description 基础数据
     */
    public Result baseInfo(DriverSumBean driverSumBean) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //司机编号
        String no = driverSumBean.getNo();
        Date startTime = driverSumBean.getStartTime();
        Date endTime = driverSumBean.getEndTime();
        BedRecordDynamicSqlSupport.BedRecord bedRecord = BedRecordDynamicSqlSupport.bedRecord;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecord>>>.QueryExpressionWhereBuilder whereBuilder
                = bedRecordMapper.selectByExample().where(bedRecord.driverWorkno, IsEqualTo.of(() -> no));
        if (!Objects.isNull(startTime)) {
            whereBuilder.and(bedRecord.checkindate, IsGreaterThanOrEqualTo.of(() -> startTime));
        }
        if (!Objects.isNull(endTime)) {
            whereBuilder.and(bedRecord.checkindate, IsLessThanOrEqualTo.of(() -> endTime));
        }

        List<BedRecord> bedRecordList = whereBuilder.build().execute();

        String driverName = null;
        Double sleepeffSum = 0D;
        Long totalsleepSum = 0L;
        Long sleepTimeSum = 0L;//单位:毫秒
        Long qianSum = 0L;//单位：秒
        Long shenSum = 0L;//单位：秒
        Long warnSum = 0L;
        for (int i = 0; i < bedRecordList.size(); i++) {
            BedRecord record = bedRecordList.get(i);
            driverName = record.getDriverName();

            Double sleepeff = record.getSleepeff();
            sleepeffSum += (sleepeff == null ? 0D : sleepeff);

            Integer totalsleept = record.getTotalsleept();
            totalsleepSum += (totalsleept == null ? 0 : totalsleept);

            Date sleeptimeDate = record.getSleeptime();
            long sleepTime = 0;
            if (Objects.nonNull(sleeptimeDate)) {
                sleepTime = DateUtils.UTCToCST(record.getSleeptime());
            }
            sleepTimeSum += sleepTime;

            Integer qian = record.getQian();
            qianSum += (qian == null ? 0 : qian * 5);

            Integer shen = record.getShen();
            shenSum += (shen == null ? 0 : shen * 5);

            Integer warn = record.getWarn();
            warnSum += (warn == null ? 0 : warn);
        }
        DriverBaseRes driverBaseRes = new DriverBaseRes();
        driverBaseRes.setDriverName(driverName);
        driverBaseRes.setUseTime(Long.valueOf(bedRecordList.size()));
        if (bedRecordList.size() > 0) {
            int size = bedRecordList.size();
            driverBaseRes.setSleepeff(Double.valueOf(sleepeffSum.doubleValue() / size));
            driverBaseRes.setTotalsleept(FormatUtils.minTime2HMS(totalsleepSum / size));
            driverBaseRes.setSleepTime(FormatUtils.msTime2HMS(sleepTimeSum / size));
            driverBaseRes.setQian(FormatUtils.secondTime2HMS(qianSum / size));
            driverBaseRes.setShen(FormatUtils.secondTime2HMS(shenSum / size));
            driverBaseRes.setWarn(warnSum / size);
        }
        driverBaseRes.setTimeSpan(this.getSepcialTimeStr(driverSumBean.getStartTime(), driverSumBean.getEndTime()));
        return Result.success(driverBaseRes);
    }

    /**
     * @Description 心率曲线、呼吸曲线
     */
    public Result htBrTrend(DriverSumBean driverSumBean) {
        //司机编号
        String no = driverSumBean.getNo();
        if (Objects.isNull(no) || no.equals("")) {
            return Result.paramError("司机编号错误!");
        }
        Date startTime = driverSumBean.getStartTime();
        Date endTime = driverSumBean.getEndTime();

        BedRecordDynamicSqlSupport.BedRecord bedRecord = BedRecordDynamicSqlSupport.bedRecord;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecord>>>.QueryExpressionWhereBuilder whereBuilder
                = bedRecordMapper.selectByExample().where(bedRecord.driverWorkno, IsEqualTo.of(() -> no));
        if (!Objects.isNull(startTime)) {
            whereBuilder.and(bedRecord.checkindate, IsGreaterThanOrEqualTo.of(() -> startTime));
        }
        if (!Objects.isNull(endTime)) {
            whereBuilder.and(bedRecord.checkindate, IsLessThanOrEqualTo.of(() -> endTime));
        }
        //排序
        whereBuilder.orderBy(SimpleSortSpecification.of(bedRecord.checkindate.name()));
        //执行
        List<BedRecord> bedRecordList = whereBuilder.build().execute();

        List<Map<Date, Integer>> heartList = new ArrayList<>();
        List<Map<Date, Integer>> breathList = new ArrayList<>();
        DriverTrendBean driverTrendBean = new DriverTrendBean();
        DriverTrendBean.HeartDetail heartDetail = driverTrendBean.getHeartDetail();
        DriverTrendBean.BreathDetail breathDetail = driverTrendBean.getBreathDetail();
        Long heartBeatSum = 0L;
        Long breathSum = 0L;
        for (int i = 0; i < bedRecordList.size(); i++) {
            BedRecord record = bedRecordList.get(i);
            Date checkindate = record.getCheckindate();
            Integer avghb = record.getAvghb();
            Integer avgbr = record.getAvgbr();

            HashMap<Date, Integer> heartNode = new HashMap<>();
            heartNode.put(checkindate, avghb);
            heartList.add(heartNode);

            HashMap<Date, Integer> breathNode = new HashMap<>();
            breathNode.put(checkindate, avgbr);
            breathList.add(breathNode);
        }
        List<List<Map<Date, Integer>>> result = new ArrayList<>(2);
        //趋势图信息
        driverTrendBean.getTrend().add(heartList);
        driverTrendBean.getTrend().add(breathList);
        //心跳信息
        DriverTrendBean.HeartDetail driverHeartInfo = customSQL.getDriverHeartInfo(no, startTime, endTime);
        driverTrendBean.setHeartDetail(driverHeartInfo);
        //呼吸信息
        DriverTrendBean.BreathDetail driverBreathfo = customSQL.getDriverBreathfo(no, startTime, endTime);
        driverTrendBean.setBreathDetail(driverBreathfo);
        return Result.success(driverTrendBean);
    }

    /**
     * @Description 睡眠效率曲线图
     */
    public Result sleepEffTrend(DriverSumBean driverSumBean) {
        //司机编号
        String no = driverSumBean.getNo();
        if (Objects.isNull(no) || no.equals("")) {
            return Result.paramError("司机编号错误!");
        }
        Date startTime = driverSumBean.getStartTime();
        Date endTime = driverSumBean.getEndTime();

        BedRecordDynamicSqlSupport.BedRecord bedRecord = BedRecordDynamicSqlSupport.bedRecord;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecord>>>.QueryExpressionWhereBuilder whereBuilder
                = bedRecordMapper.selectByExample().where(bedRecord.driverWorkno, IsEqualTo.of(() -> no));
        if (!Objects.isNull(startTime)) {
            whereBuilder.and(bedRecord.checkindate, IsGreaterThanOrEqualTo.of(() -> startTime));
        }
        if (!Objects.isNull(endTime)) {
            whereBuilder.and(bedRecord.checkindate, IsLessThanOrEqualTo.of(() -> endTime));
        }
        //排序
        whereBuilder.orderBy(SimpleSortSpecification.of(bedRecord.checkindate.name()));
        //执行
        List<BedRecord> bedRecordList = whereBuilder.build().execute();

        List<Map<Date, Double>> trend = new ArrayList<>();
        for (int i = 0; i < bedRecordList.size(); i++) {
            BedRecord record = bedRecordList.get(i);
            Date checkindate = record.getCheckindate();
            Double sleepeff = record.getSleepeff();
            HashMap<Date, Double> node = new HashMap<>();
            node.put(checkindate, sleepeff);
            trend.add(node);
        }

        return Result.success(trend);
    }

    /**
     * @Description 入睡时长曲线图
     */
    public Result sleepTimeTrend(DriverSumBean driverSumBean) {
        //司机编号
        String no = driverSumBean.getNo();
        if (Objects.isNull(no) || no.equals("")) {
            return Result.paramError("司机编号错误!");
        }
        Date startTime = driverSumBean.getStartTime();
        Date endTime = driverSumBean.getEndTime();

        BedRecordDynamicSqlSupport.BedRecord bedRecord = BedRecordDynamicSqlSupport.bedRecord;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecord>>>.QueryExpressionWhereBuilder whereBuilder
                = bedRecordMapper.selectByExample().where(bedRecord.driverWorkno, IsEqualTo.of(() -> no));
        if (!Objects.isNull(startTime)) {
            whereBuilder.and(bedRecord.checkindate, IsGreaterThanOrEqualTo.of(() -> startTime));
        }
        if (!Objects.isNull(endTime)) {
            whereBuilder.and(bedRecord.checkindate, IsLessThanOrEqualTo.of(() -> endTime));
        }
        //排序
        whereBuilder.orderBy(SimpleSortSpecification.of(bedRecord.checkindate.name()));
        //执行
        List<BedRecord> bedRecordList = whereBuilder.build().execute();

        List<Map<Date, BigDecimal>> trend = new ArrayList<>();
        for (int i = 0; i < bedRecordList.size(); i++) {
            BedRecord record = bedRecordList.get(i);
            Date checkindate = record.getCheckindate();
            Date sleeptime = record.getSleeptime();
            HashMap<Date, BigDecimal> node = new HashMap<>();
            BigDecimal value = BigDecimal.ZERO;
            if (Objects.nonNull(sleeptime)) {
                //转换入睡时长的单位(单位：分钟)
                value = BigDecimal.valueOf(DateUtils.UTCToCST(sleeptime)).divide(BigDecimal.valueOf(60 * 1000), 2, RoundingMode.HALF_UP);
            }
            node.put(checkindate, value);
            trend.add(node);
        }
        return Result.success(trend);
    }

    /**
     * @Description 三个空心饼图
     */
    public Result ringSleepKpi(DriverSumBean driverSumBean) {
        //司机编号
        String no = driverSumBean.getNo();
        if (Objects.isNull(no) || no.equals("")) {
            return Result.paramError("司机编号错误!");
        }
        Date startTime = driverSumBean.getStartTime();
        Date endTime = driverSumBean.getEndTime();
        BedRecordDynamicSqlSupport.BedRecord bedRecord = BedRecordDynamicSqlSupport.bedRecord;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecord>>>.QueryExpressionWhereBuilder whereBuilder
                = bedRecordMapper.selectByExample().where(bedRecord.driverWorkno, IsEqualTo.of(() -> no));
        if (Objects.nonNull(startTime)) {
            whereBuilder.and(bedRecord.checkindate, IsGreaterThanOrEqualTo.of(() -> startTime));
        }
        if (Objects.nonNull(endTime)) {
            whereBuilder.and(bedRecord.checkindate, IsLessThanOrEqualTo.of(() -> endTime));
        }

        List<BedRecord> bedRecordList = whereBuilder.build().execute();
        //平均睡眠效率：时间范围内Info_Inteface表中此人SleepEff字段计算平均值
        Double sleepeffSum = 0D;
        //平均睡眠时长：时间范围内Info_Inteface表中此人TotalsleepT字段求平均
        Long totalsleepSum = 0L;
        //平均入睡时长：时间范围内Info_Inteface表中此人SleepTime字段求平均
        Long sleepTimeSum = 0L;
        for (int i = 0; i < bedRecordList.size(); i++) {
            BedRecord record = bedRecordList.get(i);

            Double sleepeff = record.getSleepeff();
            sleepeffSum += (sleepeff == null ? 0D : sleepeff);

            Integer totalsleept = record.getTotalsleept();
            totalsleepSum += (totalsleept == null ? 0 : totalsleept);

            Date sleeptimeDate = record.getSleeptime();
            long sleepTime = 0;
            if (Objects.nonNull(sleeptimeDate)) {
                sleepTime = DateUtils.UTCToCST(record.getSleeptime());
            }
            sleepTimeSum += sleepTime;
        }
        DriverRingRes ringRes = new DriverRingRes();
        if (bedRecordList.size() > 0) {
            int size = bedRecordList.size();
            //平均睡眠效率
            BigDecimal avgSleepEff = BigDecimal.valueOf(sleepeffSum.doubleValue()).divide(BigDecimal.valueOf(size), 2, RoundingMode.HALF_UP);
            ringRes.setSleepeff(avgSleepEff);
            //平均睡眠时长
            String avgTotalSleep = FormatUtils.minTime2HMS(totalsleepSum / size);
            ringRes.setTotalsleept(avgTotalSleep);
            //平均入睡时长
            String avgSleepTime = FormatUtils.msTime2HMS(sleepTimeSum / size);
            ringRes.setSleepTime(avgSleepTime);

            ringRes.setSleepeffLevel(LevelUtils.avgSleepEffLevel(sleepeffSum.doubleValue() / size));
            ringRes.setTotalsleeptLevel(LevelUtils.avgTotalsleepLevel(totalsleepSum / size));
            ringRes.setSleepTimeLevel(LevelUtils.avgSleepTimeLevel(sleepTimeSum / size));
        }
        return Result.success(ringRes);
    }

    /**
     * @Description 睡眠综合分析
     */
    public Result sleepAnalysis(DriverSumBean driverSumBean) {
        //司机编号
        String no = driverSumBean.getNo();
        if (Objects.isNull(no) || no.equals("")) {
            return Result.paramError("司机编号错误!");
        }
        Date startTime = driverSumBean.getStartTime();
        Date endTime = driverSumBean.getEndTime();

        BedRecordDynamicSqlSupport.BedRecord bedRecord = BedRecordDynamicSqlSupport.bedRecord;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecord>>>.QueryExpressionWhereBuilder whereBuilder
                = bedRecordMapper.selectByExample().where(bedRecord.driverWorkno, IsEqualTo.of(() -> no));
        if (Objects.nonNull(startTime)) {
            whereBuilder.and(bedRecord.checkindate, IsGreaterThanOrEqualTo.of(() -> startTime));
        }
        if (Objects.nonNull(endTime)) {
            whereBuilder.and(bedRecord.checkindate, IsLessThanOrEqualTo.of(() -> endTime));
        }
        //排序
        whereBuilder.orderBy(SimpleSortSpecification.of(bedRecord.checkindate.name()));
        //执行
        List<BedRecord> bedRecordList = whereBuilder.build().execute();

        Integer sleepScoreSum = 0;
        //遍历
        for (int i = 0; i < bedRecordList.size(); i++) {
            BedRecord record = bedRecordList.get(i);
            Integer sleepscore = record.getSleepscore();
            if (Objects.nonNull(sleepscore)) {
                sleepScoreSum += sleepscore;
            }
        }
        int score = -1;
        if (bedRecordList.size() > 0) {
            int size = bedRecordList.size();
            score = sleepScoreSum.intValue() / size;
        }

        if (score != -1 && score >= 0 && score <= 6 && score != 5) {
            BaseDataDynamicSqlSupport.BaseData baseData = BaseDataDynamicSqlSupport.baseData;
            int finalScore = score;
            List<BaseData> baseDataList = baseDataMapper.selectByExample()
                    .where(baseData.value, IsEqualTo.of(() -> finalScore))
                    .build()
                    .execute();
            return Result.success(baseDataList);
        } else {
            if (score == -1) {
                return Result.businessError("该司机该时间段没有睡眠记录!");
            }
            if (!(score >= 0 && score <= 6 && score != 5)) {
                return Result.businessError("没有睡眠比对值为" + score + "的基础数据.");
            }
            return Result.success();
        }
    }

    /**
     * @param
     * @return
     * @Description 监测记录
     * @date 2019/6/18
     * @auther zvbb
     */
    public Result monitorRecords(DriverSumBean driverSumBean) {
        if (driverSumBean.getPage() == null) {
            driverSumBean.setPage(Constant.PAGE);
        }
        if (driverSumBean.getLimit() == null) {
            driverSumBean.setLimit(Constant.LIMIT);
        }
        //分页
        PageHelper.startPage(driverSumBean.getPage(), driverSumBean.getLimit());

        //司机编号
        String no = driverSumBean.getNo();
        //开始时间
        Date startTime = driverSumBean.getStartTime();
        //结束时间
        Date endTime = driverSumBean.getEndTime();
        BedRecordDynamicSqlSupport.BedRecord bedRecord = BedRecordDynamicSqlSupport.bedRecord;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BedRecord>>>.QueryExpressionWhereBuilder whereBuilder
                = bedRecordMapper.selectByExample()
                .where(bedRecord.isupload, IsEqualTo.of(() -> Status.ENABLE.getCode()))
                .and(bedRecord.driverWorkno, IsEqualTo.of(() -> no));
        if (!Objects.isNull(startTime)) {
            whereBuilder.and(bedRecord.checkindate, IsGreaterThanOrEqualTo.of(() -> startTime));
        }
        if (!Objects.isNull(endTime)) {
            whereBuilder.and(bedRecord.checkindate, IsLessThanOrEqualTo.of(() -> endTime));
        }

        List<BedRecord> bedRecordList = whereBuilder
                .orderBy(SimpleSortSpecification.of(bedRecord.checkindate.name()).descending())
                .build()
                .execute();
        List<DriverMonitorRes> resList = new ArrayList<>();
        for (int i = 0; i < bedRecordList.size(); i++) {
            BedRecord record = bedRecordList.get(i);
            Date checkindate = record.getCheckindate();
            String reportguid = record.getReportguid();

            DriverMonitorRes driverMonitorRes = new DriverMonitorRes();
            driverMonitorRes.setMonitorTime(checkindate);
            driverMonitorRes.setReportguid(reportguid);
            //间休地址
            Long deptId = record.getDeptId();
            Dept dept = null;
            if (Objects.nonNull(deptId)) {
                dept = deptMapper.selectByPrimaryKey(deptId);
            }
            if (Objects.nonNull(dept)) {
                driverMonitorRes.setRestAddr(dept.getName());
            }
            resList.add(driverMonitorRes);
        }
        return Result.page(new PageInfo(resList));
    }

    /**
     * @param no 司机编号
     * @return
     * @Description TODO
     * @date 2019/6/18
     * @auther zvbb
     */
    public Result driverInfo(String no) {
        if (Objects.isNull(no)) {
            return Result.businessError("参数错误");
        }
        DriverDynamicSqlSupport.Driver driver = DriverDynamicSqlSupport.driver;
        List<Driver> driverList = driverMapper.selectByExample()
                .where(driver.driverNo, IsEqualTo.of(() -> no))
                .build()
                .execute();
        HashMap<Object, Object> resultMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(driverList)) {
            Driver record = driverList.get(0);
            String driverName = record.getDriverName();
            Long deptId = record.getDeptId();
            resultMap.put("deptid", deptId);
            resultMap.put("name", driverName);
            resultMap.put("no", no);
        }
        return Result.success(resultMap);
    }


    private static String getSepcialTimeStr(Date start, Date end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String startStr = sdf.format(start);
        String endStr = sdf.format(end);
        return startStr + " - " + endStr;
    }
}
