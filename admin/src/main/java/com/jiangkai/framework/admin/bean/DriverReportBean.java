package com.jiangkai.framework.admin.bean;

import lombok.Data;

/**
 * User: zvbb
 * Date: 2019/5/18
 * Description:
 */
@Data
public class DriverReportBean {
    //（一）、前置信息
    //1、姓名：Info_Inteface表Driver_Name字段
    private String DriverName;
    //2、睡眠效率：时间范围内Info_Inteface表中此人SleepEff字段计算平均值
    private double sleepEff;
    //4、时间范围：此份报告数据对应的时间范围，2019年3月2日-2019年4月7日
    private String timeFrame;
    //5、使用次数：上面时间范围内Info_Inteface表中此人的记录数
    private long userTime;

    //（二）、基本信息（平均值）
    //1、睡眠时长：时间范围内Info_Inteface表中此人TotalsleepT字段求平均
    private double avgTotalsleepT;
    //2、入睡时长：时间范围内Info_Inteface表中此人SleepTime字段求平均
    private double avgSleepTime;
    //3、浅睡时长：时间范围内Info_Inteface表中此人qian字段求平均
    private double avgQian;
    //4、深睡时长：时间范围内Info_Inteface表中此人shen字段求平均
    private double avgShen;
    //5、预警次数：时间范围内Info_Inteface表中此人Warn字段求平均
    private double avgWarn;

    //（三）、趋势汇总
    //（1）、心率曲线：时间范围内Info_Inteface表中此人AvgHb字段依据时间正序绘制曲线
    private int[] sortAvgHb;
    //（2）、呼吸曲线：时间范围内Info_Inteface表中此人AvgBr字段依据时间正序绘制曲线
    private int[] sortAvgBr;
    //（3）、最低心率：时间范围内Info_Inteface表中此人MinHb字段取最小值
    private int minMinHb;
    //（4）、最高心率：时间范围内Info_Inteface表中此人MaxHb字段取最大值
    private int maxMaxHb;
    //（5）、平均心率：时间范围内Info_Inteface表中此人AvgHb字段计算平均值
    private double avgAvgHb;
    //（6）、最低呼吸：时间范围内Info_Inteface表中此人MinBr字段取最小值
    private int minMinBr;
    //（7）、最高呼吸：时间范围内Info_Inteface表中此人MaxBr字段取最大值
    private int maxMaxBr;
    //（8）、平均呼吸：时间范围内Info_Inteface表中此人AvgBr字段计算平均值
    private double avgAvgBr;

    //2、睡眠效率曲线图（0-100，%）
    // (1)、睡眠效率：时间范围内Info_Inteface表中此人SleepEff字段依据时间正序绘制曲线
    private int[] sortSleepEff;
    //3、入睡时长曲线图（0-60，分钟）
    // (1)、时间范围内Info_Inteface表中此人SleepTime字段依据时间正序绘制曲线
    private int[] sortSleepTime;
    //4、三个空心饼图（尽量类似控件实现）
    // (1)、平均睡眠效率：时间范围内Info_Inteface表中此人SleepEff字段计算平均值
    private double avgSleepEff;
    // (2)、平均睡眠时长：时间范围内Info_Inteface表中此人TotalsleepT字段求平均（同2.1）
    // (3)、平均入睡时长：时间范围内Info_Inteface表中此人SleepTime字段求平均（同2.2）

    //（四）、睡眠综合分析
    //1、睡眠状态：
    //（1）、比对值：时间范围内Info_Inteface表中此人SleepScore字段计算平均值（四舍五入）得到比对值
    private int avgSleepScore;
    //（2）、通过对比值查找对应睡眠状态文字
    private String sleepStatusDesc;
    //	2、监测维度：生理体征参数：心率、呼吸率、睡眠时长、引睡时长、浅睡、深睡、体动次数、体动，在床/离床监测、离床总次数、离床时间。
    private String monitorDimension;
    //  3、总体建议：通过对比值查找对应睡眠状态文字
    private String recommendation;
}
