package com.jiangkai.framework.admin.bean;

import com.jiangkai.framework.source.model.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: zvbb
 * Date: 2019/6/10
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptRes extends Dept {
    //上级部门名称
    private String superiorName;
}
