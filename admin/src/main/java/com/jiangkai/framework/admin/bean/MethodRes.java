package com.jiangkai.framework.admin.bean;

import com.jiangkai.framework.source.model.Method;
import lombok.Data;

import java.util.List;

/**
 * User: zvbb
 * Date: 2019/6/20
 * Description:
 */
@Data
public class MethodRes extends Method {
    //叶子节点
    private List<MethodRes> leafNodes;
    //角色是否有该权限  0.没有权限  1.有权限
    private Long power = 0L;
}
