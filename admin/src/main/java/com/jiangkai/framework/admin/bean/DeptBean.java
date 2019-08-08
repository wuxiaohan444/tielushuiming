package com.jiangkai.framework.admin.bean;

import com.jiangkai.framework.source.base.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: zvbb
 * Date: 2019/6/10
 * Description:
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class DeptBean extends BasePage {
    //编号
    private String no;
    //名称
    private String name;
    //部门类型 0.顶级部门 1.待乘室 2.外公寓
    private Long deptType;
}
