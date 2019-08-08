package com.jiangkai.framework.admin.util;

import com.jiangkai.framework.admin.bean.LoginUser;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2019/6/20 10:56
 */
public abstract class LoginUserUtils {

    public static LoginUser getLoginUser(HttpServletRequest request) {
        Authentication  authentication= (Authentication) request.getUserPrincipal();
        return (LoginUser) authentication.getPrincipal();
    }

    public static Long getLoginUserId(HttpServletRequest request){
        return getLoginUser(request).getId();
    }
}
