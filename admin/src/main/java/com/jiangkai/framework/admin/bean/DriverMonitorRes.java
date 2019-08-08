package com.jiangkai.framework.admin.bean;

import lombok.Data;

import java.util.Date;

/**
 * User: zvbb
 * Date: 2019/6/18
 * Description:
 */
@Data
public class DriverMonitorRes {
    //监测时间
    private Date monitorTime;
    //间休地址
    private String restAddr;
    //报告的guid
    private String reportguid;
}
