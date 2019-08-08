package com.jiangkai.framework.admin.security.authentication;

import com.jiangkai.framework.admin.bean.LoginUser;
import com.jiangkai.framework.admin.security.exception.PasswordAuthenticationException;
import com.jiangkai.framework.utils.tools.IStringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2019/4/28 10:58
 * 自定义的登陆认证器
 */
@Service
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    /*登陆逻辑*/
    private final UserDetailServiceImpl userDetailService;
    /*密码加盐*/
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

//        LoginWebAuthenticationDetails details = (LoginWebAuthenticationDetails) authentication.getDetails();//拿到表单的其他信息
//        String verifyCode;
//        if (IStringUtils.isEmpty(verifyCode = details.getVerifyCode()))
//            throw new VerifyCodeAuthenticationException("验证码不得为空");
//        if (IStringUtils.isEmpty(details.getSessionCode()))
//            throw new VerifyCodeAuthenticationException("请先获取验证码");
//        if (null == details.getVerifyCodeCreateTime() || System.currentTimeMillis() - details.getVerifyCodeCreateTime() > 60 * 1000)
//            throw new VerifyCodeAuthenticationException("验证码过期");
//        if (!details.getSessionCode().equals(verifyCode))
//            throw new VerifyCodeAuthenticationException("验证码不正确");
        // 这里构建来判断用户是否存在
        String userName = authentication.getName();// 这个获取表单输入中返回的用户名;
        String password;// 这个是表单中输入的密码；
        if (IStringUtils.isEmpty(password = ((String) authentication.getCredentials())))
            throw new PasswordAuthenticationException("密码不得为空");
        LoginUser user = (LoginUser) userDetailService.loadUserByUsername(userName); // 这里调用我们的自己写的获取用户的方法；
        //判断密码是否正确
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new PasswordAuthenticationException("密码不正确");
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 这里直接改成return true;表示是支持这个执行
        return true;
    }
}
