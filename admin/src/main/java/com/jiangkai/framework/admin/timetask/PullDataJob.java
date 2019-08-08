package com.jiangkai.framework.admin.timetask;

import com.jiangkai.framework.admin.datasource.CustomDataSource;
import com.jiangkai.framework.admin.datasource.DataSourceHelper;
import com.jiangkai.framework.admin.timetask.summary.SummaryHandler;
import com.jiangkai.framework.admin.timetask.summary.SummaryHelper;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: zvbb
 * Date: 2019/5/14
 * Description: 定期数据拉去任务
 */
@Component
@RequiredArgsConstructor
public class PullDataJob {
    private final SummaryHelper summaryHelper;

    private ExecutorService threadPool;

    @PostConstruct
    public void getThreadPool() {
        threadPool = Executors.newCachedThreadPool();
    }

    public void execute() {
        Map<Long, CustomDataSource> dataSourceMap = DataSourceHelper.getDataSourceMap();
        SummaryHandler summaryHandlerChain = summaryHelper.getSummaryHandlerChain();
        dataSourceMap.forEach((datasouceId, customDataSource) -> {
            threadPool.execute(() -> summaryHandlerChain.handleProcess(customDataSource));
        });
    }
}
