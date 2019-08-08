package com.jiangkai.framework.admin.bean;

import com.jiangkai.framework.source.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zvbb
 * Date: 2019/6/21
 * Description:
 */
@Data
public class UserRes extends User {
    //角色名
    private List<String> roleName = new ArrayList<>();
    //角色id
    private List<Long> roleId = new ArrayList<>();
    //部门类型
    private Long deptType;
    //部门名
    private String deptName;
    //部门id
    private Long deptId;
    //上级部门名
    private String superiorName;
    //上级部门id
    private Long superiorId;
}
