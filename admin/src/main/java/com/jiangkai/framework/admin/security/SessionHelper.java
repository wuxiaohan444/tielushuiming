package com.jiangkai.framework.admin.security;

import com.jiangkai.framework.source.model.Method;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.function.Function;

/**
 * @author Administrator
 * @date 2019/4/28 10:48
 */
public abstract class SessionHelper {

    //所有需要得的权限链接集合url-id
    private static Map<String, Method> allUrlMap = new HashMap<>();

    public static void initUrlMap(List<Method> methodList, Function<String, String> function) {
        if (CollectionUtils.isEmpty(allUrlMap) && !CollectionUtils.isEmpty(methodList)) {
            methodList.forEach(method -> allUrlMap.put(function.apply(method.getUrl()), method));
        }
    }


    public static void addVerify(HttpSession session, String verifyCode) {
        add(session, Attribute.VERIFY_CODE_ATTRIBUTE, verifyCode);
    }

    public static void addVerifyCodeCreateTime(HttpSession session) {
        add(session, Attribute.VERIFY_CODE_CREATE_TIME_ATTRIBUTE, new Date().getTime());
    }

    public static Long getVerifyCodeCreateTime(HttpSession session) {
        return (Long) get(session, Attribute.VERIFY_CODE_CREATE_TIME_ATTRIBUTE);
    }

    public static String getVerify(HttpSession session) {
        return (String) get(session, Attribute.VERIFY_CODE_ATTRIBUTE);
    }


    public static Method getUrlId(String url) {
        return allUrlMap.get(url);
    }


    private static <V> void add(HttpSession session, Attribute attribute, V v) {
        session.setAttribute(attribute.name(), v);
    }

    private static Object get(HttpSession session, Attribute attribute) {
        return session.getAttribute(attribute.name());
    }


    private enum Attribute {
        VERIFY_CODE_ATTRIBUTE,
        VERIFY_CODE_CREATE_TIME_ATTRIBUTE
    }

}
