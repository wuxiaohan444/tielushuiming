package com.jiangkai.framework.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * User: zvbb
 * Date: 2019/6/28
 * Description:
 */
@Getter
@RequiredArgsConstructor
public enum SleepStatus {
    YINSLEEP("引睡"),
    QINSLEEP("清醒"),
    QIANSLEEP("浅睡"),
    SHENSLEEP("深睡");
    private final String mark;
}
