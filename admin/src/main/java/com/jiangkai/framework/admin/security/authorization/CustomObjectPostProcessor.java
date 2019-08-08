package com.jiangkai.framework.admin.security.authorization;


import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


/**
 * @author Administrator
 * @date 2019/4/29 14:23
 */

public class CustomObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {

    @Override
    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
        object.setSecurityMetadataSource(new CustomSecurityMetadataSource());
        object.setAccessDecisionManager(new CustomAccessDecisionManager());
        return object;
    }
}
