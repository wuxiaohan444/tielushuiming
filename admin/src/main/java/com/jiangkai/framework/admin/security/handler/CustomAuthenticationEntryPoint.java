package com.jiangkai.framework.admin.security.handler;

import com.jiangkai.framework.admin.common.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2019/4/28 15:16
 * 未登录的返回结果
 */
@Component
public class CustomAuthenticationEntryPoint implements JsonResultHandler<Result>, AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        render(Result.notLogin(), response);
    }
}
