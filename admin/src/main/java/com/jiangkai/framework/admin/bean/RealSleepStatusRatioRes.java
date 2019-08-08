package com.jiangkai.framework.admin.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * User: zvbb
 * Date: 2019/6/19
 * Description:
 */
@Data
public class RealSleepStatusRatioRes {
    private Ring1 ring1 = new Ring1();
    private Ring2 ring2 = new Ring2();

    @Data
    public static class Ring1 {
        //深睡占比
        private BigDecimal shenRatio;
        //浅睡占比
        private BigDecimal qianRatio;
        //浅睡时长 + 深睡时长
        private String sleepTotal;
    }

    @Data
    public static class Ring2 {
        //(浅睡时长 + 深睡时长)/(深睡、浅睡、引睡、清醒)
        private BigDecimal sleepRatio;
        //(深睡、浅睡、引睡、清醒) - (浅睡时长 + 深睡时长)/(深睡、浅睡、引睡、清醒)
        private BigDecimal unSleepRatio;
        //深睡、浅睡、引睡、清醒
        private String totalTime;
    }
}
