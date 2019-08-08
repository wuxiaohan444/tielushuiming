package com.jiangkai.framework.admin.config;

import com.jiangkai.framework.admin.common.enums.YesOrNo;
import com.jiangkai.framework.admin.datasource.CustomDataSource;
import com.jiangkai.framework.admin.security.SessionHelper;
import com.jiangkai.framework.admin.timetask.JobManager;
import com.jiangkai.framework.source.mapper.DataSourceDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.DataSourceMapper;
import com.jiangkai.framework.source.mapper.MethodDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.MethodMapper;
import com.jiangkai.framework.source.model.DataSource;
import com.jiangkai.framework.source.model.Method;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/4/29 15:34
 */
@Component
@RequiredArgsConstructor
public class CustomApplicationRunner implements ApplicationRunner {

    private final MethodMapper methodMapper;
    private final DataSourceMapper dataSourceMapper;
    private final JobManager jobManager;

    @Override
    public void run(ApplicationArguments args) {
        //第一步 加载所有需要权限的菜单
        MethodDynamicSqlSupport.Method method = MethodDynamicSqlSupport.method;
        List<Method> methodList = methodMapper.selectByExample().where(method.status, IsEqualTo.of(YesOrNo.YES::getCode)).build().execute();
        SessionHelper.initUrlMap(methodList, s -> s);
        //第二步 加载所有已知的数据源
        DataSourceDynamicSqlSupport.DataSource dataSource = DataSourceDynamicSqlSupport.dataSource;
        List<DataSource> dataSourceList = dataSourceMapper.selectByExample().where(dataSource.status, IsEqualTo.of(YesOrNo.YES::getCode)).build().execute();
        if (!CollectionUtils.isEmpty(dataSourceList)) {
            dataSourceList.forEach(dataSourceItem -> {
                CustomDataSource customDataSource = new CustomDataSource();
                customDataSource.setAddress(dataSourceItem);
                try {
                    //建立连接
                    customDataSource.link();
                } catch (Exception e) {
                    //连接建立失败,修改数据库，数据源状态
                    e.printStackTrace();
                    dataSourceItem.setStatus(YesOrNo.NO.getCode());
                    dataSourceMapper.updateByExampleSelective(dataSourceItem);
                }
            });
        }
    }
}
