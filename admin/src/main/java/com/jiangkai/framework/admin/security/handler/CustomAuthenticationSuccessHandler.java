package com.jiangkai.framework.admin.security.handler;

import com.jiangkai.framework.admin.common.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2019/4/28 14:33
 * 登陆成功返回的结果
 */
@Component
public class CustomAuthenticationSuccessHandler implements JsonResultHandler<Result>, AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        render(Result.success(), response);
    }
}
