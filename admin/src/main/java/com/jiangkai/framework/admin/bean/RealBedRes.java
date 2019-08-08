package com.jiangkai.framework.admin.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * User: zvbb
 * Date: 2019/6/19
 * Description:
 */
@Data
public class RealBedRes {
    //床铺号
    private String bedSerialNo;
    //工号
    private String driverNo;
    //当前使用者
    private String driverName;
    //睡眠时长
    private BigDecimal sleepTime;
    //当前心跳值
    private Integer heartBeat;
    //当前呼吸值
    private Integer breath;
}
