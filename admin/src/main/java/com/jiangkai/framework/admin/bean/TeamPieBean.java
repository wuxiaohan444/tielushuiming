package com.jiangkai.framework.admin.bean;

import lombok.Data;

/**
 * User: zvbb
 * Date: 2019/6/14
 * Description:
 */
@Data
public class TeamPieBean {
    private PieSleepEff pieSleepEff = new PieSleepEff();
    private PieSleepTime pieSleepTime = new PieSleepTime();

    //睡眠效率示意图
    @Data
    public class PieSleepEff {
        private Long lowSleepEffCnt = Long.valueOf(0);
        private Long middleSleepEffCnt = Long.valueOf(0);
        private Long hightSleepEffCnt = Long.valueOf(0);
    }

    //入睡时长示意图
    @Data
    public class PieSleepTime {
        private Long lowSleepTimeCnt = Long.valueOf(0);
        private Long middleSleepTimeCnt = Long.valueOf(0);
        private Long hightSleepTimeCnt = Long.valueOf(0);
    }
}
