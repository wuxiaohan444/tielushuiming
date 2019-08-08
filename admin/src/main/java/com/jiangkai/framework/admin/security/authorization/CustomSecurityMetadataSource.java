package com.jiangkai.framework.admin.security.authorization;

import com.jiangkai.framework.admin.security.SessionHelper;
import com.jiangkai.framework.source.model.Method;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;
import java.util.List;

/**
 * @author Administrator
 * @date 2019/4/29 13:38
 */

public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //如果系统设置该路经需要拦截，则拦截，如果没设置，则不拦截
        Method method;
        if (null != (method = SessionHelper.getUrlId(((FilterInvocation) object).getRequestUrl())))
            return SecurityConfig.createList(method.getUrl());
        return List.of();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
