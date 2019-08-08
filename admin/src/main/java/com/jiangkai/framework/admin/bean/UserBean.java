package com.jiangkai.framework.admin.bean;

import com.jiangkai.framework.source.base.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * User: zvbb
 * Date: 2019/6/10
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserBean extends BasePage {
    //工号
    private String no;
    //姓名
    private String name;
}
