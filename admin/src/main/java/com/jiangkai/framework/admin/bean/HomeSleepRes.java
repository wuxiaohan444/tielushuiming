package com.jiangkai.framework.admin.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * User: zvbb
 * Date: 2019/6/18
 * Description:
 */
@Data
public class HomeSleepRes {
    private SleepTimeAnalysis sleepTimeAnalysis = new SleepTimeAnalysis();
    private SleepEffAnalysis sleepEffAnalysis = new SleepEffAnalysis();

    @Data
    public static class SleepTimeAnalysis {
        //深睡
        private BigDecimal shen = BigDecimal.ZERO;
        //入睡
        private BigDecimal sleepTime = BigDecimal.ZERO;
        //浅睡
        private BigDecimal qian = BigDecimal.ZERO;
        //其他
        private BigDecimal other = BigDecimal.ZERO;
    }

    @Data
    public static class SleepEffAnalysis {
        private BigDecimal level1SleepEff;
        private BigDecimal level2SleepEff;
        private BigDecimal level3SleepEff;
        private BigDecimal level4SleepEff;
        private BigDecimal level5SleepEff;
    }
}
