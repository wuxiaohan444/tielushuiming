package com.jiangkai.framework.admin.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * User: zvbb
 * Date: 2019/6/14
 * Description:
 */
@Data
public class HomeRes {
    //总床位数
    private Long totalbed;
    //监测床位
    private Long totalactivebed;
    //使用人数
    private Long totalusetime;
    //预警人数
    private Long warncnt;
    //人均入睡时长
    private BigDecimal avgsleeptime;
    //人均浅睡时长
    private BigDecimal avgqian;
    //人均深睡时长
    private BigDecimal avgshen;
    //平均睡眠效率
    private BigDecimal avgsleepeff;
}
