package com.jiangkai.framework.admin.bean;

import com.jiangkai.framework.source.base.BasePage;
import lombok.Data;

/**
 * User: zvbb
 * Date: 2019/6/21
 * Description:
 */
@Data
public class RoleBean extends BasePage {
    //角色id
    private Long id;
    //角色名称
    private String name;
    //是否启用该角色
    private Integer status;
    //角色权限
    private String permission;
}
