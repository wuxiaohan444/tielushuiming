package com.jiangkai.framework.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * User: zvbb
 * Date: 2019/7/3
 * Description:
 */
@RequiredArgsConstructor
@Getter
public enum ConstNumber {
    SECONDE(1000L);
    private final long value;
}
