package com.jiangkai.framework.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * User: zvbb
 * Date: 2019/6/27
 * Description:
 */
@Getter
@RequiredArgsConstructor
public enum HaveEdit {
    EDIT(1),//修改了,需要更新数据库
    NOEDIT(2);//没有修改
    private final Integer code;
}
