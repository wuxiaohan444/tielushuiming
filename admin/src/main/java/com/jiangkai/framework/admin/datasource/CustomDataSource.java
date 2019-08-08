package com.jiangkai.framework.admin.datasource;

import com.jiangkai.framework.admin.datasource.exception.CustomDataSourceException;
import com.jiangkai.framework.extdata.mapper.InfoBedMapper;
import com.jiangkai.framework.extdata.mapper.InfoDetailMapper;
import com.jiangkai.framework.extdata.mapper.InfoIntefaceMapper;
import com.jiangkai.framework.extdata.mapper.InfoLeaveMapper;
import com.jiangkai.framework.extdata.model.InfoBed;
import com.jiangkai.framework.extdata.model.InfoDetail;
import com.jiangkai.framework.extdata.model.InfoInteface;
import com.jiangkai.framework.source.model.DataSource;
import com.jiangkai.framework.utils.tools.IStringUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Administrator
 * @date 2019/4/29 18:21
 */

public class CustomDataSource {

    //数据地址
    @Getter
    @Setter
    private DataSource address;

    //数据源
    private HikariDataSource dataSource;

    //环境
    private Environment environment;

    //SqlSessionFactory
    private SqlSessionFactory sqlSessionFactory;

    @Getter
    @Setter
    private String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /**
     * 获取Mapper
     * 使用Consumer来对Mapper进行操作
     */
    public <M, R> R findMapperDoMethod(Class<M> clazz, Function<M, R> function) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            return function.apply(sqlSession.getMapper(clazz));
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 获取Mapper
     * 使用Consumer来对Mapper进行操作
     */
    public <M> void findMapperDoMethod(Class<M> clazz, Consumer<M> consumer) {
        this.findMapperDoMethod(clazz, m -> {
            consumer.accept(m);
            return true;
        });
    }


    /**
     * 查看是否在运行
     */
    public boolean isRunning() {
        return null != dataSource && dataSource.isRunning();
    }

    /**
     * 链接数据库
     */
    public void link() throws Exception {
        if (!this.isRunning())
            this.link0();
    }

    /**
     * 强制链接数据库
     */
    public void forceLink() throws Exception {
        //先关闭再链接
        this.close();
        this.link0();
    }


    private void link0() throws Exception {
        if (null == address)
            throw new CustomDataSourceException("无数据源配置信息");
        if (IStringUtils.isEmpty(address.getIp()))
            throw new CustomDataSourceException("数据库源配置缺少IP信息");
        if (null == address.getPort())
            throw new CustomDataSourceException("数据源配置缺少端口号");
        if (IStringUtils.isEmpty(address.getDatabaseName()))
            throw new CustomDataSourceException("数据源配置缺少数据库名称");
        if (IStringUtils.isEmpty(address.getUsername()))
            throw new CustomDataSourceException("数据源配置缺少用户名");
        if (IStringUtils.isEmpty(address.getPassword()))
            throw new CustomDataSourceException("数据源配置缺少密码");
        /*数据库名称*/
        HikariConfig config = new HikariConfig();
        config.setCatalog(address.getDatabaseName());
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl("jdbc:sqlserver://" + address.getIp() + ":" + address.getPort() + ";DatabaseName=" + address.getDatabaseName());
        config.setUsername(address.getUsername());
        config.setPassword(address.getPassword());
        config.setPoolName(address.getIp() + "-HikariDataSource");
        config.setMaxLifetime(1800000);
        config.setMaximumPoolSize(4);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        dataSource = new HikariDataSource(config);
        environment = new Environment(address.getIp(), new SpringManagedTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);
//        configuration.addMappers("com.jiangkai.framework.extdata.mapper");
        configuration.addMapper(InfoBedMapper.class);
        configuration.addMapper(InfoDetailMapper.class);
        configuration.addMapper(InfoIntefaceMapper.class);
        configuration.addMapper(InfoLeaveMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        DataSourceHelper.addDataSource(address.getId(), this);
    }

    /**
     * 关闭数据源
     */
    public void close() {
        if (null != environment)
            environment = null;
        if (null != sqlSessionFactory)
            sqlSessionFactory = null;
        if (this.isRunning())
            dataSource.close();
        DataSourceHelper.removeDataSource(address.getId());
    }
}
