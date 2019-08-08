package com.jiangkai.framework.admin.security.authentication;

import com.jiangkai.framework.admin.bean.LoginWebAuthenticationDetails;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2019/4/28 11:07
 * 自定义用户表单信息
 */
@Component
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new LoginWebAuthenticationDetails(context);
    }
}
