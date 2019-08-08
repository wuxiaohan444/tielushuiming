package com.jiangkai.framework.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Administrator
 * @date 2019/4/26 17:53
 */
@RequiredArgsConstructor
@Getter
public enum Status {
    ENABLE(1, "可用的"),
    DISABLE(0, "不可用"),
    DELETE(-1, "已删除");
    private final Integer code;
    private final String mark;
}
