package com.jiangkai.framework.admin.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zvbb
 * Date: 2019/6/18
 * Description:
 */
@Data
public class RealBedInfoRes {
    //房间号
    private String restRoom="";
    //房间中的所有床铺状态
    private List<BedStatusNode> beds = new ArrayList<>();

    @Data
    public static class BedStatusNode {
        //床铺序列号
        private String serialNo;
        //床铺现在被哪个司机使用
        private String sleepDriverName;
        //司机睡眠状态
        private String status;
        //司机编号
        private String sleepDriverNo;
        //InfoInteface表的主键
        private Integer mainID;
    }
}
