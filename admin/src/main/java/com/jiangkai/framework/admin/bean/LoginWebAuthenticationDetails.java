package com.jiangkai.framework.admin.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2019/4/28 11:02
 * 自定义登陆表单
 */
@Getter
@Setter
public class LoginWebAuthenticationDetails extends WebAuthenticationDetails {
    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public LoginWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
//        HttpSession session = request.getSession();
//        this.verifyCode = request.getParameter("code");
//        this.sessionCode = SessionHelper.getVerify(session);
//        this.verifyCodeCreateTime = SessionHelper.getVerifyCodeCreateTime(session);
    }
//    /*验证码(前端传的)*/
//    private String verifyCode;
//    /*验证码(放入Session的验证码)*/
//    private String sessionCode;
//    /*验证码生成的时间*/
//    private Long verifyCodeCreateTime;
}
