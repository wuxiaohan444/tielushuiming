package com.jiangkai.framework.source.model;

import com.jiangkai.framework.source.base.BasePage;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Dept extends BasePage implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long superiorId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String path;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long depttype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String no;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

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
    private BigDecimal longitude;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal latitude;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer level;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
}