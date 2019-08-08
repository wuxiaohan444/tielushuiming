package com.jiangkai.framework.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * User: zvbb
 * Date: 2019/6/25
 * Description:
 */
@RequiredArgsConstructor
@Getter
public enum  SumType {
    MONTH(1, "MONTH"),
    DAY(2, "DAY");
    private final Integer code;
    private final String mark;
}
