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
public class BedRecord extends BasePage implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer mainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String bedSerialno;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String driverWorkno;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date checkindate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date checkoutdate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date wakeupdate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer yunanSid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String driverName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer finish;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer avgbr;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer avghb;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Double sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date firstonbeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date sleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date lastleavebeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer sleepscore;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer leavecount;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer autocallwake;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date leavealltime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer maxhb;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer minbr;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer totalsleept;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer totaltime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer qian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer shen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String cloudguid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer warn;

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
    private Integer maxbr;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer minhb;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long driverId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String reportguid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer isupload;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long deptId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
}