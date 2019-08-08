package com.jiangkai.framework.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangkai.framework.admin.bean.DataSourceBean;
import com.jiangkai.framework.admin.bean.DataSourceRes;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.common.enums.Status;
import com.jiangkai.framework.admin.constant.Constant;
import com.jiangkai.framework.admin.datasource.CustomDataSource;
import com.jiangkai.framework.admin.datasource.DataSourceHelper;
import com.jiangkai.framework.admin.timetask.JobManager;
import com.jiangkai.framework.admin.util.LoginUserUtils;
import com.jiangkai.framework.source.mapper.*;
import com.jiangkai.framework.source.model.DataSource;
import com.jiangkai.framework.source.model.Dept;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.where.condition.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * User: zvbb
 * Date: 2019/5/13
 * Description:
 */
@RequiredArgsConstructor
@Component
public class DatasourceService {
    private final DataSourceMapper dataSourceMapper;
    private final JobManager jobManager;
    private final DeptMapper deptMapper;

    @Transactional
    public Result page(DataSourceBean datasourceBean) {
        if (datasourceBean.getPage() == null) {
            datasourceBean.setPage(Constant.PAGE);
        }
        if (datasourceBean.getLimit() == null) {
            datasourceBean.setLimit(Constant.LIMIT);
        }
        //查询条件
        DataSourceDynamicSqlSupport.DataSource dataSource = DataSourceDynamicSqlSupport.dataSource;
        QueryExpressionDSL<org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter<List<DataSource>>>.QueryExpressionWhereBuilder builder =
                dataSourceMapper.selectByExample().where(dataSource.status, IsNotEqualTo.of(Status.DELETE::getCode));
        if (Objects.nonNull(datasourceBean.getNickname())) {
            builder.and(dataSource.nickname, IsLike.of(() -> "%" + datasourceBean.getNickname() + "%"));
        }
        if (Objects.nonNull(datasourceBean.getIp())) {
            builder.and(dataSource.ip, IsLike.of(() -> "%" + datasourceBean.getIp() + "%"));
        }
        if (Objects.nonNull(datasourceBean.getUsername())) {
            builder.and(dataSource.username, IsLike.of(() -> "%" + datasourceBean.getUsername() + "%"));
        }

        //分页
        PageHelper.startPage(datasourceBean.getPage(), datasourceBean.getLimit());
        //查询
        List<DataSource> dataSourceList = builder.build().execute();
        PageInfo info = new PageInfo(dataSourceList);
        //查询数据源所属部门名称
        if (!CollectionUtils.isEmpty(dataSourceList)) {
            HashMap<Long, DataSourceRes> datasoureMap = new HashMap();
            List<DataSourceRes> resList = new ArrayList<>();
            dataSourceList.forEach(data -> {
                DataSourceRes dataSourceRes = new DataSourceRes();
                BeanUtils.copyProperties(data, dataSourceRes);
                Long deptId = data.getDeptId();
                //与部门绑定的数据源
                if (Objects.nonNull(deptId)) {
                    datasoureMap.put(deptId, dataSourceRes);
                }
                resList.add(dataSourceRes);
            });
            DeptDynamicSqlSupport.Dept dept = DeptDynamicSqlSupport.dept;
            List<Dept> deptList = deptMapper.selectByExample()
                    .where(dept.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .and(dept.id, IsIn.of(new ArrayList<Long>(datasoureMap.keySet())))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(deptList)) {
                deptList.forEach(deptRow -> {
                    datasoureMap.get(deptRow.getId()).setDeptName(deptRow.getName());
                });
            }
            info.setList(resList);
        }
        return Result.page(info);
    }

    @Transactional
    public Result insert(DataSource dataSource, HttpServletRequest request) {
        Result result = dataStandard(dataSource, false);
        if (0 != result.getCode()) {
            return result;
        }
        //默认不激活
        dataSource.setStatus(Status.DISABLE.getCode());
        dataSource.setCreateTime(new Date());
        dataSource.setCreateUser(LoginUserUtils.getLoginUserId(request));
        if (dataSourceMapper.insert(dataSource) > 0) {
            return Result.success();
        } else {
            return Result.businessError("添加用户失败");
        }
    }

    public Result select(Long id) {
        if (Objects.isNull(id)) {
            return Result.businessError("id参数错误!");
        }
        DataSource dataSource = dataSourceMapper.selectByPrimaryKey(id);
        DataSourceRes dataSourceRes = new DataSourceRes();
        BeanUtils.copyProperties(dataSource, dataSourceRes);
        //获取关联的部门名称
        Long deptId = dataSource.getDeptId();
        Dept dept = deptMapper.selectByPrimaryKey(deptId);
        if (Objects.nonNull(dept)) {
            dataSourceRes.setDeptName(dept.getName());
            dataSourceRes.setDeptType(dept.getDepttype());
            Long superiorId = dept.getSuperiorId();
            dataSourceRes.setSuperiorDeptId(superiorId);
            if (Objects.nonNull(superiorId) && (0L != superiorId)) {
                Dept superiorDept = deptMapper.selectByPrimaryKey(superiorId);
                dataSourceRes.setSuperiorDeptName(superiorDept.getName());
            }
        }
        return Result.success(dataSourceRes);
    }

    @Transactional
    public Result delete(Long id, HttpServletRequest request) {
        if (Objects.isNull(id)) {
            return Result.paramError("id参数错误!");
        }
        DataSource old = dataSourceMapper.selectByPrimaryKey(id);
        if (Objects.isNull(old)) {
            return Result.paramError("数据源不存在!");
        }
        DataSource dataSource = new DataSource();
        dataSource.setUpdateTime(new Date());
        dataSource.setUpdateUser(LoginUserUtils.getLoginUserId(request));
        dataSource.setStatus(Status.ENABLE.getCode());
        if (dataSourceMapper.deleteByPrimaryKey(id) > 0) {
            return Result.success();
        } else {
            return Result.businessError("删除失败!");
        }
    }

