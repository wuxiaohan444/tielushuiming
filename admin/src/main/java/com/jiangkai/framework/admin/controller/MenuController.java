package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.MenuService;
import com.jiangkai.framework.source.model.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * User: zvbb
 * Date: 2019/6/20
 * Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;
    private final HttpServletRequest request;

    @GetMapping("/allNode")
    public Result allNode() {
        return menuService.allNode();
    }

    @PutMapping("/insert")
    public Result insert(Method method) {
        return menuService.insert(method, request);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return menuService.delete(id, request);
    }

    @PostMapping("/update")
    public Result update(Method method) {
        return menuService.update(method, request);
    }

    @GetMapping("/{id}")
    public Result select(@PathVariable Long id) {
        return menuService.select(id);
    }
}
