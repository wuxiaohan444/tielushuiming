package com.jiangkai.framework.admin.security.handler;

import com.jiangkai.framework.admin.common.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2019/4/28 14:37
 * 登陆失败返回的结果
 */
@Component
public class CustomAuthenticationFailHandler implements JsonResultHandler<Result>, AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        render(Result.businessError(exception.getMessage()), response);
    }
}
