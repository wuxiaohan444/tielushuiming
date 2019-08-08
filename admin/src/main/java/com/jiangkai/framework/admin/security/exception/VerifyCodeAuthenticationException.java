package com.jiangkai.framework.admin.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Administrator
 * @date 2019/4/28 11:11
 */
public class VerifyCodeAuthenticationException extends AuthenticationException {
    public VerifyCodeAuthenticationException(String msg) {
        super(msg);
    }
}
