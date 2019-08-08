package com.jiangkai.framework.admin.security;

/**
 * @author Administrator
 * @date 2019/4/1 9:18
 */
public abstract class VerifyCodeHelper {

    private static final char[] codeTable = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static String getCode(int length) {
        StringBuffer sb = new StringBuffer();
        for (var i = 0; i < length; i++) {
            sb.append(codeTable[(int) ((double) codeTable.length * Math.random())]);
        }
        return sb.toString();
    }

    public static String defaultCode() {
        return getCode(4);
    }

}
