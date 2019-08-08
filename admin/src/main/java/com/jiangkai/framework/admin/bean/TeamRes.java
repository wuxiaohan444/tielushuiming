package com.jiangkai.framework.admin.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * User: zvbb
 * Date: 2019/6/14
 * Description:
 */
@Data
public class TeamRes {
    //总人数
    private Long totaldriver;
    //总床位数
    private Long totalbed;
    //使用人数
    private Long totalusedriver;
    //使用人数
    private Long totalusetime;
    //人均使用时长
    private String avgusetime;
    //人均睡眠时长
    private String avgsleept;
    //人均睡眠效率
    private BigDecimal avgsleepeff;
    //入睡困难人数
    private Long sleeptroublecnt;
    //自动叫班次数
    private Long autocallwakecnt;
    //人工叫班次数
    private Long artificalcallwakecnt;
    //自动叫班准确率
    private BigDecimal ratioautocallwake;
    //预警次数
    private Long warncnt;
    //人均浅睡时长
    private String avgqian;
    //人均深睡时长
    private String avgShen;
    //人均浅睡时长百分比
    private BigDecimal ratioqian;
    //人均深睡时长百分比
    private BigDecimal ratioshen;
}
