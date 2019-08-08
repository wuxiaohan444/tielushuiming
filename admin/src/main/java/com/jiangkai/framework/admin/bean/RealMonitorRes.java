package com.jiangkai.framework.admin.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: zvbb
 * Date: 2019/6/19
 * Description:
 */
@Data
public class RealMonitorRes {
    private BigDecimal avgHeartBeat;
    private BigDecimal avgBreath;
    private List<String> sleepStatus = new ArrayList<>();
}
