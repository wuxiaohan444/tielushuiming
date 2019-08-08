package com.jiangkai.framework.admin.timetask.summary;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * User: zvbb
 * Date: 2019/5/14
 * Description:
 */
@Component
@RequiredArgsConstructor
public class SummaryHelper {
    public static ThreadLocal threadLocal = new ThreadLocal();
    private final GetDataHandler getDataHandler;
    private final DealDataHandler dealDataHandler;
    private final TeamSummaryHandler teamSummaryHandler;


    /**
     * @Description 获取任务链
     * @param
     * @return
     * @date 2019/5/14
     * @auther zvbb
     */
    public SummaryHandler getSummaryHandlerChain(){
        getDataHandler.setNextHandler(dealDataHandler);
        dealDataHandler.setNextHandler(teamSummaryHandler);
        return getDataHandler;
    }
}
