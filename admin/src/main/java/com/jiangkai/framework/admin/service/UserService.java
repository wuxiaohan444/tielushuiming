package com.jiangkai.framework.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangkai.framework.admin.bean.MethodRes;
import com.jiangkai.framework.admin.bean.UserBean;
import com.jiangkai.framework.admin.bean.UserInsertBean;
import com.jiangkai.framework.admin.bean.UserRes;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.common.enums.Status;
import com.jiangkai.framework.admin.constant.Constant;
import com.jiangkai.framework.admin.util.ListUtils;
import com.jiangkai.framework.admin.util.LoginUserUtils;
import com.jiangkai.framework.source.mapper.*;
import com.jiangkai.framework.source.model.*;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsIn;
import org.mybatis.dynamic.sql.where.condition.IsLike;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualTo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User: zvbb
 * Date: 2019/5/10
 * Description:
 */
@Component
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDeptMapper userDeptMapper;
    private final UserRoleMapper userRoleMapper;
    private final DeptMapper deptMapper;
    private final RoleMapper roleMapper;
    private final RoleMethodMapper roleMethodMapper;
    private final MethodMapper methodMapper;

    @Transactional
    public Result page(UserBean userBean) {
        if (userBean.getPage() == null) {
            userBean.setPage(Constant.PAGE);
        }
        if (userBean.getLimit() == null) {
            userBean.setLimit(Constant.LIMIT);
        }
        //分页
        PageHelper.startPage(userBean.getPage(), userBean.getLimit());

        //查询
        UserDynamicSqlSupport.User userSupport = UserDynamicSqlSupport.user;
        List<User> userList = userMapper.selectByExample()
                .where(userSupport.status, IsNotEqualTo.of(() -> Status.DELETE.getCode()))
                .and(userSupport.name, IsLike.of(() -> userBean.getName()).when(Objects::nonNull).then(s -> "%" + s + "%"))
                .and(userSupport.no, IsLike.of(() -> userBean.getNo()).when(Objects::nonNull).then(s -> "%" + s + "%"))
                .build().execute();

        PageInfo info = new PageInfo<>(userList);

        UserDeptDynamicSqlSupport.UserDept userDept = UserDeptDynamicSqlSupport.userDept;
        //用户与部门间的关系
        List<UserDept> userDeptList = userDeptMapper.selectByExample()
                .where(userDept.status, IsNotEqualTo.of(Status.DELETE::getCode))
                .build()
                .execute();
        UserRoleDynamicSqlSupport.UserRole userRole = UserRoleDynamicSqlSupport.userRole;
        //用户与角色间的关系
        List<UserRole> userRoleList = userRoleMapper.selectByExample()
                .where(userRole.status, IsNotEqualTo.of(Status.DELETE::getCode))
                .build()
                .execute();

        if (!CollectionUtils.isEmpty(userList)) {
            List<UserRes> resList = new ArrayList<>();
            HashMap<Long, List<UserRes>> deptMap = new HashMap<>();
            HashMap<Long, List<UserRes>> roleMap = new HashMap<>();
            for (int i = 0; i < userList.size(); i++) {
                UserRes res = new UserRes();
                User userRow = userList.get(i);
                BeanUtils.copyProperties(userRow, res);
                resList.add(res);
                Long userId = userRow.getId();
                if (!CollectionUtils.isEmpty(userDeptList)) {
                    for (int j = 0; j < userDeptList.size(); j++) {
                        UserDept userDeptRow = userDeptList.get(j);
                        if (userId.equals(userDeptRow.getUserId())) {
                            deptMap.computeIfAbsent(userDeptRow.getDeptId(), key -> new ArrayList<>()).add(res);
                        }
                    }
                }
                if (!CollectionUtils.isEmpty(userRoleList)) {
                    for (int j = 0; j < userRoleList.size(); j++) {
                        UserRole userRoleRow = userRoleList.get(j);
                        if (userId.equals(userRoleRow.getUserId())) {
                            roleMap.computeIfAbsent(userRoleRow.getRoleId(), key -> new ArrayList<>()).add(res);
                        }
                    }
                }
            }
            //部门名称
            DeptDynamicSqlSupport.Dept dept = DeptDynamicSqlSupport.dept;
            List<Dept> deptList = deptMapper.selectByExample()
                    .where(dept.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(deptList)) {
                deptList.forEach(deptRow -> {
                    if (Objects.nonNull(deptMap.get(deptRow.getId()))) {
                        if (Objects.nonNull(deptMap.get(deptRow.getId()))) {
                            deptMap.get(deptRow.getId()).forEach(userRes -> {
                                userRes.setDeptId(deptRow.getId());
                                userRes.setDeptName(deptRow.getName());
                            });
                        }
                    }
                });
            }

            //角色名称
            RoleDynamicSqlSupport.Role role = RoleDynamicSqlSupport.role;
            List<Role> roleList = roleMapper.selectByExample()
                    .where(role.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(roleList)) {
                roleList.forEach(roleRow -> {
                    if (Objects.nonNull(roleMap.get(roleRow.getId()))) {
                        roleMap.get(roleRow.getId()).forEach(userRes -> {
                            userRes.getRoleId().add(roleRow.getId());
                            userRes.getRoleName().add(roleRow.getName());
                        });
                    }
                });
            }
            info.setList(resList);
        }

        return Result.page(info);
    }

    @Transactional
    public Result insert(UserInsertBean userInsertBean, HttpServletRequest request) {
        //1.检查信息是否完整
        Result result = this.dataStandard(userInsertBean, true);
        if (0 != result.getCode()) {
            return result;
        }
        //3.密码加密
        String password = userInsertBean.getPassword();
        userInsertBean.setPassword(passwordEncoder.encode(password));
        //4.添加时间、添加人员id等字段
        Date now = new Date();
        Long loginUserId = LoginUserUtils.getLoginUserId(request);
        userInsertBean.setStatus(Status.ENABLE.getCode());
        userInsertBean.setCreateUser(loginUserId);
        userInsertBean.setCreateTime(now);

        //4.插入user表
        if (userMapper.insertSelective(userInsertBean) > 0) {
            //5.插入user_dept表,部门只能选一个
            UserDept userDept = new UserDept();
            userDept.setStatus(Status.ENABLE.getCode());
            userDept.setUserId(userInsertBean.getId());
            userDept.setDeptId(userInsertBean.getDeptId());
            userDept.setCreateTime(now);
            userDept.setCreateUser(loginUserId);
            userDeptMapper.insertSelective(userDept);

            //5.插入user_role表
            List<Long> roleIds = string2ListLong(userInsertBean.getRoleId());
            if (!CollectionUtils.isEmpty(roleIds)) {
                for (int i = 0; i < roleIds.size(); i++) {
                    UserRole userRole = new UserRole();
                    userRole.setStatus(Status.ENABLE.getCode());
                    userRole.setUserId(userInsertBean.getId());
                    userRole.setRoleId(roleIds.get(i));
                    userRole.setCreateTime(now);
                    userRole.setCreateUser(loginUserId);
                    userRoleMapper.insertSelective(userRole);
                }
            }

            return Result.success();
        } else {
            return Result.businessError("添加用户失败");
        }
    }

    public Result select(Long id) {
        if (Objects.isNull(id)) {
            return Result.businessError("id参数错误!");
        }
        //获取user表中的数据
        User user = userMapper.selectByPrimaryKey(id);
        if (Objects.isNull(user)) {
            return Result.paramError("用户不存在!");
        }
        UserRes res = new UserRes();
        BeanUtils.copyProperties(user, res);
        //获取角色信息
        UserRoleDynamicSqlSupport.UserRole userRole = UserRoleDynamicSqlSupport.userRole;
        List<UserRole> userRoleList = userRoleMapper.selectByExample()
                .where(userRole.status, IsNotEqualTo.of(Status.DELETE::getCode))
                .and(userRole.userId, IsEqualTo.of(() -> id))
                .build()
                .execute();
        if (!CollectionUtils.isEmpty(userRoleList)) {
            RoleDynamicSqlSupport.Role role = RoleDynamicSqlSupport.role;
            for (int i = 0; i < userRoleList.size(); i++) {
                UserRole userRoleRow = userRoleList.get(i);
                Long roleId = userRoleRow.getRoleId();
                res.getRoleId().add(roleId);
                List<Role> roleList = roleMapper.selectByExample()
                        .where(role.status, IsNotEqualTo.of(Status.DELETE::getCode))
                        .and(role.id, IsEqualTo.of(userRoleRow::getRoleId))
                        .build()
                        .execute();
                if (!CollectionUtils.isEmpty(roleList)) {
                    Role roleRow = roleList.get(0);
                    res.getRoleName().add(roleRow.getName());
                }
            }
        }
        //获取部门信息
        UserDeptDynamicSqlSupport.UserDept userDept = UserDeptDynamicSqlSupport.userDept;
        List<UserDept> userDeptList = userDeptMapper.selectByExample()
                .where(userDept.status, IsNotEqualTo.of(Status.DELETE::getCode))
                .and(userDept.userId, IsEqualTo.of(() -> id))
                .build()
                .execute();
        if (!CollectionUtils.isEmpty(userDeptList)) {
            if (userDeptList.size() > 1) {
                return Result.businessError("该用户存在异常,一个用户不能属于多个部门!");
            }
            UserDept userDeptRow = userDeptList.get(0);
            DeptDynamicSqlSupport.Dept dept = DeptDynamicSqlSupport.dept;
            List<Dept> deptList = deptMapper.selectByExample()
                    .where(dept.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .and(dept.id, IsEqualTo.of(userDeptRow::getDeptId))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(deptList)) {
                Dept deptRow = deptList.get(0);
                res.setDeptType(deptRow.getDepttype());
                res.setDeptId(deptRow.getId());
                res.setDeptName(deptRow.getName());
                Long superiorId = deptRow.getSuperiorId();
                res.setSuperiorId(superiorId);
                if (superiorId != 0) {
                    Dept superiorDept = deptMapper.selectByPrimaryKey(superiorId);
                    if (Objects.nonNull(superiorDept)) {
                        res.setSuperiorName(superiorDept.getName());
                    }
                }
            }
        }
        return Result.success(res);
    }

    public Result self(HttpServletRequest request) {
        Long loginUserId = LoginUserUtils.getLoginUserId(request);
        UserDynamicSqlSupport.User user = UserDynamicSqlSupport.user;
        List<User> users = userMapper.selectByExample()
                .where(user.id, IsEqualTo.of(() -> loginUserId))
                .build()
                .execute();

        return Result.success(users);
    }

    @Transactional
    public Result delete(Long id, HttpServletRequest request) {
        if (Objects.isNull(id)) {
            return Result.paramError("id参数错误!");
        }
        User old = userMapper.selectByPrimaryKey(id);
        if (Objects.isNull(old)) {
            return Result.paramError("删除的用户不存在!");
        }
        if (Objects.nonNull(old.getNo()) && old.getNo().equals("admin")) {
            return Result.paramError("admin用户不能删除!");
        }
        Date now = new Date();
        Long loginUserId = LoginUserUtils.getLoginUserId(request);
        //1.删除user表
        old.setStatus(Status.DELETE.getCode());
        old.setUpdateTime(now);
        old.setUpdateUser(loginUserId);
        if (userMapper.updateByPrimaryKeySelective(old) > 0) {
            //2.删除user_role表
            UserRoleDynamicSqlSupport.UserRole userRole = UserRoleDynamicSqlSupport.userRole;
            List<UserRole> userRoleList = userRoleMapper.selectByExample()
                    .where(userRole.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .and(userRole.userId, IsEqualTo.of(() -> id))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(userRoleList)) {
                for (int i = 0; i < userRoleList.size(); i++) {
                    UserRole userRoleRow = userRoleList.get(i);
                    userRoleRow.setStatus(Status.DELETE.getCode());
                    userRoleRow.setUpdateTime(now);
                    userRoleRow.setUpdateUser(loginUserId);
                    userRoleMapper.updateByPrimaryKeySelective(userRoleRow);
                }
            }
            //3.删除user_dept表
            UserDeptDynamicSqlSupport.UserDept userDept = UserDeptDynamicSqlSupport.userDept;
            List<UserDept> userDeptList = userDeptMapper.selectByExample()
                    .where(userDept.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .and(userDept.userId, IsEqualTo.of(() -> id))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(userDeptList)) {
                for (int i = 0; i < userDeptList.size(); i++) {
                    UserDept userDeptRow = userDeptList.get(i);
                    userDeptRow.setStatus(Status.DELETE.getCode());
                    userDeptRow.setCreateTime(now);
                    userDeptRow.setCreateUser(loginUserId);
                    userDeptMapper.updateByPrimaryKeySelective(userDeptRow);
                }
            }
            return Result.success();
        } else {
            return Result.businessError("用户删除失败!");
        }
    }

    @Transactional
    public Result update(UserInsertBean userInsertBean, HttpServletRequest request) {
        Result result = dataStandard(userInsertBean, false);
        if (0 != result.getCode()) {
            return result;
        }
        //1.修改user表
        Long id = userInsertBean.getId();
        User old = userMapper.selectByPrimaryKey(id);
        if (Objects.isNull(old)) {
            return Result.paramError("该用户不存在!");
        }
        String oldPassword = old.getPassword();
        if (!oldPassword.equals(userInsertBean.getPassword())) {
            String newPasswd = passwordEncoder.encode(userInsertBean.getPassword());
            userInsertBean.setPassword(newPasswd);
        }
        Date now = new Date();
        Long loginUserId = LoginUserUtils.getLoginUserId(request);
        userInsertBean.setUpdateTime(now);
        userInsertBean.setUpdateUser(loginUserId);
        userMapper.updateByPrimaryKeySelective(userInsertBean);
        List<Long> roleIdList = string2ListLong(userInsertBean.getRoleId());
        //2.修改user_role表
        UserRoleDynamicSqlSupport.UserRole userRole = UserRoleDynamicSqlSupport.userRole;
        List<UserRole> userRoleList = userRoleMapper.selectByExample()
                .where(userRole.userId, IsEqualTo.of(userInsertBean::getId))
                .build()
                .execute();
        //2.1 角色设置被清空
        if (CollectionUtils.isEmpty(string2ListLong(userInsertBean.getRoleId()))) {
            //把user_role表的相关记录删除
            if (!CollectionUtils.isEmpty(userRoleList)) {
                for (int i = 0; i < userRoleList.size(); i++) {
                    UserRole userRoleRow = userRoleList.get(i);
                    userRoleRow.setStatus(Status.DELETE.getCode());
                    userRoleRow.setUpdateTime(now);
                    userRoleRow.setUpdateUser(loginUserId);
                    userRoleMapper.updateByPrimaryKeySelective(userRoleRow);
                }
            }
        } else {
            if (!CollectionUtils.isEmpty(userRoleList)) {
                //user_role表中已经存在的角色记录
                List<Long> existRoleIDs = userRoleList.stream()
                        .map(userRoleRow -> userRoleRow.getRoleId())
                        .collect(Collectors.toList());
                //交集 existRoleIDs ^ roleId
                List interSectionList = ListUtils.interSection(existRoleIDs, roleIdList);
                updateUserRoleStatus(userRoleList, interSectionList, Status.ENABLE.getCode(), now, loginUserId);
                //差集 existRoleIDs - interSectionList
                existRoleIDs.removeAll(interSectionList);
                if (!CollectionUtils.isEmpty(existRoleIDs)) {
                    updateUserRoleStatus(userRoleList, existRoleIDs, Status.DELETE.getCode(), now, loginUserId);
                }
                //差集 roleId - interSectionList
                roleIdList.removeAll(interSectionList);
                if (!CollectionUtils.isEmpty(roleIdList)) {
                    insertUserRole(userInsertBean.getId(), roleIdList, Status.ENABLE.getCode(), now, loginUserId);
                }
            }
        }

        //3. 修改user_dept表
        UserDeptDynamicSqlSupport.UserDept userDept = UserDeptDynamicSqlSupport.userDept;
        List<UserDept> userDeptList = userDeptMapper.selectByExample()
                .where(userDept.userId, IsEqualTo.of(userInsertBean::getId))
                .build()
                .execute();
        Long deptId = userInsertBean.getDeptId();
        if (!CollectionUtils.isEmpty(userDeptList)) {
            List<Long> existDeptList = userDeptList.stream()
                    .map(row -> row.getDeptId())
                    .collect(Collectors.toList());
            //3.1修改已经存在的记录
            if (!CollectionUtils.isEmpty(existDeptList)) {
                //把该用户 role_dept的记录都删除
                updateUserDeptStatus(userDeptList, existDeptList, Status.DELETE.getCode(), now, loginUserId);
                if (existDeptList.contains(deptId)) {
                    //更新已经存在的记录 的 status状态
                    updateUserOneDeptStatus(userDeptList, deptId, Status.ENABLE.getCode(), now, loginUserId);
                    return Result.success();
                }
            }
            //3.2 user_dept表插入数据
            UserDept newUserDept = new UserDept();
            newUserDept.setUserId(userInsertBean.getId());
            newUserDept.setDeptId(userInsertBean.getDeptId());
            newUserDept.setStatus(Status.ENABLE.getCode());
            newUserDept.setCreateTime(now);
            newUserDept.setCreateUser(loginUserId);
            userDeptMapper.insertSelective(newUserDept);

        }

        return Result.success();
    }

    @Transactional
    public Result updateSelf(User user, HttpServletRequest request) {
        Result result = userStandard(user, request, false);
        if (0 != result.getCode()) {
            return result;
        }
        Long loginUserId = LoginUserUtils.getLoginUserId(request);
        User old = userMapper.selectByPrimaryKey(loginUserId);
        if (Objects.nonNull(old)) {
            //修改密码
            String oldPassword = old.getPassword();
            if (!oldPassword.equals(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (userMapper.updateByPrimaryKeySelective(user) > 0) {
                return Result.success();
            } else {
                return Result.businessError("修改失败!");
            }
        } else {
            return Result.paramError("修改的用户不存在!");
        }
    }

    /**
     * @Description 返回用户设置页面的菜单
     */
    public Result menuControl(HttpServletRequest request) {
        Long loginUserId = LoginUserUtils.getLoginUserId(request);
        if (Objects.isNull(loginUserId)) {
            return Result.businessError("用户没有登陆!");
        }
        //获取user表中的数据
        User user = userMapper.selectByPrimaryKey(loginUserId);
        if (Objects.isNull(user)) {
            return Result.paramError("用户不存在!");
        }
        List<MethodRes> resList = new ArrayList<>();
        //获取角色信息
        UserRoleDynamicSqlSupport.UserRole userRole = UserRoleDynamicSqlSupport.userRole;
        //1.获取该用户有哪些角色
        List<UserRole> userRoleList = userRoleMapper.selectByExample()
                .where(userRole.status, IsNotEqualTo.of(Status.DELETE::getCode))
                .and(userRole.userId, IsEqualTo.of(() -> loginUserId))
                .build()
                .execute();
        if (!CollectionUtils.isEmpty(userRoleList)) {
            RoleMethodDynamicSqlSupport.RoleMethod roleMethod = RoleMethodDynamicSqlSupport.roleMethod;
            List<Long> roleIdList = userRoleList.stream().map(row -> row.getRoleId()).collect(Collectors.toList());
            //2.角色有哪些权限
            List<RoleMethod> roleMethodList = roleMethodMapper.selectByExample()
                    .where(roleMethod.status, IsEqualTo.of(Status.ENABLE::getCode))
                    .and(roleMethod.roleId, IsIn.of(roleIdList))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(roleMethodList)) {
                HashSet<Long> methodIdSet = new HashSet();
                for (int i = 0; i < roleMethodList.size(); i++) {
                    RoleMethod item = roleMethodList.get(i);
                    methodIdSet.add(item.getMethodId());
                }
                //3.权限到底有哪些，以及权限间的层次关系
                MethodDynamicSqlSupport.Method method = MethodDynamicSqlSupport.method;
                MyBatis3SelectModelAdapter<List<Method>> build = (MyBatis3SelectModelAdapter<List<Method>>) methodMapper.selectByExample()
                        .where(method.status, IsEqualTo.of(Status.ENABLE::getCode))
                        .and(method.id, IsIn.of(new ArrayList(methodIdSet)))
                        .build();
                List<Method> methodList = build.execute();
                if (!CollectionUtils.isEmpty(methodList)) {
                    HashMap<Long, List<MethodRes>> subTreeMap = new HashMap<>();
                    for (int i = 0; i < methodList.size(); i++) {
                        Method methodRow = methodList.get(i);
                        MethodRes res = new MethodRes();
                        BeanUtils.copyProperties(methodRow, res);
                        //顶级权限
                        if (0 == methodRow.getSuperiorId()) {
                            resList.add(res);
                        } else {
                            subTreeMap.computeIfAbsent(methodRow.getSuperiorId(), k -> new ArrayList<MethodRes>()).add(res);
                        }
                    }
                    getMethodTree(resList, subTreeMap);
                }
                return Result.success(resList);
            } else {
                return Result.success("该用户所属角色，没有权限");
            }
        } else {
            return Result.success("该用户没有分配角色!");
        }
    }

//    @Transactional
//    public Result uploadPortrait(String fileNetUrl, HttpServletRequest request) {
//        Long loginUserId = LoginUserUtils.getLoginUserId(request);
//        User old = userMapper.selectByPrimaryKey(loginUserId);
//        if (Objects.isNull(old)) {
//            return Result.businessError("该用户不存在,头像修改失败!");
//        }
//        old.setHeadImg(fileNetUrl);
//        old.setUpdateUser(loginUserId);
//        old.setUpdateTime(new Date());
//        userMapper.updateByPrimaryKeySelective(old);
//        return Result.success(fileNetUrl);
//    }

    /**
     * @Description 验证数据是否符合规范
     */
    private Result dataStandard(UserInsertBean userInsertBean, boolean isInsert) {
        if (Objects.isNull(userInsertBean)) {
            return Result.paramError("前后端数据映射出错!");
        }
        if (!StringUtils.hasText(userInsertBean.getName())) {
            return Result.paramError("用户名不能为空!");
        }
        if (!StringUtils.hasText(userInsertBean.getPassword())) {
            return Result.paramError("密码不能为空!");
        }
        if (!StringUtils.hasText(userInsertBean.getNo())) {
            return Result.paramError("用户编号不能为空!");
        }
        if (!StringUtils.hasText(userInsertBean.getIdCard())) {
            return Result.paramError("身份证号不能为空!");
        }
        if (!StringUtils.hasText(userInsertBean.getPhone())) {
            return Result.paramError("手机号不能为空!");
        }
        if (Objects.isNull(userInsertBean.getSex())) {
            return Result.paramError("性别不能为空!");
        }
        if (Objects.isNull(userInsertBean.getDeptId())) {
            return Result.paramError("部门id不能为空!");
        }
        if (!StringUtils.hasText(userInsertBean.getRoleId())) {
            return Result.paramError("角色id不能为空!");
        }
        //2.插入操作检查用户编号,是否重复
        if (isInsert) {
            UserDynamicSqlSupport.User user = UserDynamicSqlSupport.user;
            List<User> existList = userMapper.selectByExample()
                    .where(user.no, IsEqualTo.of(userInsertBean::getNo))
                    .and(user.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(existList) && existList.size() > 0) {
                return Result.paramError("用户编号已存在!");
            }
        } else {
            //更新操作,检查除该用户本身的编号
            if (Objects.isNull(userInsertBean.getId())) {
                return Result.paramError("id参数错误!");
            }
            UserDynamicSqlSupport.User user = UserDynamicSqlSupport.user;
            List<User> existList = userMapper.selectByExample()
                    .where(user.no, IsEqualTo.of(userInsertBean::getNo))
                    .and(user.id, IsNotEqualTo.of(userInsertBean::getId))
                    .and(user.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(existList) && existList.size() > 0) {
                return Result.paramError("用户编号已存在!");
            }
        }
        List<Long> roleIdList = string2ListLong(userInsertBean.getRoleId());
        if (roleIdList.size() > 0) {
            //3.检查角色是否存在
            RoleDynamicSqlSupport.Role role = RoleDynamicSqlSupport.role;
            List<Role> roleList = roleMapper.selectByExample()
                    .where(role.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .and(role.id, IsIn.of(roleIdList))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(roleList)) {
                if (roleList.size() != roleIdList.size()) {
                    return Result.paramError("角色不存在!");
                }
            } else {
                return Result.paramError("角色不存在!");
            }
        }
        //4.检查部门是否存在
        Dept dept = deptMapper.selectByPrimaryKey(userInsertBean.getDeptId());
        if (Objects.isNull(dept)) {
            return Result.paramError("该部门不存在!");
        }
        return Result.success();
    }

    /**
     * @Description 验证数据是否符合规范
     */
    private Result userStandard(User user, HttpServletRequest request, boolean isInsert) {
        if (Objects.isNull(user)) {
            return Result.paramError("前后端数据映射出错!");
        }
        if (Objects.isNull(user.getName())) {
            return Result.paramError("用户名不能为空!");
        }
        if (Objects.isNull(user.getPassword())) {
            return Result.paramError("密码不能为空!");
        }
        if (Objects.isNull(user.getNo())) {
            return Result.paramError("用户编号不能为空!");
        }
        if (Objects.isNull(user.getIdCard())) {
            return Result.paramError("身份证号不能为空!");
        }
        if (Objects.isNull(user.getPhone())) {
            return Result.paramError("手机号不能为空!");
        }
        if (Objects.isNull(user.getSex())) {
            return Result.paramError("性别不能为空!");
        }
        if (Objects.isNull(user.getId())) {
            return Result.paramError("id参数错误!");
        } else {
            Long loginUserId = LoginUserUtils.getLoginUserId(request);
            if (user.getId() != loginUserId) {
                return Result.paramError("不能修改其他用户的信息!");
            }
        }

        //2.插入操作检查用户编号,是否重复
        if (isInsert) {
            UserDynamicSqlSupport.User userSupport = UserDynamicSqlSupport.user;
            List<User> existList = userMapper.selectByExample()
                    .where(userSupport.no, IsEqualTo.of(user::getNo))
                    .and(userSupport.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(existList) && existList.size() > 0) {
                return Result.paramError("用户编号已存在!");
            }
        } else {
            //更新操作,检查除该用户本身的编号
            UserDynamicSqlSupport.User userSupport = UserDynamicSqlSupport.user;
            List<User> existList = userMapper.selectByExample()
                    .where(userSupport.no, IsEqualTo.of(user::getNo))
                    .and(userSupport.id, IsNotEqualTo.of(user::getId))
                    .and(userSupport.status, IsNotEqualTo.of(Status.DELETE::getCode))
                    .build()
                    .execute();
            if (!CollectionUtils.isEmpty(existList) && existList.size() > 0) {
                return Result.paramError("用户编号已存在!");
            }
        }
        return Result.success();
    }

    /**
     * @param userRoleList 数据库中当前的记录
     * @param setList      需要修改的记录
     * @param status       修改后的状态
     * @param now          修改的日期
     * @param loginUserId  修改记录的是哪个用户
     * @return
     * @Description 修改数据库中User_Role表中某些记录的状态位
     * @date 2019/6/22
     * @auther zvbb
     */
    private void updateUserRoleStatus(List<UserRole> userRoleList, List<Long> setList, Integer status, Date now, Long loginUserId) {
        if (!CollectionUtils.isEmpty(userRoleList) && !CollectionUtils.isEmpty(setList) && Objects.nonNull(status)
                && Objects.nonNull(now) && Objects.nonNull(loginUserId)) {
            for (int i = 0; i < userRoleList.size(); i++) {
                UserRole userRoleRow = userRoleList.get(i);
                for (int j = 0; j < setList.size(); j++) {
                    Long roleID = setList.get(j);
                    if (Objects.nonNull(userRoleRow.getRoleId()) && Objects.nonNull(roleID)) {
                        if (userRoleRow.getRoleId().equals(roleID)) {
                            userRoleRow.setStatus(status);
                            userRoleRow.setRoleId(roleID);
                            userRoleRow.setUpdateTime(now);
                            userRoleRow.setUpdateUser(loginUserId);
                            userRoleMapper.updateByPrimaryKeySelective(userRoleRow);
                            break;
                        }
                    } else {
                        if (userRoleRow.getRoleId() == roleID) {
                            userRoleRow.setStatus(status);
                            userRoleRow.setRoleId(roleID);
                            userRoleRow.setUpdateTime(now);
                            userRoleRow.setUpdateUser(loginUserId);
                            userRoleMapper.updateByPrimaryKeySelective(userRoleRow);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * @param userID      用户id
     * @param setList     需要修改的记录
     * @param status      修改后的状态
     * @param now         修改的日期
     * @param loginUserId 修改记录的是哪个用户
     * @return
     * @Description 修改数据库中User_Role表中某些记录的状态位
     * @date 2019/6/22
     * @auther zvbb
     */
    private void insertUserRole(Long userID, List<Long> setList, Integer status, Date now, Long loginUserId) {
        if (!CollectionUtils.isEmpty(setList) && Objects.nonNull(status) && Objects.nonNull(now) && Objects.nonNull(loginUserId)) {
            for (int j = 0; j < setList.size(); j++) {
                Long roleID = setList.get(j);
                UserRole userRole = new UserRole();
                userRole.setUserId(userID);
                userRole.setRoleId(roleID);
                userRole.setStatus(status);
                userRole.setCreateTime(now);
                userRole.setCreateUser(loginUserId);
                userRoleMapper.insertSelective(userRole);
            }
        }
    }

    /**
     * @param userDeptList 数据库中当前的记录
     * @param setList      需要修改的记录
     * @param status       修改后的状态
     * @param now          修改的日期
     * @param loginUserId  修改记录的是哪个用户
     * @return
     * @Description 修改数据库中User_Role表中某些记录的状态位
     * @date 2019/6/22
     * @auther zvbb
     */
    private void updateUserDeptStatus(List<UserDept> userDeptList, List<Long> setList, Integer status, Date now, Long loginUserId) {
        if (!CollectionUtils.isEmpty(userDeptList) && !CollectionUtils.isEmpty(setList) && Objects.nonNull(status)
                && Objects.nonNull(now) && Objects.nonNull(loginUserId)) {
            for (int i = 0; i < userDeptList.size(); i++) {
                UserDept userDeptRow = userDeptList.get(i);
                for (int j = 0; j < setList.size(); j++) {
                    Long deptID = setList.get(j);
                    if (Objects.nonNull(userDeptRow.getDeptId()) && Objects.nonNull(deptID)) {
                        if (userDeptRow.getDeptId().equals(deptID)) {
                            userDeptRow.setStatus(status);
                            userDeptRow.setDeptId(deptID);
                            userDeptRow.setUpdateTime(now);
                            userDeptRow.setUpdateUser(loginUserId);
                            userDeptMapper.updateByPrimaryKeySelective(userDeptRow);
                            break;
                        }
                    } else {
                        if (userDeptRow.getDeptId() == deptID) {
                            userDeptRow.setStatus(status);
                            userDeptRow.setDeptId(deptID);
                            userDeptRow.setUpdateTime(now);
                            userDeptRow.setUpdateUser(loginUserId);
                            userDeptMapper.updateByPrimaryKeySelective(userDeptRow);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * @param userDeptList 数据库中当前的记录
     * @param deptID       需要修改的记录
     * @param status       修改后的状态
     * @param now          修改的日期
     * @param loginUserId  修改记录的是哪个用户
     * @return
     * @Description 修改数据库中User_Role表中某些记录的状态位
     * @date 2019/6/22
     * @auther zvbb
     */
    private void updateUserOneDeptStatus(List<UserDept> userDeptList, Long deptID, Integer status, Date now, Long loginUserId) {
        if (!CollectionUtils.isEmpty(userDeptList) && Objects.nonNull(deptID) && Objects.nonNull(status)
                && Objects.nonNull(now) && Objects.nonNull(loginUserId)) {
            for (int i = 0; i < userDeptList.size(); i++) {
                UserDept userDeptRow = userDeptList.get(i);
                if (Objects.nonNull(userDeptRow.getDeptId()) && Objects.nonNull(deptID)) {
                    if (userDeptRow.getDeptId().equals(deptID)) {
                        userDeptRow.setStatus(status);
                        userDeptRow.setDeptId(deptID);
                        userDeptRow.setUpdateTime(now);
                        userDeptRow.setUpdateUser(loginUserId);
                        userDeptMapper.updateByPrimaryKeySelective(userDeptRow);
                        break;
                    }
                } else {
                    if (userDeptRow.getDeptId() == deptID) {
                        userDeptRow.setStatus(status);
                        userDeptRow.setDeptId(deptID);
                        userDeptRow.setUpdateTime(now);
                        userDeptRow.setUpdateUser(loginUserId);
                        userDeptMapper.updateByPrimaryKeySelective(userDeptRow);
                        break;
                    }
                }
            }
        }
    }

    public static List string2ListLong(String str) {
        List<Long> result = new ArrayList();
        if (Objects.nonNull(str) && !str.equals("")) {
            String[] arr = str.split(",");
            if (Objects.nonNull(arr) && arr.length > 0) {
                for (int i = 0; i < arr.length; i++) {
                    result.add(Long.parseLong(arr[i]));
                }
            }
        }
        return result;
    }

    /**
     * @param topNodeList 树的顶级节点
     * @param subTreeMap  <父节点id,子树节点>子树集合
     * @return
     * @Description 构造权限树
     * @date 2019/6/24
     * @auther zvbb
     */
    public static void getMethodTree(List<MethodRes> topNodeList, HashMap<Long, List<MethodRes>> subTreeMap) {
        if (Objects.isNull(topNodeList) || Objects.isNull(subTreeMap)) {
            return;
        }
        if (CollectionUtils.isEmpty(topNodeList)) {
            return;
        }
        for (int i = 0; i < topNodeList.size(); i++) {
            MethodRes methodRes = topNodeList.get(i);
            Long superiorId = methodRes.getSuperiorId();
            if (Objects.nonNull(subTreeMap.get(superiorId))) {
                List<MethodRes> subNodeList = subTreeMap.get(superiorId);
                methodRes.setLeafNodes(subNodeList);
                getMethodTree(subNodeList, subTreeMap);
            }
        }
    }

//    public static void main(String[] args) {
//        // 使用双冒号::来构造静态函数引用
//        Function<String, Integer> fun = Integer::parseInt;
//        Integer value = fun.apply("123");
//        System.out.println(value);
//
//        // 使用双冒号::来构造非静态函数引用
//        String content = "Hello JDK8";
//        Function<Integer, String> func = content::substring;
//        String result = func.apply(1);
//        System.out.println(result);
//
//        // 构造函数引用
//        BiFunction<String, Integer, User> biFunction = User::new;
//        User user = biFunction.apply("mengday", 28);
//        System.out.println(user.toString());
//
//        // 函数引用也是一种函数式接口，所以也可以将函数引用作为方法的参数
//        sayHello(String::toUpperCase, "hello");
//    }
//
//    // 方法有两个参数，一个是
//    private static void sayHello(Function<String, String> func, String parameter){
//        String result = func.apply(parameter);
//        System.out.println(result);
//    }
}
