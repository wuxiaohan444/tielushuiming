package com.jiangkai.framework.admin.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * User: zvbb
 * Date: 2019/6/25
 * Description:
 */
@Data
public class DriverRingRes {
    //平均睡眠效率
    private BigDecimal sleepeff;
    //平均睡眠时长
    private String totalsleept;
    //平均入睡时长
    private String sleepTime;
    //平均睡眠效率 等级
    private Integer sleepeffLevel;
    //平均睡眠时长 等级
    private Integer totalsleeptLevel;
    //平均入睡时长 等级
    private Integer sleepTimeLevel;
}
