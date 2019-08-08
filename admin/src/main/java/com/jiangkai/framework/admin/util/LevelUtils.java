package com.jiangkai.framework.admin.util;

import java.util.Objects;

/**
 * User: zvbb
 * Date: 2019/6/25
 * Description:
 */
public class LevelUtils {
    public static Integer avgSleepEffLevel(Double value) {
        if (Objects.nonNull(value)){
            if (value <= 0.1) {
                return 1;
            } else if (value <= 0.2) {
                return 2;
            } else if (value <= 0.3) {
                return 3;
            } else if (value <= 0.4) {
                return 4;
            } else if (value <= 0.5) {
                return 5;
            } else if (value <= 0.6) {
                return 6;
            } else if (value <= 0.7) {
                return 7;
            } else if (value <= 0.8) {
                return 8;
            } else if (value <= 0.9) {
                return 9;
            } else {
                return 10;
            }
        }else {
            return null;
        }
    }

    public static Integer avgTotalsleepLevel(Long value) {
        if (Objects.nonNull(value)){
            if (value <= 48) {
                return 1;
            } else if (value <= 96) {
                return 2;
            } else if (value <= 144) {
                return 3;
            } else if (value <= 192) {
                return 4;
            } else if (value <= 240) {
                return 5;
            } else if (value <= 288) {
                return 6;
            } else if (value <= 336) {
                return 7;
            } else if (value <= 384) {
                return 8;
            } else if (value <= 432) {
                return 9;
            } else {
                return 10;
            }
        }else {
            return null;
        }
    }


    public static Integer avgSleepTimeLevel(Long value) {
        if (Objects.nonNull(value)){
            if (value <= 6) {
                return 1;
            } else if (value <= 12) {
                return 2;
            } else if (value <= 18) {
                return 3;
            } else if (value <= 24) {
                return 4;
            } else if (value <= 30) {
                return 5;
            } else if (value <= 36) {
                return 6;
            } else if (value <= 42) {
                return 7;
            } else if (value <= 48) {
                return 8;
            } else if (value <= 54) {
                return 9;
            } else {
                return 10;
            }
        }else {
            return null;
        }
    }
}
