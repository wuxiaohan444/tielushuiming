package com.jiangkai.framework.admin.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: zvbb
 * Date: 2019/6/13
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TeamBean {
    //年份
    private Integer year;

    //半年
    private Integer halfYear;

    //季度
    private Integer quarter;

    //月份
    private Integer month;

    //部门id
    private Long id;
}
