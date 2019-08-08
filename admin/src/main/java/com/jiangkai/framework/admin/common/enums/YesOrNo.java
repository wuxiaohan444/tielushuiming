package com.jiangkai.framework.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Administrator
 * @date 2019/4/29 14:26
 */
@RequiredArgsConstructor
@Getter
public enum YesOrNo {
    YES(1, "是"),
    NO(0, "否");
    private final Integer code;
    private final String mark;
}
