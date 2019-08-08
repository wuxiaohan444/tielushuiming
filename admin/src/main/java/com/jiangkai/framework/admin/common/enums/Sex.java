package com.jiangkai.framework.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Administrator
 * @date 2019/4/29 14:28
 */
@RequiredArgsConstructor
@Getter
public enum Sex {
    MALE(1, "男"),
    FEMALE(0, "女"),
    OTHER(2, "其它");
    private final Integer code;
    private final String mark;
}
