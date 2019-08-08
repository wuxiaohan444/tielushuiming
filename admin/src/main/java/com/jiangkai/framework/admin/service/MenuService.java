package com.jiangkai.framework.admin.service;

import com.jiangkai.framework.admin.bean.MethodRes;
import com.jiangkai.framework.admin.bean.PermissionInfoRes;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.common.enums.Status;
import com.jiangkai.framework.admin.util.LoginUserUtils;
import com.jiangkai.framework.source.mapper.MethodDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.MethodMapper;
import com.jiangkai.framework.source.model.Method;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualTo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * User: zvbb
 * Date: 2019/6/20
 * Description:
 */
@Component
@RequiredArgsConstructor
public class MenuService {
    private final MethodMapper methodMapper;

    public Result allNode() {
        MethodDynamicSqlSupport.Method method = MethodDynamicSqlSupport.method;
        List<Method> permissionList = methodMapper.selectByExample()
                .where(method.status, IsNotEqualTo.of(Status.DELETE::getCode))
                .build()
                .execute();
        List<MethodRes> resList = new ArrayList<>();
        HashMap<Long, List<MethodRes>> permissionResHashMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(permissionList)) {
            permissionList.forEach(row -> {
                MethodRes res = new MethodRes();
                BeanUtils.copyProperties(row, res);
                Long parentid = row.getSuperiorId();
                //父节点
                if (parentid.equals(0L)) {
                    resList.add(res);
                    //子节点
                } else {
                    permissionResHashMap.computeIfAbsent(res.getSuperiorId(), k -> {
                        return new ArrayList<>();
                    }).add(res);
                }
            });
            getTree(resList, permissionResHashMap);
        }
        return Result.success(resList);
    }

    @Transactional
    public Result insert(Method method, HttpServletRequest request) {
        Result result = dataStandard(method);
        if (0 != result.getCode()) {
            return result;
        }
        //默认激活
        method.setStatus(Status.ENABLE.getCode());
        setInsertCol(method, request);
        if (methodMapper.insert(method) > 0) {
            return Result.success();
        } else {
            return Result.businessError("菜单添加失败!");
        }
    }

    @Transactional
    public Result delete(Long id, HttpServletRequest request) {
        if (Objects.isNull(id)) {
            return Result.paramError("id参数错误!");
        }
        Method old = methodMapper.selectByPrimaryKey(id);
        if (Objects.isNull(old)) {
            return Result.paramError("删除的菜单不存在!");
        }
        old.setStatus(Status.DELETE.getCode());
        setUpdateCol(old, request);
        if (methodMapper.updateByPrimaryKeySelective(old) > 0) {
            return Result.success();
        } else {
            return Result.businessError("删除失败!");
        }
    }

    public Result update(Method method, HttpServletRequest request) {
        if (Objects.isNull(method.getId())) {
            return Result.paramError("id参数错误!");
        }
        Result result = dataStandard(method);
        if (0 != result.getCode()) {
            return result;
        }

        if (methodMapper.updateByPrimaryKeySelective(method) > 0) {
            return Result.success();
        } else {
            return Result.businessError("更新失败!");
        }
    }

    public Result select(Long id) {
        if (Objects.isNull(id)) {
            return Result.paramError("id参数错误!");
        }
        Method method = methodMapper.selectByPrimaryKey(id);
        PermissionInfoRes res = new PermissionInfoRes();
        BeanUtils.copyProperties(method, res);
        Long parentid = method.getSuperiorId();
        //父节点名称
        if (Objects.nonNull(parentid) && !parentid.equals(0L)) {
            Method parentNode = methodMapper.selectByPrimaryKey(parentid);
            res.setParentName(parentNode.getName());
        }
        return Result.success(res);
    }

    private static Result dataStandard(Method method) {
        if (Objects.isNull(method.getName())) {
            return Result.paramError("名称字段不能为空!");
        }
        if (Objects.isNull(method.getIsMenu())) {
            return Result.paramError("菜单字段不能为空!");
        }
        return Result.success();
    }

    /**
     * @Description 插入时
     */
    private static void setInsertCol(Method method, HttpServletRequest request) {
        method.setCreateTime(new Date());
        method.setCreateUser(LoginUserUtils.getLoginUserId(request));
    }

    /**
     * @Description 更新时
     */
    private static void setUpdateCol(Method method, HttpServletRequest request) {
        method.setUpdateTime(new Date());
        method.setUpdateUser(LoginUserUtils.getLoginUserId(request));
    }

    private static void getTree(List<MethodRes> resList,
                                HashMap<Long, List<MethodRes>> permissionResHashMap) {
        if (resList.size() == 0 || permissionResHashMap.size() == 0) {
            return;
        }
        for (int i = 0; i < resList.size(); i++) {
            MethodRes row = resList.get(i);
            if (Objects.nonNull(permissionResHashMap.get(row.getId()))) {
                Long id = row.getId();
                List<MethodRes> permissionRes = permissionResHashMap.get(id);
                row.setLeafNodes(permissionResHashMap.get(id));
                //移除掉，已经找到父节点的
                permissionResHashMap.remove(id);
                //为剩余的节点，寻找父节点
                getTree(permissionRes, permissionResHashMap);
            }
        }
    }
}
