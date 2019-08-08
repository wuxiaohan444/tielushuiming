package com.jiangkai.framework.admin.security.handler;

import com.jiangkai.framework.admin.common.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2019/4/28 15:35
 */
@Component
public class CustomLogoutSuccessHandler implements JsonResultHandler<Result>, LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        render(Result.success(), response);
    }
}
