package com.jiangkai.framework.admin.bean;

import com.jiangkai.framework.source.model.User;
import lombok.Data;

/**
 * User: zvbb
 * Date: 2019/6/21
 * Description:
 */
@Data
public class UserInsertBean extends User {
    private Long deptId;
    private String roleId;
}
