package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.bean.RoleBean;
import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * User: zvbb
 * Date: 2019/6/21
 * Description:
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final HttpServletRequest request;

    @GetMapping("/page")
    public Result page(RoleBean roleBean) {
        return roleService.page(roleBean);
    }

    @PutMapping("/insert")
    public Result insert(RoleBean roleBean) {
        return roleService.insert(roleBean, request);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return roleService.delete(id, request);
    }

    @PostMapping("/update")
    public Result update(RoleBean roleBean) {
        return roleService.update(roleBean, request);
    }

    @GetMapping("/{id}")
    public Result select(@PathVariable Long id) {
        return roleService.select(id);
    }
}
