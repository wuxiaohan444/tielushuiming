package com.jiangkai.framework.admin.security.handler;

import com.jiangkai.framework.admin.common.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2019/4/28 15:19
 * 无权限访问
 */
@Component
public class CustomAccessDeniedHandler implements JsonResultHandler<Result>, AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        render(Result.noPermission(), response);
    }
}
