package com.jiangkai.framework.admin.security.handler;

import com.jiangkai.framework.admin.util.JSONUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author Administrator
 * @date 2019/4/28 15:21
 */
public interface JsonResultHandler<T> {
    default void render(T t, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter writer = response.getWriter();
        writer.write(JSONUtils.toString(t));
        writer.flush();
        writer.close();
    }
}
