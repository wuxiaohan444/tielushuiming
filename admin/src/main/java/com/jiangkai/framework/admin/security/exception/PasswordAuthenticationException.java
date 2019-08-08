package com.jiangkai.framework.admin.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Administrator
 * @date 2019/4/28 14:23
 */
public class PasswordAuthenticationException extends AuthenticationException {
    public PasswordAuthenticationException(String msg) {
        super(msg);
    }
}
