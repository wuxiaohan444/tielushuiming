package com.jiangkai.framework.admin.security.authentication;

import com.jiangkai.framework.admin.bean.LoginUser;
import com.jiangkai.framework.admin.common.enums.Status;
import com.jiangkai.framework.admin.common.enums.YesOrNo;
import com.jiangkai.framework.source.mapper.*;
import com.jiangkai.framework.source.model.*;
import com.jiangkai.framework.utils.tools.ICollectionUtils;
import com.jiangkai.framework.utils.tools.IStringUtils;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsIn;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @author Administrator
 * @date 2019/4/28 11:21
 * 用户登陆判断逻辑
 */
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleMethodMapper roleMethodMapper;
    private final MethodMapper methodMapper;
    private final UserDeptMapper userDeptMapper;
    private final DeptMapper deptMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (IStringUtils.isEmpty(username))
            throw new UsernameNotFoundException("用户名不得为空");
        UserDynamicSqlSupport.User user = UserDynamicSqlSupport.user;
        List<User> userList = userMapper.selectByExample()
                .where(user.no, IsEqualTo.of(() -> username))
                .and(user.status, IsEqualTo.of(Status.ENABLE::getCode))
                .build().execute();
        if (CollectionUtils.isEmpty(userList))
            throw new UsernameNotFoundException("系统无此用户");
        if (userList.size() > 1)
            throw new UsernameNotFoundException("用户信息异常");
        User u = userList.get(0);
        UserRoleDynamicSqlSupport.UserRole userRole = UserRoleDynamicSqlSupport.userRole;
        List<UserRole> userRoleList = userRoleMapper.selectByExample()
                .where(userRole.userId, IsEqualTo.of(u::getId))
                .and(userRole.status, IsEqualTo.of(Status.ENABLE::getCode))
                .build().execute();
        if (CollectionUtils.isEmpty(userRoleList))
            throw new UsernameNotFoundException("用户无权限信息");
        RoleMethodDynamicSqlSupport.RoleMethod roleMethod = RoleMethodDynamicSqlSupport.roleMethod;
        List<RoleMethod> roleMethodList = roleMethodMapper.selectByExample()
                .where(roleMethod.roleId, IsIn.of(ICollectionUtils.toList(userRoleList, UserRole::getRoleId)))
                .and(roleMethod.status, IsEqualTo.of(Status.ENABLE::getCode))
                .build().execute();
        if (CollectionUtils.isEmpty(roleMethodList))
            throw new UsernameNotFoundException("用户无权限信息");
        MethodDynamicSqlSupport.Method method = MethodDynamicSqlSupport.method;
        //个人权限
        List<Method> methodList = methodMapper.selectByExample()
                .where(method.id, IsIn.of(ICollectionUtils.toList(roleMethodList, RoleMethod::getMethodId)), SqlBuilder.and(method.status, IsEqualTo.of(YesOrNo.YES::getCode)))
                .or(method.status, IsEqualTo.of(YesOrNo.NO::getCode))
                .build().execute();
        //个人的部门
        UserDeptDynamicSqlSupport.UserDept userDept = UserDeptDynamicSqlSupport.userDept;
        List<UserDept> deptUserList = userDeptMapper.selectByExample()
                .where(userDept.status, IsEqualTo.of(Status.ENABLE::getCode))
                .and(userDept.userId, IsEqualTo.of(u::getId))
                .build().execute();
        if (CollectionUtils.isEmpty(deptUserList))
            throw new UsernameNotFoundException("该用户无部门信息");
        Dept dept = deptMapper.selectByPrimaryKey(deptUserList.get(0).getDeptId());
        if (null == dept || !Status.ENABLE.getCode().equals(dept.getStatus()))
            throw new UsernameNotFoundException("用户所选部门不存在");
        LoginUser loginUser = new LoginUser(u, ICollectionUtils.toList(methodList, m -> new SimpleGrantedAuthority(m.getUrl())));
        loginUser.setDept(dept);
        return loginUser;
    }


}
