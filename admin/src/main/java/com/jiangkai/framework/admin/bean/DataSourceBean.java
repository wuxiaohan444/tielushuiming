package com.jiangkai.framework.admin.bean;

import com.jiangkai.framework.source.base.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: zvbb
 * Date: 2019/6/11
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataSourceBean extends BasePage {
    //数据源昵称
    private String nickname;
    //数据源ip
    private String ip;
    //数据源用户名
    private String username;
}
