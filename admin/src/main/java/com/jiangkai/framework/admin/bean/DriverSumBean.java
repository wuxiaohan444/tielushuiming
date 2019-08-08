package com.jiangkai.framework.admin.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiangkai.framework.source.base.BasePage;
import lombok.Data;

import java.util.Date;

/**
 * User: zvbb
 * Date: 2019/6/14
 * Description:
 */
@Data
public class DriverSumBean extends BasePage {
    //司机编号
    private String no;
    //开始时间
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date startTime;
    //结束时间
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endTime;
}
