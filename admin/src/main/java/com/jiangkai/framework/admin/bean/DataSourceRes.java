package com.jiangkai.framework.admin.bean;

import com.jiangkai.framework.source.model.DataSource;
import lombok.Data;

/**
 * User: zvbb
 * Date: 2019/6/20
 * Description:
 */
@Data
public class DataSourceRes extends DataSource {
    //数据源所属部门名称
    private String deptName;
    //部门类型
    private Long deptType;
    //上级部门名称
    private String superiorDeptName;
    //上级部门ID
    private Long superiorDeptId;
}
