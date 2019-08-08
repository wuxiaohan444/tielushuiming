package com.jiangkai.framework.admin.timetask.summary.restructure;

/**
 * User: zvbb
 * Date: 2019/5/15
 * Description:
 */
@FunctionalInterface
public interface RetructureInterface<T,R> {
    /**
     * 数据转换方法
     * */
    R restructureData(T t);
}
