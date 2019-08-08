package com.jiangkai.framework.admin.util;

import org.apache.tomcat.jni.BIOCallback;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Description:两个数值相加，计算方式用BigDecimal
 */
public class NumberUtils {
    public static <K extends Number, T extends Number> BigDecimal bigDecimalAdd(K x, T y) throws Exception {
        BigDecimal bigX = BigDecimal.ZERO;
        BigDecimal bigY = BigDecimal.ZERO;

        if (Objects.nonNull(x)) {
            bigX = number2BigDecimal(x);
        }

        if (Objects.nonNull(y)) {
            bigY = number2BigDecimal(y);
        }
        return bigX.add(bigY);
    }

    /**
     * @param scale        保留几位小数
     * @param roundingMode 舍入方式
     * @return
     * @Description 两个数值相除，计算方式用BigDecimal
     * @date 2019/6/25
     * @auther zvbb
     */
    public static <K extends Number,T extends Number> BigDecimal bigDecimalDivide(K x, T y, int scale, RoundingMode roundingMode) throws Exception {
        BigDecimal bigX = BigDecimal.ZERO;
        BigDecimal bigY = BigDecimal.ZERO;
        if (Objects.nonNull(x)) {
            bigX = number2BigDecimal(x);
        }

        if (Objects.nonNull(y)){
            bigY = number2BigDecimal(y);
        }

        if (bigY.intValue() == 0){
            throw new ArithmeticException("除数不能为0");
        }
        return bigX.divide(bigY,scale,roundingMode);
    }

    public static <K extends Number> BigDecimal null2BigDecialZero(K obj) throws Exception {
        if (Objects.isNull(obj)) {
            return BigDecimal.ZERO;
        }
        if (obj instanceof Long) {
            return BigDecimal.valueOf((Long) obj);
        }
        if (obj instanceof Integer) {
            return BigDecimal.valueOf((Integer) obj);
        }
        if (obj instanceof Double) {
            return BigDecimal.valueOf((Double) obj);
        }
        if (obj instanceof Float) {
            return BigDecimal.valueOf((Float) obj);
        }
        throw new Exception("数据类型异常!");
    }

    private static <K extends Number> BigDecimal number2BigDecimal(K x) {
        BigDecimal big = BigDecimal.ZERO;
        if ((x instanceof Long)) {
            big = BigDecimal.valueOf((Long) x);
        } else if ((x instanceof Double)) {
            big = BigDecimal.valueOf((Double) x);
        } else if ((x instanceof Integer)) {
            big = BigDecimal.valueOf((Integer) x);
        } else if ((x instanceof Float)) {
            big = BigDecimal.valueOf((Float) x);
        } else if ((x instanceof BigDecimal)){
            big = (BigDecimal) x;
        }
        return big;
    }


    public static void main(String[] args) {
        Long a = null;
        BigDecimal bigDecimal = BigDecimal.valueOf(a);
        System.out.println(bigDecimal);
    }

}
