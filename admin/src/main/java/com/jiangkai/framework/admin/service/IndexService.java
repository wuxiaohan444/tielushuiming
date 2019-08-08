package com.jiangkai.framework.admin.service;

import com.jiangkai.framework.admin.common.Result;
import com.jiangkai.framework.admin.security.SessionHelper;
import com.jiangkai.framework.admin.security.VerifyCodeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @date 2019/4/26 17:15
 */
@Component
@RequiredArgsConstructor
public class IndexService {

    public Result getVerifyCode(HttpServletRequest request) {
        String verifyCode = VerifyCodeHelper.defaultCode();
        HttpSession session = request.getSession();
        SessionHelper.addVerify(session, verifyCode);
        SessionHelper.addVerifyCodeCreateTime(session);
        return Result.success(verifyCode);
    }
}
