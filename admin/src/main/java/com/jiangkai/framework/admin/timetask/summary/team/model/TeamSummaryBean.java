package com.jiangkai.framework.admin.timetask.summary.team.model;

import lombok.Data;

import java.util.Date;

/**
 * User: zvbb
 * Date: 2019/5/16
 * Description: 统计实体
 */
@Data
public class TeamSummaryBean {
    //（一）、前置信息
    //  1、单位名称：依据查询范围标识,集团、段、间休室/外公寓
    private String deptName;
    //  2、时间
    private Date date;

    //（二）、基本信息
    //  1、总人数：总司机数
    private long totalDriver;
    //  2、总床位数：总床位数
    private long totalBed;
    //  3、使用人数：目前版本同总人数
    private long totalUseDriver;
    //	4、使用次数：使用人次，Info_Inteface表中数据条目数
    private long totalUseTime;
    //	5、人均使用时长：Info_Inteface表TotalTime字段计算平均值
    private double avgUseTime;
    //	6、人均睡眠时长：Info_Inteface表TotalsleepT字段计算平均值
    private double avgSleepTime;
    //	7、人均睡眠效率：Info_Inteface表SleepEff字段计算平均值
    private double avgSleepEff;
    //	8、入睡困难人数：Info_Inteface表SleepTime字段的值大于30分钟
    private long sleepTroubleCnt;
    //	9、自动叫班次数：Info_Inteface表AutoCallWake字段为1的数量
    private long autoCallWakeCnt;
    //	10、人工叫班次数：Info_Inteface表AutoCallWake字段为0的数量
    private long artificalCallWakeCnt;
    //	11、自动叫班准确率：AutoCallWake字段为1的数量除以总数量得出百分比
    private double ratioAutoCallWake;
    //	12、预警次数：Info_Inteface表Warn字段全部相加得出次数
    private long warnCnt;
    //	13、人均浅睡时长：Info_Inteface表qian字段计算平均值
    private double avgQian;
    //	14、人均浅睡时长百分比：Info_Inteface表qian字段的合计总时间除以Info_Inteface表TotalsleepT字段的合计总时间，得出百分比
    private double ratioQian;
    //	15、人均深睡时长：Info_Inteface表shen字段计算平均值
    private double avgShen;
    //	16、人均深睡时长百分比：Info_Inteface表shen字段的合计总时间除以Info_Inteface表TotalsleepT字段的合计总时间，得出百分比
    private double ratioShen;

    // (辅助统计的变量)
    // 所有数据条目总和
    private long SumRecords;
    // 所有数据条目的 TotalTime字段 总和
    private long SumTotalTime;
    // 所有数据条目的 TotalsleepT字段 总和
    private long SumSleepT;
    // 所有数据条目的 SleepEff字段 总和
    private long SumSleepEff;
    // 所有数据条目的 qian字段 总和
    private long SumQian;
    // 所有数据条目 shen字段 总和
    private long SumShen;
}
