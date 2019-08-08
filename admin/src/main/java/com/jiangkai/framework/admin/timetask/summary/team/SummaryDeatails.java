package com.jiangkai.framework.admin.timetask.summary.team;

import com.jiangkai.framework.admin.common.enums.ConstNumber;
import com.jiangkai.framework.admin.common.enums.SumType;
import com.jiangkai.framework.admin.timetask.JobManager;
import com.jiangkai.framework.admin.util.DateUtils;
import com.jiangkai.framework.admin.util.NumberUtils;
import com.jiangkai.framework.source.model.BedRecord;
import com.jiangkai.framework.source.model.SummaryDeptBed;
import com.jiangkai.framework.source.model.SummaryDeptPerson;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * User: zvbb
 * Date: 2019/6/27
 * Description:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SummaryDeatails {
    private final JobManager jobManager;

    /**
     * @param
     * @return
     * @Description 字段的统计细节
     * @date 2019/5/17
     * @auther zvbb
     */
    @Synchronized
    public void summaryDetails(SummaryDeptBed summaryDeptBed, List<BedRecord> bedRecordList, SumType sumType, Long deptID) {
        try {
            //辅助字段
            bedRecordList.stream()
                    .forEach((bedRecord -> {
                        try {
                            if (Objects.isNull(bedRecord.getWakeupdate()) || Objects.isNull(bedRecord.getFirstonbeddate())) {
                                log.error("原始数据异常,MainID:" + bedRecord.getMainid());
                                return;
                            }
                            //单位(秒)
                            summaryDeptBed.setSumtotaltime(summaryDeptBed.getSumtotaltime() + (bedRecord.getWakeupdate().getTime() - bedRecord.getFirstonbeddate().getTime()) / ConstNumber.SECONDE.getValue());
                            summaryDeptBed.setSumsleepeff(NumberUtils.bigDecimalAdd(summaryDeptBed.getSumsleepeff(), bedRecord.getSleepeff()));
                            summaryDeptBed.setSumqian(summaryDeptBed.getSumqian() + (bedRecord.getQian() == null ? 0 : bedRecord.getQian()));
                            summaryDeptBed.setSumshen(summaryDeptBed.getSumshen() + (bedRecord.getShen() == null ? 0 : bedRecord.getShen()));
                            summaryDeptBed.setSumtotalsleept((summaryDeptBed.getSumqian() + summaryDeptBed.getSumshen()) * 5);
                            summaryDeptBed.setSumsleeptime((summaryDeptBed.getSumsleeptime() == null ? 0 : summaryDeptBed.getSumsleeptime()) + (bedRecord.getSleeptime() == null ? 0 : (DateUtils.UTCToCST(bedRecord.getSleeptime()) / (60 * 1000))));
                            //	4、使用次数：使用人次，Info_Inteface表中数据条目数
                            summaryDeptBed.setTotalusetime(summaryDeptBed.getTotalusetime() + 1);

                            if (Objects.nonNull(bedRecord.getSleeptime())) {
                                //	8、入睡困难人数：Info_Inteface表SleepTime字段的值大于30分钟
                                if ((DateUtils.UTCToCST(bedRecord.getSleeptime()) / (60 * 1000)) > 30) {
                                    summaryDeptBed.setSleeptroublecnt(summaryDeptBed.getSleeptroublecnt() + 1);
                                }
                            }

                            if (!Objects.isNull(bedRecord.getAutocallwake())) {
                                //	9、自动叫班次数：Info_Inteface表AutoCallWake字段为1的数量
                                if (bedRecord.getAutocallwake() == 1) {
                                    summaryDeptBed.setAutocallwakecnt(summaryDeptBed.getAutocallwakecnt() + 1);
                                }
                            }

                            if (!Objects.isNull(bedRecord.getAutocallwake())) {
                                //	10、人工叫班次数：Info_Inteface表AutoCallWake字段为0的数量
                                if (bedRecord.getAutocallwake() == 0) {
                                    summaryDeptBed.setArtificalcallwakecnt(summaryDeptBed.getArtificalcallwakecnt() + 1);
                                }
                            }
                            //	12、预警次数：Info_Inteface表Warn字段全部相加得出次数
                            summaryDeptBed.setWarncnt(summaryDeptBed.getWarncnt() + (bedRecord.getWarn() == null ? 0 : bedRecord.getWarn()));

                            //  19-21、睡眠效率示意图（饼图）：Info_Inteface表SleepEff字段进行区间划分
                            if (Objects.nonNull(bedRecord.getSleepeff())) {
                                Double sleepeff = bedRecord.getSleepeff();
                                if (sleepeff < 60 && sleepeff >= 0) {
                                    summaryDeptBed.setLowsleepeffcnt((summaryDeptBed.getLowsleepeffcnt() == null ? 0 : summaryDeptBed.getLowsleepeffcnt()) + 1);
                                } else if (sleepeff >= 60 && sleepeff <= 80) {
                                    summaryDeptBed.setMiddlesleepeffcnt((summaryDeptBed.getMiddlesleepeffcnt() == null ? 0 : summaryDeptBed.getMiddlesleepeffcnt()) + 1);
                                } else if (sleepeff > 80) {
                                    summaryDeptBed.setHightsleepeffcnt((summaryDeptBed.getHightsleepeffcnt() == null ? 0 : summaryDeptBed.getHightsleepeffcnt()) + 1);
                                }
                            }

                            // 22-24、入睡时长示意图（饼图）：Info_Inteface表SleepTime字段进行区间划分
                            if (!Objects.isNull(bedRecord.getSleeptime())) {
                                long time = DateUtils.UTCToCST(bedRecord.getSleeptime()) / (60 * 1000);
                                if (time < 15 && time >= 0) {
                                    summaryDeptBed.setLowsleeptimecnt((summaryDeptBed.getLowsleeptimecnt() == null ? 0 : summaryDeptBed.getLowsleeptimecnt()) + 1);
                                } else if (time >= 15 && time <= 30) {
                                    summaryDeptBed.setMiddlesleeptimecnt((summaryDeptBed.getMiddlesleeptimecnt() == null ? 0 : summaryDeptBed.getMiddlesleeptimecnt()) + 1);
                                } else {
                                    summaryDeptBed.setHightsleeptimecnt((summaryDeptBed.getHightsleeptimecnt() == null ? 0 : summaryDeptBed.getHightsleeptimecnt()) + 1);
                                }
                            }
                            if (!Objects.isNull(bedRecord.getSleepeff())) {
                                Double sleepeff = bedRecord.getSleepeff();
                                if (sleepeff >= 0 && sleepeff < 24) {
                                    summaryDeptBed.setLevel1sleepeff((summaryDeptBed.getLevel1sleepeff() == null ? 0 : summaryDeptBed.getLevel1sleepeff()) + 1);
                                } else if (sleepeff >= 24 && sleepeff < 48) {
                                    summaryDeptBed.setLevel2sleepeff((summaryDeptBed.getLevel2sleepeff() == null ? 0 : summaryDeptBed.getLevel2sleepeff()) + 1);
                                } else if (sleepeff >= 48 && sleepeff < 68) {
                                    summaryDeptBed.setLevel3sleepeff((summaryDeptBed.getLevel3sleepeff() == null ? 0 : summaryDeptBed.getLevel3sleepeff()) + 1);
                                } else if (sleepeff >= 68 && sleepeff < 84) {
                                    summaryDeptBed.setLevel4sleepeff((summaryDeptBed.getLevel4sleepeff() == null ? 0 : summaryDeptBed.getLevel4sleepeff()) + 1);
                                } else if (sleepeff >= 84 && sleepeff <= 100) {
                                    summaryDeptBed.setLevel5sleepeff((summaryDeptBed.getLevel5sleepeff() == null ? 0 : summaryDeptBed.getLevel5sleepeff()) + 1);
                                }
                            }
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                    }));
            BedRecord bedRecord = bedRecordList.get(0);
            //  1、总人数：总司机数
            Long totaldriver = jobManager.getDeptDriverCnt(deptID);
            summaryDeptBed.setTotaldriver(totaldriver);

            //	2、总床位数：总床位数 (这里存在重复的床铺,这里需要做去重操作)
            Long totalBed = jobManager.getDeptBedCnt(deptID);
            summaryDeptBed.setTotalbed(totalBed);

            //	3、使用人数：目前版本同总人数
            summaryDeptBed.setTotalusedriver((long) totaldriver);

            // 使用过的床铺数
            Date checkindate = bedRecord.getCheckindate();
            Long deptActiveBedCnt = jobManager.getDeptActiveBedCnt(sumType, deptID, checkindate);
            summaryDeptBed.setTotalactivebed(deptActiveBedCnt);

            if (summaryDeptBed.getTotalusetime().longValue() != 0) {
                //	5、人均使用时长：Info_Inteface表TotalTime字段计算平均值
                summaryDeptBed.setAvgusetime(NumberUtils.bigDecimalDivide(summaryDeptBed.getSumtotaltime(), summaryDeptBed.getTotalusetime(), 2, RoundingMode.HALF_UP));

                //	6、人均睡眠时长：Info_Inteface表TotalsleepT字段计算平均值
                summaryDeptBed.setAvgsleept(NumberUtils.bigDecimalDivide(summaryDeptBed.getSumtotalsleept(), summaryDeptBed.getTotalusetime(), 2, RoundingMode.HALF_UP));

                //	7、人均睡眠效率：Info_Inteface表SleepEff字段计算平均值
                summaryDeptBed.setAvgsleepeff(NumberUtils.bigDecimalDivide(summaryDeptBed.getSumsleepeff(), summaryDeptBed.getTotalusetime(), 2, RoundingMode.HALF_UP));

                //	11、自动叫班准确率：AutoCallWake字段为1的数量除以总数量得出百分比
                summaryDeptBed.setRatioautocallwake(NumberUtils.bigDecimalDivide(summaryDeptBed.getAutocallwakecnt(), summaryDeptBed.getTotalusetime(), 4, RoundingMode.HALF_UP));

                //	13、人均浅睡时长：Info_Inteface表qian字段计算平均值
                //  说明：这里的qian是次数的意思,一次代表5秒
                summaryDeptBed.setAvgqian(NumberUtils.bigDecimalDivide(summaryDeptBed.getSumqian(), summaryDeptBed.getTotalusetime(), 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(5)));

                //	15、人均深睡时长：Info_Inteface表shen字段计算平均值
                summaryDeptBed.setAvgshen(NumberUtils.bigDecimalDivide(summaryDeptBed.getSumshen(), summaryDeptBed.getTotalusetime(), 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(5)));

                // 17、人均入睡时长:Info_Inteface表SleepTime字段计算平均值
                summaryDeptBed.setAvgsleeptime(NumberUtils.bigDecimalDivide(summaryDeptBed.getSumsleeptime(), summaryDeptBed.getTotalusetime(), 2, RoundingMode.HALF_UP));
            }

            if (summaryDeptBed.getSumtotalsleept().longValue() != 0) {
                //	14、人均浅睡时长百分比：Info_Inteface表qian字段的合计总时间除以Info_Inteface表TotalsleepT字段的合计总时间，得出百分比
                //  说明：这里的qian是次数的意思,一次代表5秒
                //  TotalsleepT的单位：秒
                summaryDeptBed.setRatioqian(NumberUtils.bigDecimalDivide(summaryDeptBed.getSumqian(), summaryDeptBed.getSumtotalsleept(), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(5)));
                //	16、人均深睡时长百分比：Info_Inteface表shen字段的合计总时间除以Info_Inteface表TotalsleepT字段的合计总时间，得出百分比
                summaryDeptBed.setRatioshen(NumberUtils.bigDecimalDivide(summaryDeptBed.getSumshen(), summaryDeptBed.getSumtotalsleept(), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(5)));
                //  18、人均入睡时长百分比：Info_Inteface表SleepTime字段的合计总时间除以Info_Inteface表TotalsleepT字段的合计总时间，得出百分比
                summaryDeptBed.setRatiosleeptime(NumberUtils.bigDecimalDivide(summaryDeptBed.getSumsleeptime(), summaryDeptBed.getSumtotalsleept(), 2, RoundingMode.HALF_UP));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Synchronized
    public void summaryDetails(SummaryDeptPerson summaryDeptPerson, List<BedRecord> bedRecordList, SumType sumType, Long deptID) {
        try {
            //辅助字段
            bedRecordList.stream()
                    .forEach((bedRecord -> {
                        try {
                            if (Objects.isNull(bedRecord.getWakeupdate()) || Objects.isNull(bedRecord.getFirstonbeddate())) {
                                log.error("原始数据异常,MainID:" + bedRecord.getMainid());
                                return;
                            }
                            //单位(秒)
                            summaryDeptPerson.setSumtotaltime(summaryDeptPerson.getSumtotaltime() + (bedRecord.getWakeupdate().getTime() - bedRecord.getFirstonbeddate().getTime()) / ConstNumber.SECONDE.getValue());
                            summaryDeptPerson.setSumsleepeff(NumberUtils.bigDecimalAdd(summaryDeptPerson.getSumsleepeff(), bedRecord.getSleepeff()));
                            summaryDeptPerson.setSumqian(summaryDeptPerson.getSumqian() + (bedRecord.getQian() == null ? 0 : bedRecord.getQian()));
                            summaryDeptPerson.setSumshen(summaryDeptPerson.getSumshen() + (bedRecord.getShen() == null ? 0 : bedRecord.getShen()));
                            summaryDeptPerson.setSumtotalsleept((summaryDeptPerson.getSumqian() + summaryDeptPerson.getSumshen()) * 5);
                            summaryDeptPerson.setSumsleeptime((summaryDeptPerson.getSumsleeptime() == null ? 0 : summaryDeptPerson.getSumsleeptime()) + (bedRecord.getSleeptime() == null ? 0 : (DateUtils.UTCToCST(bedRecord.getSleeptime()) / (60 * 1000))));
                            //	4、使用次数：使用人次，Info_Inteface表中数据条目数
                            summaryDeptPerson.setTotalusetime(summaryDeptPerson.getTotalusetime() + 1);

                            if (Objects.nonNull(bedRecord.getSleeptime())) {
                                //	8、入睡困难人数：Info_Inteface表SleepTime字段的值大于30分钟
                                if ((DateUtils.UTCToCST(bedRecord.getSleeptime()) / (60 * 1000)) > 30) {
                                    summaryDeptPerson.setSleeptroublecnt(summaryDeptPerson.getSleeptroublecnt() + 1);
                                }
                            }

                            if (!Objects.isNull(bedRecord.getAutocallwake())) {
                                //	9、自动叫班次数：Info_Inteface表AutoCallWake字段为1的数量
                                if (bedRecord.getAutocallwake() == 1) {
                                    summaryDeptPerson.setAutocallwakecnt(summaryDeptPerson.getAutocallwakecnt() + 1);
                                }
                            }

                            if (!Objects.isNull(bedRecord.getAutocallwake())) {
                                //	10、人工叫班次数：Info_Inteface表AutoCallWake字段为0的数量
                                if (bedRecord.getAutocallwake() == 0) {
                                    summaryDeptPerson.setArtificalcallwakecnt(summaryDeptPerson.getArtificalcallwakecnt() + 1);
                                }
                            }
                            //	12、预警次数：Info_Inteface表Warn字段全部相加得出次数
                            summaryDeptPerson.setWarncnt(summaryDeptPerson.getWarncnt() + (bedRecord.getWarn() == null ? 0 : bedRecord.getWarn()));

                            //  19-21、睡眠效率示意图（饼图）：Info_Inteface表SleepEff字段进行区间划分
                            if (Objects.nonNull(bedRecord.getSleepeff())) {
                                Double sleepeff = bedRecord.getSleepeff();
                                if (sleepeff < 60 && sleepeff >= 0) {
                                    summaryDeptPerson.setLowsleepeffcnt((summaryDeptPerson.getLowsleepeffcnt() == null ? 0 : summaryDeptPerson.getLowsleepeffcnt()) + 1);
                                } else if (sleepeff >= 60 && sleepeff <= 80) {
                                    summaryDeptPerson.setMiddlesleepeffcnt((summaryDeptPerson.getMiddlesleepeffcnt() == null ? 0 : summaryDeptPerson.getMiddlesleepeffcnt()) + 1);
                                } else if (sleepeff > 80) {
                                    summaryDeptPerson.setHightsleepeffcnt((summaryDeptPerson.getHightsleepeffcnt() == null ? 0 : summaryDeptPerson.getHightsleepeffcnt()) + 1);
                                }
                            }

                            // 22-24、入睡时长示意图（饼图）：Info_Inteface表SleepTime字段进行区间划分
                            if (!Objects.isNull(bedRecord.getSleeptime())) {
                                long time = DateUtils.UTCToCST(bedRecord.getSleeptime()) / (60 * 1000);
                                if (time < 15 && time >= 0) {
                                    summaryDeptPerson.setLowsleeptimecnt((summaryDeptPerson.getLowsleeptimecnt() == null ? 0 : summaryDeptPerson.getLowsleeptimecnt()) + 1);
                                } else if (time >= 15 && time <= 30) {
                                    summaryDeptPerson.setMiddlesleeptimecnt((summaryDeptPerson.getMiddlesleeptimecnt() == null ? 0 : summaryDeptPerson.getMiddlesleeptimecnt()) + 1);
                                } else {
                                    summaryDeptPerson.setHightsleeptimecnt((summaryDeptPerson.getHightsleeptimecnt() == null ? 0 : summaryDeptPerson.getHightsleeptimecnt()) + 1);
                                }
                            }
                            if (!Objects.isNull(bedRecord.getSleepeff())) {
                                Double sleepeff = bedRecord.getSleepeff();
                                if (sleepeff >= 0 && sleepeff < 24) {
                                    summaryDeptPerson.setLevel1sleepeff((summaryDeptPerson.getLevel1sleepeff() == null ? 0 : summaryDeptPerson.getLevel1sleepeff()) + 1);
                                } else if (sleepeff >= 24 && sleepeff < 48) {
                                    summaryDeptPerson.setLevel2sleepeff((summaryDeptPerson.getLevel2sleepeff() == null ? 0 : summaryDeptPerson.getLevel2sleepeff()) + 1);
                                } else if (sleepeff >= 48 && sleepeff < 68) {
                                    summaryDeptPerson.setLevel3sleepeff((summaryDeptPerson.getLevel3sleepeff() == null ? 0 : summaryDeptPerson.getLevel3sleepeff()) + 1);
                                } else if (sleepeff >= 68 && sleepeff < 84) {
                                    summaryDeptPerson.setLevel4sleepeff((summaryDeptPerson.getLevel4sleepeff() == null ? 0 : summaryDeptPerson.getLevel4sleepeff()) + 1);
                                } else if (sleepeff >= 84 && sleepeff <= 100) {
                                    summaryDeptPerson.setLevel5sleepeff((summaryDeptPerson.getLevel5sleepeff() == null ? 0 : summaryDeptPerson.getLevel5sleepeff()) + 1);
                                }
                            }
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                    }));
            BedRecord bedRecord = bedRecordList.get(0);
            //  1、总人数：总司机数
            Long totaldriver = jobManager.getDeptDriverCnt(deptID);
            summaryDeptPerson.setTotaldriver(totaldriver);

            //	2、总床位数：总床位数 (这里存在重复的床铺,这里需要做去重操作)
            Long totalBed = jobManager.getDeptBedCnt(deptID);
            summaryDeptPerson.setTotalbed(totalBed);

            //	3、使用人数：目前版本同总人数
            summaryDeptPerson.setTotalusedriver((long) totaldriver);

            // 使用过的床铺数
            Date checkindate = bedRecord.getCheckindate();
            Long deptActiveBedCnt = jobManager.getDeptActiveBedCnt(sumType, deptID, checkindate);
            summaryDeptPerson.setTotalactivebed(deptActiveBedCnt);

            if (summaryDeptPerson.getTotalusetime().longValue() != 0) {
                //	5、人均使用时长：Info_Inteface表TotalTime字段计算平均值
                summaryDeptPerson.setAvgusetime(NumberUtils.bigDecimalDivide(summaryDeptPerson.getSumtotaltime(), summaryDeptPerson.getTotalusetime(), 2, RoundingMode.HALF_UP));

                //	6、人均睡眠时长：Info_Inteface表TotalsleepT字段计算平均值
                summaryDeptPerson.setAvgsleept(NumberUtils.bigDecimalDivide(summaryDeptPerson.getSumtotalsleept(), summaryDeptPerson.getTotalusetime(), 2, RoundingMode.HALF_UP));

                //	7、人均睡眠效率：Info_Inteface表SleepEff字段计算平均值
                summaryDeptPerson.setAvgsleepeff(NumberUtils.bigDecimalDivide(summaryDeptPerson.getSumsleepeff(), summaryDeptPerson.getTotalusetime(), 2, RoundingMode.HALF_UP));

                //	11、自动叫班准确率：AutoCallWake字段为1的数量除以总数量得出百分比
                summaryDeptPerson.setRatioautocallwake(NumberUtils.bigDecimalDivide(summaryDeptPerson.getAutocallwakecnt(), summaryDeptPerson.getTotalusetime(), 4, RoundingMode.HALF_UP));

                //	13、人均浅睡时长：Info_Inteface表qian字段计算平均值
                //  说明：这里的qian是次数的意思,一次代表5秒
                summaryDeptPerson.setAvgqian(NumberUtils.bigDecimalDivide(summaryDeptPerson.getSumqian(), summaryDeptPerson.getTotalusetime(), 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(5)));

                //	15、人均深睡时长：Info_Inteface表shen字段计算平均值
                summaryDeptPerson.setAvgshen(NumberUtils.bigDecimalDivide(summaryDeptPerson.getSumshen(), summaryDeptPerson.getTotalusetime(), 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(5)));

                // 17、人均入睡时长:Info_Inteface表SleepTime字段计算平均值
                summaryDeptPerson.setAvgsleeptime(NumberUtils.bigDecimalDivide(summaryDeptPerson.getSumsleeptime(), summaryDeptPerson.getTotalusetime(), 2, RoundingMode.HALF_UP));
            }

            if (summaryDeptPerson.getSumtotalsleept().longValue() != 0) {
                //	14、人均浅睡时长百分比：Info_Inteface表qian字段的合计总时间除以Info_Inteface表TotalsleepT字段的合计总时间，得出百分比
                //  说明：这里的qian是次数的意思,一次代表5秒
                //  TotalsleepT的单位：秒
                summaryDeptPerson.setRatioqian(NumberUtils.bigDecimalDivide(summaryDeptPerson.getSumqian(), summaryDeptPerson.getSumtotalsleept(), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(5)));
                //	16、人均深睡时长百分比：Info_Inteface表shen字段的合计总时间除以Info_Inteface表TotalsleepT字段的合计总时间，得出百分比
                summaryDeptPerson.setRatioshen(NumberUtils.bigDecimalDivide(summaryDeptPerson.getSumshen(), summaryDeptPerson.getSumtotalsleept(), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(5)));
                //  18、人均入睡时长百分比：Info_Inteface表SleepTime字段的合计总时间除以Info_Inteface表TotalsleepT字段的合计总时间，得出百分比
                summaryDeptPerson.setRatiosleeptime(NumberUtils.bigDecimalDivide(summaryDeptPerson.getSumsleeptime(), summaryDeptPerson.getSumtotalsleept(), 2, RoundingMode.HALF_UP));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
