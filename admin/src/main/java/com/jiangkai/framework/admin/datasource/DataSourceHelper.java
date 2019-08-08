package com.jiangkai.framework.admin.datasource;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @date 2019/4/30 13:23
 */
public abstract class DataSourceHelper {

    private static ConcurrentHashMap<Long, CustomDataSource> dataSourceMap = new ConcurrentHashMap<>();

    public static Map<Long, CustomDataSource> getDataSourceMap() {
        return dataSourceMap;
    }

    /**
     * 根据数据库ID 获取自定义数据源
     */
    public static CustomDataSource getDataSource(Long dataSourceId) {
        return dataSourceMap.get(dataSourceId);
    }

    /**
     * 添加数据源
     */
    static void addDataSource(Long dataSourceId, CustomDataSource custom) {
        dataSourceMap.put(dataSourceId, custom);
    }

    /**
     * 移除数据源
     */
    static void removeDataSource(Long dataSourceId) {
        CustomDataSource customDataSource = dataSourceMap.remove(dataSourceId);
        if (null != customDataSource)
            customDataSource.close();
    }
}
