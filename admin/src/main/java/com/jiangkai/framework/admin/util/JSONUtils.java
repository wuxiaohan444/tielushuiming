package com.jiangkai.framework.admin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangkai.framework.utils.tools.IStringUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * JSON工具类
 */
public abstract class JSONUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * JSON转Object
     */
    public static <T> T toObject(String json, Class<T> c) {
        try {
            return objectMapper.readValue(json, c);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Object转JSON
     */
    public static String toString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 对象转字节
     */
    public static byte[] toByte(Object o) {
        try {
            return objectMapper.writeValueAsBytes(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }


    /**
     * 字节转对象
     */
    public static <T> T toObject(byte[] data, Class<T> clazz) {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JSON转List
     */
    public static <T> List<T> toList(String json, Class<T> c) {
        if (IStringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(List.class, c));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JSON转Iterator
     */
    public static <T> Iterator<T> toIterator(String json, Class<? extends Iterator> c) {
        if (IStringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(Iterator.class, c));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
