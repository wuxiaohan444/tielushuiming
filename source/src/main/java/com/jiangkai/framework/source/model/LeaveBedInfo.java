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
public class LeaveBedInfo extends BasePage implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long key;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer mainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date onbeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date leavebeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date leavetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long dataSourceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long createUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long updateUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long bedInfoId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String file;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer fileType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long deptId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
}