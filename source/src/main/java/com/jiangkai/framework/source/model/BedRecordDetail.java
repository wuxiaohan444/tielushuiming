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
public class BedRecordDetail extends BasePage implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer interfaceMainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer recordNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String state;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer heartbeat;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer breath;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String wet;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer weight;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String position;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer od;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updatetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String sleepstatus;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long dataSourceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long createUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long updateUser;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long bedRecordId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
}