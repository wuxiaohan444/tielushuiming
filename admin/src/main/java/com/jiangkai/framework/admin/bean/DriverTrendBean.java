package com.jiangkai.framework.admin.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: zvbb
 * Date: 2019/6/14
 * Description:
 */
@Data
public class DriverTrendBean {
    private List<List<Map<Date, Integer>>> trend = new ArrayList<>();
    private HeartDetail heartDetail = new HeartDetail();
    private BreathDetail breathDetail = new BreathDetail();

    @Data
    public static class HeartDetail {
        private Integer max = 0;
        private Integer min = 0;
        private Integer avg = 0;
    }

    @Data
    public static class BreathDetail {
        private Integer max = 0;
        private Integer min = 0;
        private Integer avg = 0;
    }
}
