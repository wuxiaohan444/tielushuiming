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
public class BasedataBean extends BasePage {
    private Integer value;
}