    @Transactional
    public Result update(DataSource dataSource, HttpServletRequest request) {
        //1.参数是否正确
        if (Objects.isNull(dataSource.getId())) {
            return Result.paramError("id参数错误!");
        }
        Result result = dataStandard(dataSource, true);
        if (0 != result.getCode()) {
            return result;
        }
        //2.编辑的数据源是否存在
        DataSource old = dataSourceMapper.selectByPrimaryKey(dataSource.getId());
        if (Objects.isNull(old)) {
            return Result.paramError("更新的数据源不存在!");
        }
        //3.更新
        dataSource.setUpdateTime(new Date());
        dataSource.setUpdateUser(LoginUserUtils.getLoginUserId(request));
        if (dataSourceMapper.updateByPrimaryKeySelective(dataSource) > 0) {
            return Result.success();
        } else {
            return Result.businessError("更新失败!");
        }
    }

    /**
     * @param
     * @return
     * @Description 修改数据源状态为连接, 每次数据轮询时,
     * 只轮询状态为连接的数据源，如果轮询是连接失败,设置状态为断开
     * @date 2019/6/11
     * @auther zvbb
     */
    public Result onLink(Long id, HttpServletRequest request) throws Exception {
        if (Objects.isNull(id)) {
            return Result.businessError("id参数错误!");
        }
        //获取数据库中的数据源信息
        DataSource data = dataSourceMapper.selectByPrimaryKey(id);
        CustomDataSource customDataSource = new CustomDataSource();
        customDataSource.setAddress(data);
        try {
            //开启连接
            customDataSource.link();
        } catch (Exception e) {
            Result.businessError("连接数据源失败,请检查相关参数!");
        }
        //开启成功后，修改数据库中数据源状态、内存中的辅助变量
        if (customDataSource.isRunning()) {
            //修改数据库
            DataSource dataSource = new DataSource();
            dataSource.setId(id);
            dataSource.setStatus(Status.ENABLE.getCode());
            dataSource.setUpdateTime(new Date());
            dataSource.setUpdateUser(LoginUserUtils.getLoginUserId(request));
            dataSourceMapper.updateByPrimaryKeySelective(dataSource);
            jobManager.getDept2Datasource().put(data.getDeptId(), id);
            jobManager.getDatasource2Dept().put(id, data.getDeptId());
        } else {
            return Result.businessError("连接数据源失败,请检查相关参数!");
        }

        return Result.success();
    }

    /**
     * @param
     * @return
     * @Description 修改数据源状态, 每次数据轮询时，只轮询在线的数据源
     * @date 2019/6/11
     * @auther zvbb
     */
    public Result offLink(Long id, HttpServletRequest request) {
        if (Objects.isNull(id)) {
            return Result.businessError("id参数错误!");
        }
        CustomDataSource customDataSource = DataSourceHelper.getDataSource(id);
        try {
            //关闭连接
            customDataSource.close();
        } catch (Exception e) {
            return Result.businessError("数据源关闭失败!");
        }
        if (!customDataSource.isRunning()) {
            DataSource dataSource = dataSourceMapper.selectByPrimaryKey(id);
            dataSource.setStatus(Status.DISABLE.getCode());
            dataSource.setUpdateTime(new Date());
            dataSource.setUpdateUser(LoginUserUtils.getLoginUserId(request));
            //修改部门到数据源的映射
            jobManager.getDept2Datasource().remove(jobManager.getDatasource2Dept().get(id));
            //修改数据源 与 部门的映射关系
            jobManager.getDatasource2Dept().remove(id);
            //修改数据库记录
            dataSourceMapper.updateByPrimaryKeySelective(dataSource);
        } else {
            return Result.businessError("数据源关闭失败");
        }
        return Result.success();
    }

    /**
     * @Description 在编辑和插入数据源时，作数据检查
     */
    private Result dataStandard(DataSource dataSource, boolean isEdit) {
        if (Objects.isNull(dataSource.getDeptId())) {
            return Result.paramError("部门Id不能为空!");
        }
        if (Objects.isNull(dataSource.getIp())) {
            return Result.paramError("数据源IP不能为空!");
        }
        if (Objects.isNull(dataSource.getPort())) {
            return Result.paramError("数据源port不能为空!");
        }
        if (Objects.isNull(dataSource.getUsername())) {
            return Result.paramError("数据源用户名不能为空!");
        }
        if (Objects.isNull(dataSource.getPassword())) {
            return Result.paramError("数据源密码不能为空!");
        }
        if (Objects.isNull(dataSource.getDatabaseName())) {
            return Result.paramError("数据源所用数据库不能为空!");
        }
        DataSourceDynamicSqlSupport.DataSource dataSourceSupport = DataSourceDynamicSqlSupport.dataSource;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<DataSource>>>.QueryExpressionWhereBuilder whereBuilder = dataSourceMapper.selectByExample()
                .where(dataSourceSupport.status, IsNotEqualTo.of(Status.DISABLE::getCode))
                .and(dataSourceSupport.ip, IsEqualTo.of(dataSource::getIp));
        //编辑时,判断ip是否重复，需要排除它自己
        if (isEdit) {
            whereBuilder.and(dataSourceSupport.id, IsNotEqualTo.of(dataSource::getId));
        }

        List<DataSource> dataSourceList = whereBuilder.build().execute();
        if (!CollectionUtils.isEmpty(dataSourceList)) {
            return Result.paramError("数据源ip重复!");
        }
        return Result.success();
    }
}
