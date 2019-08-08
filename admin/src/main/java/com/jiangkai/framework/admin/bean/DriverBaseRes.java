package com.jiangkai.framework.admin.bean;

import lombok.Data;

/**
 * User: zvbb
 * Date: 2019/6/14
 * Description:
 */
@Data
public class DriverBaseRes {
    //1、姓名：Info_Inteface表Driver_Name字段
    private String driverName;
    //2、睡眠效率 与 3、综合睡眠评价
    //时间范围内Info_Inteface表中此人SleepEff字段计算平均值
    private Double sleepeff;
    //4、时间范围：此份报告数据对应的时间范围，2019年3月2日-2019年4月7日
    private String timeSpan;
    //5、使用次数：上面时间范围内Info_Inteface表中此人的记录数
    private Long useTime;
    //1、睡眠时长：时间范围内Info_Inteface表中此人TotalsleepT字段求平均
    private String totalsleept;
    //2、入睡时长：时间范围内Info_Inteface表中此人SleepTime字段求平均
    private String sleepTime;
    //3、浅睡时长：时间范围内Info_Inteface表中此人qian字段求平均
    private String qian;
    //4、深睡时长：时间范围内Info_Inteface表中此人shen字段求平均
    private String shen;
    //5、预警次数：时间范围内Info_Inteface表中此人Warn字段求平均
    private Long warn;
}
