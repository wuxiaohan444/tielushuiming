package com.jiangkai.framework.admin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User: zvbb
 * Date: 2019/6/22
 * Description:
 */
public class ListUtils {
    /**
     * @Description 返回连个List的交集(只支持 基本数据类型和基本类型的包装类)
     */
    public static <K> List interSection(List<K> list1, List<K> list2) {
        if (Objects.nonNull(list1) && Objects.nonNull(list2)) {
            List<K> result = new ArrayList<>();
            for (int i = 0; i < list1.size(); i++) {
                K row1 = list1.get(i);
                for (int j = 0; j < list2.size(); j++) {
                    K row2 = list2.get(j);
                    if (row1 == row2) {
                        result.add(row1);
                    }
                }
            }
            return result;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Long aLong = Long.valueOf(1L);
        Long aLong1 = Long.valueOf(1L);
        System.out.println(aLong == aLong1);
    }
}
