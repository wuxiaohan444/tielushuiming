package com.jiangkai.framework.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangkai.framework.admin.bean.MethodRes;
import com.jiangkai.framework.admin.bean.RoleBean;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.common.enums.Status;
import com.jiangkai.framework.admin.constant.Constant;
import com.jiangkai.framework.admin.util.LoginUserUtils;
import com.jiangkai.framework.source.mapper.*;
import com.jiangkai.framework.source.model.Method;
import com.jiangkai.framework.source.model.Role;
import com.jiangkai.framework.source.model.RoleMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsIn;
import org.mybatis.dynamic.sql.where.condition.IsLike;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualTo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User: zvbb
 * Date: 2019/6/21
 * Description:
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RoleService {
    private final RoleMapper roleMapper;
    private final MethodMapper methodMapper;
    private final RoleMethodMapper roleMethodMapper;

    public Result page(RoleBean roleBean) {
        if (Objects.isNull(roleBean.getPage())) {
            roleBean.setPage(Constant.PAGE);
        }
        if (Objects.isNull(roleBean.getLimit())) {
            roleBean.setLimit(Constant.LIMIT);
        }
        RoleDynamicSqlSupport.Role role = RoleDynamicSqlSupport.role;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Role>>>.QueryExpressionWhereBuilder builder = roleMapper.selectByExample()
                .where(role.status, IsNotEqualTo.of(Status.DELETE::getCode));
        if (Objects.nonNull(roleBean.getName())) {
            builder.and(role.name, IsLike.of(() -> "%" + roleBean.getName() + "%"));
        }
        if (Objects.nonNull(roleBean.getStatus())) {
            builder.and(role.status, IsEqualTo.of(roleBean::getStatus));
        }
        //分页
        PageHelper.startPage(roleBean.getPage(), roleBean.getLimit());
        List<Role> roleList = builder.build().execute();
        //TODO:如果不存在数据转换,是不是就不用执行info.setList()
        PageInfo<Role> info = new PageInfo<>(roleList);
        return Result.page(info);
    }

    @Transactional
    public Result insert(RoleBean roleBean, HttpServletRequest request) {
        Result result = dataStandard(roleBean);
        if (0 != result.getCode()) {
            return result;
        }
        //校验权限是否存在
        if (Objects.nonNull(roleBean.getPermission()) && (!roleBean.getPermission().equals(""))) {
            Result checkResult = this.checkPermission(roleBean);
            if (0 != checkResult.getCode()) {
                return checkResult;
            }
        }
        Role role = new Role();
        role.setName(roleBean.getName());
        role.setStatus(roleBean.getStatus());
        setInsertCol(role, request);
        if (roleMapper.insertSelective(role) > 0) {
            ArrayList<RoleMethod> roleMethodArrayList = buildRolePerList(role, roleBean, request);
            if (Objects.nonNull(roleMethodArrayList)) {
                for (int i = 0; i < roleMethodArrayList.size(); i++) {
                    if (roleMethodMapper.insertSelective(roleMethodArrayList.get(i)) < 0) {
                        return Result.businessError("添加角色失败!");
                    }
                }
            }
        }
        return Result.success();
    }

    @Transactional
    public Result delete(Long id, HttpServletRequest request) {
        if (Objects.isNull(id)) {
            return Result.paramError("id参数不能为空!");
        }
        Role old = roleMapper.selectByPrimaryKey(id);
        if (Objects.isNull(old)) {
            return Result.paramError("删除的角色不存在!");
        }
        old.setStatus(Status.DELETE.getCode());
        setUpdateCol(old, request);
        if (roleMapper.updateByPrimaryKeySelective(old) > 0) {
            //删除该角色对应的权限
            RoleMethodDynamicSqlSupport.RoleMethod roleMethod = RoleMethodDynamicSqlSupport.roleMethod;
            List<RoleMethod> roleMethodList = roleMethodMapper.selectByExample().where(roleMethod.roleId, IsEqualTo.of(() -> id))
                    .and(roleMethod.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(roleMethodList)) {
                Date now = new Date();
                Long loginUserId = LoginUserUtils.getLoginUserId(request);
                roleMethodList.forEach(row -> {
                    row.setStatus(Status.DELETE.getCode());
                    row.setUpdateUser(loginUserId);
                    row.setUpdateTime(now);
                    roleMethodMapper.updateByPrimaryKeySelective(row);
                });
            }
            return Result.success();
        }
        return Result.businessError("删除失败!");
    }

    @Transactional
    public Result update(RoleBean roleBean, HttpServletRequest request) {
        Result result = dataStandard(roleBean);
        if (0 != result.getCode()) {
            return result;
        }
        if (Objects.isNull(roleBean.getId())) {
            return Result.paramError("id参数不能为空!");
        }
        //1.更新role表
        Role role = new Role();
        role.setId(roleBean.getId());
        role.setName(roleBean.getName());
        role.setStatus(roleBean.getStatus());
        setUpdateCol(role, request);
        roleMapper.updateByPrimaryKeySelective(role);

        //2.更新role_permission表
        RoleMethodDynamicSqlSupport.RoleMethod roleMethod = RoleMethodDynamicSqlSupport.roleMethod;
        //权限被被清空
        if (Objects.isNull(roleBean.getPermission()) || roleBean.getPermission().equals("")) {
            List<RoleMethod> existPer = roleMethodMapper.selectByExample()
                    .where(roleMethod.roleId, IsEqualTo.of(roleBean::getId))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(existPer)) {
                for (int i = 0; i < existPer.size(); i++) {
                    RoleMethod rolePer = existPer.get(i);
                    rolePer.setStatus(Status.DELETE.getCode());
                    setUpdateCol(rolePer, request);
                    if (roleMethodMapper.updateByPrimaryKeySelective(rolePer) < 0) {
                        return Result.businessError("编辑角色失败!");
                    }
                }
            }
            //修改了部分权限
        } else {
            //验证权限是否存在
            Result checkResult = this.checkPermission(roleBean);
            if (0 != checkResult.getCode()) {
                return checkResult;
            }
            //查看现在角色存在的权限
            List<RoleMethod> existPer = roleMethodMapper.selectByExample()
                    .where(roleMethod.roleId, IsEqualTo.of(roleBean::getId))
                    .build()
                    .execute();
            String permission = roleBean.getPermission();
            //当前数据库中存在的
            List<Long> existList = existPer.stream()
                    .map((per) -> per.getMethodId())
                    .collect(Collectors.toList());

            List<Long> perList = tranceList(Arrays.asList(permission.split(",")));
            //1.重合的(状态置为激活)
            List<Long> repeatPermission = getRepeatPermission(existList, perList);
            editRecordStatus(roleBean, repeatPermission, Status.ENABLE.getCode(), request);
            //2.数据库有的权限记录，本次更新没有的权限(状态置为删除)
            existList.removeAll(repeatPermission);
            editRecordStatus(roleBean, existList, Status.DELETE.getCode(), request);
            //3.数据库中需要新增的记录(需要insert数据，状态置为激活)
            perList.removeAll(repeatPermission);
            if (perList.size() > 0) {
                ArrayList<RoleMethod> roleMethodArrayList = buildRolePerList(role, perList, request);
                if (Objects.nonNull(roleMethodArrayList) && roleMethodArrayList.size() > 0) {
                    for (int i = 0; i < roleMethodArrayList.size(); i++) {
                        if (roleMethodMapper.insertSelective(roleMethodArrayList.get(i)) < 0) {
                            return Result.businessError("编辑角色失败!");
                        }
                    }
                }
            }
        }
        return Result.success();
    }

    /**
     * @Description 如果id为空，传一个空数;否则传递该id代表的角色的权限树
     */
    public Result select(Long id) {
        HashMap<String, Object> resultMap = new HashMap<>();
        //用户权限
        Map<Long, Long> rolePerMap = new HashMap();
        //角色id不为空
        if (Objects.nonNull(id)) {
            Role role = roleMapper.selectByPrimaryKey(id);
            resultMap.put("role", role);

            RoleMethodDynamicSqlSupport.RoleMethod roleMethod = RoleMethodDynamicSqlSupport.roleMethod;
            List<RoleMethod> roleMethodList = roleMethodMapper.selectByExample()
                    .where(roleMethod.roleId, IsEqualTo.of(() -> id))
                    .and(roleMethod.status, IsEqualTo.of(Status.ENABLE::getCode))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(roleMethodList)) {
                roleMethodList.forEach(row -> {
                    rolePerMap.put(row.getMethodId(), row.getMethodId());
                });
            }
        }
        MethodDynamicSqlSupport.Method method = MethodDynamicSqlSupport.method;
        List<Method> methodList = methodMapper.selectByExample()
                .where(method.status, IsNotEqualTo.of(Status.DELETE::getCode))
                .build()
                .execute();
        List<MethodRes> resList = new ArrayList<>();
        HashMap<Long, List<MethodRes>> methodResHashMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(methodList)) {
            methodList.forEach(row -> {
                MethodRes res = new MethodRes();
                BeanUtils.copyProperties(row, res);
                Long parentid = row.getSuperiorId();
                //父节点
                if (parentid.equals(0L)) {
                    resList.add(res);
                    //子节点
                } else {
                    methodResHashMap.computeIfAbsent(res.getSuperiorId(), k -> {
                        return new ArrayList<>();
                    }).add(res);
                }
            });
            getTree(resList, methodResHashMap, rolePerMap);
        }
        resultMap.put("tree", resList);
        return Result.success(resultMap);
    }

    private static Result dataStandard(RoleBean roleBean) {
        if (Objects.isNull(roleBean.getName()) || roleBean.getName().equals("")) {
            return Result.paramError("角色名不能为空!");
        }
        if (Objects.isNull(roleBean.getStatus())) {
            return Result.paramError("角色状态不能为空!");
        }
        return Result.success();
    }

    private static ArrayList<Long> tranceList(List<String> list) {
        ArrayList<Long> result = new ArrayList<>();
        if (Objects.nonNull(list) && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                result.add(Long.parseLong(list.get(i)));
            }
            return result;
        }
        return result;
    }

    private static void setInsertCol(Role role, HttpServletRequest request) {
        role.setCreateTime(new Date());
        role.setCreateUser(LoginUserUtils.getLoginUserId(request));
    }

    private static void setUpdateCol(Role role, HttpServletRequest request) {
        role.setUpdateTime(new Date());
        role.setUpdateUser(LoginUserUtils.getLoginUserId(request));
    }

    private static void setInsertCol(RoleMethod roleMethod, HttpServletRequest request) {
        roleMethod.setCreateTime(new Date());
        roleMethod.setCreateUser(LoginUserUtils.getLoginUserId(request));
    }

    private static void setUpdateCol(RoleMethod roleMethod, HttpServletRequest request) {
        roleMethod.setUpdateTime(new Date());
        roleMethod.setUpdateUser(LoginUserUtils.getLoginUserId(request));
    }

    /**
     * @Description 验证权限是否存在
     */
    private Result checkPermission(RoleBean roleBean) {
        String permission = roleBean.getPermission();
        List<Long> perList = tranceList(Arrays.asList(permission.split(",")));
        MethodDynamicSqlSupport.Method p = MethodDynamicSqlSupport.method;
        List<Method> methodList = methodMapper.selectByExample()
                .where(p.status, IsNotEqualTo.of(Status.DELETE::getCode))
                .and(p.id, IsIn.of(perList))
                .build()
                .execute();
        //不存在的权限
        if (methodList.size() != perList.size()) {
            for (int i = 0; i < perList.size(); i++) {
                Long per = perList.get(i);
                boolean match = false;
                for (int j = 0; j < methodList.size(); j++) {
                    if (methodList.get(j).getId().equals(per)) {
                        methodList.remove(j);
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    return Result.paramError("id为" + per + "的权限不存在");
                }
            }
        }
        return Result.success();
    }

    /**
     * @Description 获取当前角色存在的权限记录与新加的权限重合的部分
     */
    private List<Long> getRepeatPermission(List<Long> existList, List<Long> perList) {
        List<Long> repeatePer = new ArrayList<>();
        for (int i = 0; i < existList.size(); i++) {
            Long permissionid = existList.get(i);
            if (perList.contains(permissionid)) {
                repeatePer.add(permissionid);
            }
        }
        return repeatePer;
    }

    /**
     * @Description 构建插入数据库的RolePermission
     */
    private static ArrayList<RoleMethod> buildRolePerList(Role role, RoleBean roleBean, HttpServletRequest request) {
        if (Objects.isNull(roleBean) || Objects.isNull(role)) {
            return null;
        }
        ArrayList<RoleMethod> RoleMethodList = new ArrayList<>();
        if (Objects.nonNull(roleBean.getPermission()) && !roleBean.getPermission().equals("")) {
            Long id = role.getId();
            List<Long> perArr = tranceList(Arrays.asList(roleBean.getPermission().split(",")));
            Date now = new Date();
            Long loginUserId = LoginUserUtils.getLoginUserId(request);
            for (int i = 0; i < perArr.size(); i++) {
                RoleMethod roleMethod = new RoleMethod();
                roleMethod.setCreateTime(now);
                roleMethod.setCreateUser(loginUserId);
                roleMethod.setStatus(Status.ENABLE.getCode());
                roleMethod.setMethodId(perArr.get(i));
                roleMethod.setRoleId(id);
                RoleMethodList.add(roleMethod);
            }
        }
        return RoleMethodList;
    }

    /**
     * @Description 构建插入数据库的RolePermission
     */
    private static ArrayList<RoleMethod> buildRolePerList(Role role, List<Long> perArr, HttpServletRequest request) {
        ArrayList<RoleMethod> roleMethodArrayList = new ArrayList<>();
        Long id = role.getId();
        Date now = new Date();
        Long loginUserId = LoginUserUtils.getLoginUserId(request);
        for (int i = 0; i < perArr.size(); i++) {
            RoleMethod roleMethod = new RoleMethod();
            roleMethod.setCreateTime(now);
            roleMethod.setCreateUser(loginUserId);
            roleMethod.setStatus(Status.ENABLE.getCode());
            roleMethod.setMethodId(perArr.get(i));
            roleMethod.setRoleId(id);
            roleMethodArrayList.add(roleMethod);
        }
        return roleMethodArrayList;
    }

    /**
     * @Description 编辑指定角色的权限状态
     */
    private void editRecordStatus(RoleBean roleBean, List<Long> permissionIds, Integer status, HttpServletRequest request) {
        if (!CollectionUtils.isEmpty(permissionIds)) {
            RoleMethodDynamicSqlSupport.RoleMethod roleMethod = RoleMethodDynamicSqlSupport.roleMethod;
            List<RoleMethod> rolePermissionList = roleMethodMapper.selectByExample()
                    .where(roleMethod.roleId, IsEqualTo.of(roleBean::getId))
                    .and(roleMethod.methodId, IsIn.of(permissionIds))
                    .build()
                    .execute();
            rolePermissionList.forEach(row -> {
                row.setStatus(status);
                setUpdateCol(row, request);
                roleMethodMapper.updateByPrimaryKeySelective(row);
            });
        }
    }

    private static void getTree(List<MethodRes> resList,
                                HashMap<Long, List<MethodRes>> methodResHashMap, Map<Long, Long> roleMethodMap) {
        if (Objects.isNull(resList) || Objects.isNull(methodResHashMap)) {
            return;
        }
        for (int i = 0; i < resList.size(); i++) {
            MethodRes row = resList.get(i);
            Long permissionID = row.getId();
            if (Objects.nonNull(roleMethodMap.get(permissionID))) {
                row.setPower(1L);
            }
            if (Objects.nonNull(methodResHashMap.get(row.getId()))) {
                Long id = row.getId();
                List<MethodRes> permissionRes = methodResHashMap.get(id);
                row.setLeafNodes(methodResHashMap.get(id));
                //移除掉，已经找到父节点的
                methodResHashMap.remove(id);
                //为剩余的节点，寻找父节点
                getTree(permissionRes, methodResHashMap, roleMethodMap);
            }
        }
        if (resList.size() == 0 || methodResHashMap.size() == 0) {
            return;
        }
    }
}
