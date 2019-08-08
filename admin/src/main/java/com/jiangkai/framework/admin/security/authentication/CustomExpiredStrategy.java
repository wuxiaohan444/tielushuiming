package com.jiangkai.framework.admin.security.authentication;

import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.util.JSONUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: zvbb
 * Date: 2019/7/6
 * Description:
 */
@Component
public class CustomExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletRequest request = event.getRequest();
        HttpServletResponse response = event.getResponse();
        String requestURL = request.getRequestURL().toString();
        //cookie失效
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                Cookie newCookie = new Cookie(cookie.getName(), null);
                String cookiePath = request.getContextPath() + "/";
                newCookie.setPath(cookiePath);
                newCookie.setMaxAge(0);
                response.addCookie(newCookie);
            }
        }
        if (StringUtils.endsWithIgnoreCase(requestURL, ".html")) {
            new DefaultRedirectStrategy().sendRedirect(request, response, "/login.html");
        } else {
            Result result = Result.noPermission();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONUtils.toString(result));
        }
    }
}
