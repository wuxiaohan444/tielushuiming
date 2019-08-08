package com.jiangkai.framework.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Administrator
 * @date 2018/6/28
 */
@Getter
@RequiredArgsConstructor
public enum MsgType {
    SUCCESS(0, "操作成功"),
    NOT_LOGIN(1001, "未登录"),
    NO_PERMISSION(1002, "无权限"),
    NO_DATA(1003, "无数据"),
    BUSINESS_ERROR(1004, "业务逻辑异常"),
    PARAM_ERROR(1005, "参数异常"),
    ERROR(9999, "系统异常");
    private final Integer code;
    private final String content;

}
