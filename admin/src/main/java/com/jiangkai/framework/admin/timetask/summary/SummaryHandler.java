package com.jiangkai.framework.admin.timetask.summary;

import lombok.Getter;
import lombok.Setter;

/**
 * User: zvbb
 * Date: 2019/5/14
 * Description: T:要求传入的数据格式; U:说明返回的数据格式
 */
public abstract class SummaryHandler<T, R> {
    //指向下个处理过程
    @Getter
    @Setter
    private SummaryHandler nextHandler;

    public final R handleProcess(T request) {
        R reponse = this.deal(request);

        if (null != this.nextHandler) {
            reponse = (R) this.nextHandler.handleProcess(reponse);
        }

        return reponse;
    }

    /**
     * @date 创建时间 2018/3/28
     * @author Jiang zinc
     * @Description 对任务的具体处理操作
     * @version
     */
    protected abstract R deal(T request);
}
