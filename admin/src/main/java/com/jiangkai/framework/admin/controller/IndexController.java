package com.jiangkai.framework.admin.controller;

import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.service.IndexService;
import com.jiangkai.framework.admin.util.LoginUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2019/4/26 17:14
 */
@Controller
@RequestMapping
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;

    @GetMapping(value = "/getVerifyCode")
    @ResponseBody
    public Result getVerifyCode(HttpServletRequest request) {
        return indexService.getVerifyCode(request);
    }

    @GetMapping(value = "/getMe")
    @ResponseBody
    public Result getMe(HttpServletRequest request) {
        return Result.success(LoginUserUtils.getLoginUserId(request));
    }

    @GetMapping(value = "/")
    public String index(HttpServletRequest request) {
        return null == request.getUserPrincipal() ? "login" : "index";
    }

    @GetMapping(value = "/{web}.html")
    public String html(@PathVariable String web, HttpServletRequest request) {
        return request.getUserPrincipal() == null ? "login" : web;
    }


    @GetMapping(value = "/{include}.include")
    public String include(@PathVariable String include) {
        return include + "::base";
    }
}

