package com.jiangkai.framework.source.model;

import com.jiangkai.framework.source.base.BasePage;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BedInfo extends BasePage implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String manu;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String model;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String version;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String serialno;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String ip;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String restSite;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String restRoom;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String restBed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String restBuilding;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String restFloor;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long byBedid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long dataSourceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long createUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long updateUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long deptId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
}