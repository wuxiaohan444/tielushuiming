package com.jiangkai.framework.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangkai.framework.admin.bean.DeptBean;
import com.jiangkai.framework.admin.bean.DeptRes;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.common.enums.Status;
import com.jiangkai.framework.admin.constant.Constant;
import com.jiangkai.framework.admin.util.LoginUserUtils;
import com.jiangkai.framework.source.mapper.DeptDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.DeptMapper;
import com.jiangkai.framework.source.mapper.UserDeptDynamicSqlSupport;
import com.jiangkai.framework.source.mapper.UserDeptMapper;
import com.jiangkai.framework.source.model.Dept;
import com.jiangkai.framework.source.model.UserDept;
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

/**
 * User: zvbb
 * Date: 2019/5/13
 * Description:
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class DeptService {
    private final DeptMapper deptMapper;
    private final UserDeptMapper userDeptMapper;

    public Result page(DeptBean deptBean) {
        if (deptBean.getPage() == null) {
            deptBean.setPage(Constant.PAGE);
        }
        if (deptBean.getLimit() == null) {
            deptBean.setLimit(Constant.LIMIT);
        }

        //填写查询条件
        DeptDynamicSqlSupport.Dept dept = DeptDynamicSqlSupport.dept;
        //TODO：变量尽量使用时创建
//        List<Dept> deptList = null;
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Dept>>>.QueryExpressionWhereBuilder builder
                = deptMapper.selectByExample().where(dept.status, IsNotEqualTo.of(Status.DELETE::getCode));
        if (Objects.nonNull(deptBean.getNo())) {
            builder.and(dept.no, IsLike.of(() -> "%" + deptBean.getNo() + "%"));
        }
        if (Objects.nonNull(deptBean.getName())) {
            builder.and(dept.name, IsLike.of(() -> "%" + deptBean.getName() + "%"));
        }
        if (Objects.nonNull(deptBean.getDeptType())) {
            builder.and(dept.depttype, IsEqualTo.of(deptBean::getDeptType));
        }
        //TODO：分页
        PageHelper.startPage(deptBean.getPage(), deptBean.getLimit());
        //查询
        List<Dept> deptList = builder.build().execute();
        PageInfo info = new PageInfo(deptList);
        if (!CollectionUtils.isEmpty(deptList)) {
            Map<Long, List<DeptRes>> deptMap = new HashMap<>();
            List<DeptRes> resList = new ArrayList<>(deptList.size());
            deptList.forEach(d -> {
                DeptRes res = new DeptRes();
                BeanUtils.copyProperties(d, res);
                if (res.getSuperiorId() == 0) {
                    res.setSuperiorName("顶级部门");
                } else {
                    deptMap.computeIfAbsent(d.getSuperiorId(), k -> new ArrayList<>()).add(res);
                }
                resList.add(res);
            });
            //子级部门的上级部门名称
            if (!CollectionUtils.isEmpty(deptMap.keySet())) {
                List<Dept> superDeptList = deptMapper.selectByExample()
                        .where(dept.id, IsIn.of(new ArrayList<>(deptMap.keySet())))
                        .build().execute();
                superDeptList.forEach(superior -> {
                    deptMap.get(superior.getId()).forEach(deptRes ->
                            deptRes.setSuperiorName(superior.getName())
                    );
                });
            }
            //TODO:容易遗漏
            info.setList(resList);
        }
        return Result.page(info);
    }

    /**
     * @Description 返回用户可以管理的部门列表
     */
    public Result deptControl(HttpServletRequest request) {
        Long loginUserId = LoginUserUtils.getLoginUserId(request);
        if (Objects.isNull(loginUserId)) {
            return Result.businessError("用户没有登陆!");
        }
        UserDeptDynamicSqlSupport.UserDept userDept = UserDeptDynamicSqlSupport.userDept;
        //1.用户所属部门
        List<UserDept> userDeptList = userDeptMapper.selectByExample()
                .where(userDept.status, IsEqualTo.of(Status.ENABLE::getCode))
                .and(userDept.userId, IsEqualTo.of(() -> loginUserId))
                .build()
                .execute();
        if (CollectionUtils.isEmpty(userDeptList)) {
            return Result.businessError("该用户没有分配部门!");
        }
        UserDept userDeptRow = userDeptList.get(0);
        //用户的部门id
        Long deptId = userDeptRow.getDeptId();
        if (Objects.isNull(deptId)) {
            return Result.businessError("该用户没有分配部门!");
        }

        DeptDynamicSqlSupport.Dept dept = DeptDynamicSqlSupport.dept;
        List<Dept> deptList = deptMapper.selectByExample()
                .where(dept.status, IsEqualTo.of(Status.ENABLE::getCode))
                .and(dept.id, IsEqualTo.of(() -> deptId))
                .build()
                .execute();
        if (CollectionUtils.isEmpty(deptList)) {
            return Result.businessError("用户分配的部门不存在!");
        } else {
            Dept deptRow = deptList.get(0);
            //判断是否为 "广铁集团"
            if (deptRow.getSuperiorId() == 0) {
                return this.selectLeafs(deptId);
            } else {
                return Result.success(deptList.get(0));
            }
        }
    }

    @Transactional
    public Result insert(Dept dept, HttpServletRequest request) {
        if (null == dept.getName())
            return Result.paramError("部门名称不得为空");
        if (null == dept.getNo())
            return Result.paramError("部门编号不得为空");
        if (null == dept.getDepttype())
            return Result.paramError("部门类型不得为空");
        if (null == dept.getSuperiorId())
            return Result.paramError("上级部门不得为空");
        //默认激活
        dept.setStatus(Status.ENABLE.getCode());
        if (0L != dept.getSuperiorId()) {
            Dept superior = deptMapper.selectByPrimaryKey(dept.getSuperiorId());
            if (null == superior)
                return Result.businessError("上级部门不存在");
            dept.setPath(superior.getPath() + '-' + superior.getId());
        } else {
            dept.setPath("0");
        }
        dept.setCreateTime(new Date());
        dept.setCreateUser(LoginUserUtils.getLoginUserId(request));
        if (deptMapper.insertSelective(dept) > 0) {
            return Result.success();
        } else {
            return Result.businessError("添加用户失败");
        }
    }

    public Result select(Long id) {
        if (null == id) {
            return Result.businessError("参数错误!");
        }
        Dept result = deptMapper.selectByPrimaryKey(id);
        DeptRes res = new DeptRes();
        BeanUtils.copyProperties(result, res);
        Dept superDept = deptMapper.selectByPrimaryKey(result.getSuperiorId());
        //判断有没有 上级部门
        if (null == superDept) {
            return Result.success(res);
        }
        res.setSuperiorName(superDept.getName());
        return Result.success(res);
    }

    @Transactional
    public Result delete(Long id, HttpServletRequest request) {
        if (id == null) {
            return Result.businessError("参数错误!");
        }
        Dept old = deptMapper.selectByPrimaryKey(id);
        if (null == old)
            return Result.businessError("该部门不存在");
        Dept dept = new Dept();
        dept.setStatus(Status.DELETE.getCode());
        dept.setUpdateTime(new Date());
        dept.setUpdateUser(LoginUserUtils.getLoginUserId(request));
        dept.setId(id);
        if (deptMapper.updateByPrimaryKeySelective(dept) > 0) {
            //同时删除子节点
            Dept deleteDept = new Dept();
            deleteDept.setStatus(dept.getStatus());
            deleteDept.setUpdateUser(dept.getUpdateUser());
            deleteDept.setUpdateTime(dept.getUpdateTime());
            DeptDynamicSqlSupport.Dept d = DeptDynamicSqlSupport.dept;
            deptMapper.updateByExampleSelective(deleteDept)
                    .where(d.status, IsEqualTo.of(Status.DELETE::getCode))
                    .and(d.path, IsLike.of(() -> old.getPath() + "-" + id + "%"))
                    .build().execute();
            return Result.success();
        }
        return Result.error();
    }

    @Transactional
    public Result update(Dept dept, HttpServletRequest request) {
        if (dept.getId() == null)
            return Result.businessError("id不得为空!");
        if (null == dept.getName())
            return Result.paramError("部门名称不得为空");
        if (null == dept.getNo())
            return Result.paramError("部门编号不得为空");
        if (null == dept.getDepttype())
            return Result.paramError("部门类型不得为空");
        if (null == dept.getSuperiorId())
            return Result.paramError("上级部门不得为空");
        Dept old = deptMapper.selectByPrimaryKey(dept.getId());
        if (null == old)
            return Result.businessError("该部门不存在");
        if (!old.getSuperiorId().equals(dept.getSuperiorId()))
            return Result.businessError("禁止修改上级部门");
        dept.setUpdateTime(new Date());
        dept.setUpdateUser(LoginUserUtils.getLoginUserId(request));
        if (deptMapper.updateByPrimaryKeySelective(dept) > 0)
            return Result.success();
        return Result.error();
    }

    /**
     * @Description 某个节点的所有子节点
     */
    public Result selectLeafs(Long id) {
        if (Objects.isNull(id)) {
            return Result.businessError("id不得为空!");
        }
        DeptDynamicSqlSupport.Dept dept = DeptDynamicSqlSupport.dept;
        List<Dept> deptList = deptMapper.selectByExample()
                .where(dept.status, IsNotEqualTo.of(Status.DELETE::getCode))
                .and(dept.superiorId, IsEqualTo.of(() -> id))
                .build().execute();
        Map<Long, List<Dept>> deptMap = new HashMap<>();
        deptList.forEach(d -> deptMap.computeIfAbsent(d.getDepttype(), k -> new LinkedList<>()).add(d));
        return Result.success(deptMap);
    }
}
