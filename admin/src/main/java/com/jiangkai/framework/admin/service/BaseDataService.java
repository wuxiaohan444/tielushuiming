package com.jiangkai.framework.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangkai.framework.admin.bean.BasedataBean;
import com.jiangkai.framework.admin.bean.LoginUser;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.common.enums.Status;
import com.jiangkai.framework.admin.constant.Constant;
import com.jiangkai.framework.admin.util.LoginUserUtils;
import com.jiangkai.framework.source.mapper.BaseDataDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.BaseDataMapper;
import com.jiangkai.framework.source.model.BaseData;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualTo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * User: zvbb
 * Date: 2019/6/11
 * Description:
 */
@Component
@RequiredArgsConstructor
public class BaseDataService {
    private final BaseDataMapper baseDataMapper;

    public Result page(BasedataBean basedataBean) {
        if (basedataBean.getPage() == null) {
            basedataBean.setPage(Constant.PAGE);
        }
        if (basedataBean.getLimit() == null) {
            basedataBean.setLimit(Constant.LIMIT);
        }
        //分页
        PageHelper.startPage(basedataBean.getPage(), basedataBean.getLimit());
        //查询条件
        BaseDataDynamicSqlSupport.BaseData baseData = BaseDataDynamicSqlSupport.baseData;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<BaseData>>>.QueryExpressionWhereBuilder builder = baseDataMapper.selectByExample()
                .where(baseData.status, IsNotEqualTo.of(Status.DELETE::getCode));
        if (Objects.nonNull(basedataBean.getValue())) {
            builder.and(baseData.value, IsEqualTo.of(basedataBean::getValue));
        }
        //查询
        List<BaseData> baseDataList = builder.build().execute();
        PageInfo info = new PageInfo<>(baseDataList);
        return Result.page(info);
    }

    public Result insert(BaseData baseData, HttpServletRequest request) {
        Result result = dataStandard(baseData, true);
        if (0 != result.getCode()) {
            return result;
        }
        baseData.setStatus(Status.ENABLE.getCode());
        setCreateCol(baseData, request);
        if (baseDataMapper.insertSelective(baseData) > 0) {
            return Result.success();
        } else {
            return Result.businessError("添加失败!");
        }
    }

    public Result select(Long id) {
        if (Objects.isNull(id)) {
            return Result.businessError("id参数错误!");
        }
        BaseDataDynamicSqlSupport.BaseData baseData = BaseDataDynamicSqlSupport.baseData;
        List<BaseData> baseDataList = baseDataMapper.selectByExample()
                .where(baseData.status, IsNotEqualTo.of(Status.DELETE::getCode))
                .and(baseData.id, IsEqualTo.of(() -> id))
                .build()
                .execute();
        if (CollectionUtils.isEmpty(baseDataList)) {
            return Result.paramError("该基础数据不存在!");
        } else {
            return Result.success(baseDataList.get(0));
        }
    }

    @Transactional
    public Result delete(Long id, HttpServletRequest request) {
        if (Objects.isNull(id)) {
            return Result.businessError("id参数错误");
        }
        BaseData old = baseDataMapper.selectByPrimaryKey(id);
        if (Objects.isNull(old)) {
            return Result.paramError("删除的基础数据不存在!");
        }
        old.setStatus(Status.DELETE.getCode());
        setUpdateCol(old, request);
        if (baseDataMapper.updateByPrimaryKeySelective(old) > 0) {
            return Result.success();
        } else {
            return Result.businessError("删除失败!");
        }
    }

    @Transactional
    public Result update(BaseData baseData, HttpServletRequest request) {
        Result result = dataStandard(baseData, false);
        if (0 != result.getCode()) {
            return result;
        }
        if (Objects.isNull(baseData.getId())) {
            return Result.paramError("id参数不能为空!");
        }
        BaseData old = baseDataMapper.selectByPrimaryKey(baseData.getId());
        if (Objects.isNull(old)) {
            return Result.paramError("编辑的基础数据不存在!");
        }
        baseData.setStatus(Status.ENABLE.getCode());
        setUpdateCol(baseData, request);
        if (baseDataMapper.updateByPrimaryKeySelective(baseData) > 0) {
            return Result.success();
        } else {
            return Result.businessError("更新失败!");
        }
    }

    public Result dataStandard(BaseData baseData, boolean isInsert) {
        if (Objects.isNull(baseData)) {
            return Result.paramError("前后台数据转换存在问题!");
        }
        if (Objects.isNull(baseData.getValue())) {
            return Result.paramError("对比值不能为空!");
        } else {//检查对比值是否重复
            BaseDataDynamicSqlSupport.BaseData baseDataSupport = BaseDataDynamicSqlSupport.baseData;
            if (isInsert) {
                List<BaseData> baseDataList = baseDataMapper.selectByExample()
                        .where(baseDataSupport.value, IsEqualTo.of(baseData::getValue))
                        .build()
                        .execute();
                if (!CollectionUtils.isEmpty(baseDataList)) {
                    return Result.paramError("对比值不能重复!");
                }
            } else {
                List<BaseData> baseDataList = baseDataMapper.selectByExample()
                        .where(baseDataSupport.id, IsNotEqualTo.of(baseData::getId))
                        .and(baseDataSupport.value, IsEqualTo.of(baseData::getValue))
                        .build()
                        .execute();
                if (!CollectionUtils.isEmpty(baseDataList)) {
                    return Result.paramError("对比值不能重复!");
                }
            }
        }
//        if (!isInsert) {
//            if (Objects.isNull(baseData.getStatus())){
//                return Result.paramError("状态不能为空!");
//            }
//        }
        if (Objects.isNull(baseData.getSleepStatus()) || baseData.getSleepStatus().equals("")) {
            return Result.paramError("睡眠状态不能为空!");
        }
        if (Objects.isNull(baseData.getMonitorDim()) || baseData.getMonitorDim().equals("")) {
            return Result.paramError("监测维度不能为空!");
        }

        if (Objects.isNull(baseData.getSleepAdvise()) || baseData.getSleepAdvise().equals("")) {
            return Result.paramError("总体建议不能为空!");
        }
        return Result.success();
    }

    public void setCreateCol(BaseData baseData, HttpServletRequest request) {
        baseData.setCreateTime(new Date());
        baseData.setCreateUser(LoginUserUtils.getLoginUserId(request));
    }

    public void setUpdateCol(BaseData baseData, HttpServletRequest request) {
        baseData.setUpdateTime(new Date());
        baseData.setUpdateUser(LoginUserUtils.getLoginUserId(request));
    }
}
