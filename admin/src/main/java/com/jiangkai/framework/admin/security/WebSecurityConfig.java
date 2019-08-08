package com.jiangkai.framework.admin.security;

import com.jiangkai.framework.admin.config.UploadFileProperties;
import com.jiangkai.framework.admin.security.authentication.CustomAuthenticationDetailsSource;
import com.jiangkai.framework.admin.security.authentication.CustomExpiredStrategy;
import com.jiangkai.framework.admin.security.authentication.UserDetailServiceImpl;
import com.jiangkai.framework.admin.security.authorization.CustomObjectPostProcessor;
import com.jiangkai.framework.admin.security.handler.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2019/4/26 17:27
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Component
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailHandler customAuthenticationFailHandler;
    private final CustomAuthenticationDetailsSource customAuthenticationDetailsSource;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final UserDetailServiceImpl userDetailService;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final UploadFileProperties uploadFileProperties;
    private final CustomExpiredStrategy customExpiredStrategy;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //TODO:无需登录的可访问的资源
                .antMatchers(
                        "/getVerifyCode",
                        "/reportUpload/*",
                        uploadFileProperties.getPrefix() + "*",
                        "/js/**",
                        "/css/**",
                        "/images/**",
                        "/fonts/**",
                        "/login.html"
                ).permitAll()
                .anyRequest()
                .authenticated()

                .and()
                //开启登录
                .formLogin()
//                .loginPage("/login")
                // 登录成功
                .successHandler(customAuthenticationSuccessHandler)
                // 登录失败
                .failureHandler(customAuthenticationFailHandler)
                //登陆自定义Form表单
                .authenticationDetailsSource(customAuthenticationDetailsSource)
                //不需要权限
                .permitAll()

                .and()
                //记住我
                .rememberMe()
                .tokenValiditySeconds(Integer.MAX_VALUE)
                .userDetailsService(userDetailService)

                .and()
                .httpBasic()
                //未登录
                .authenticationEntryPoint(customAuthenticationEntryPoint)

                .and()
                //退出
                .logout()
                //退出成功
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .clearAuthentication(true)
                .deleteCookies("remember-me")
                .deleteCookies("JSESSIONID")
                .permitAll()

                .and()
                .authorizeRequests()
                .anyRequest()
                // 其他 url 需要身份认证
                .authenticated()
                //权限拦截
                .withObjectPostProcessor(new CustomObjectPostProcessor())
                .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)// 无权访问 JSON 格式的数据
                .and().sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry())
                .expiredSessionStrategy(customExpiredStrategy);
    }
}
