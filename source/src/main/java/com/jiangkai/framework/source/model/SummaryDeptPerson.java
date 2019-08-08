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
public class SummaryDeptPerson extends BasePage implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long deptid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date date;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long totaldriver;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long totalactivebed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long totalbed;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long totalusedriver;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long totalusetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal avgusetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal avgsleept;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal avgsleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long sleeptroublecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long autocallwakecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long artificalcallwakecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal ratioautocallwake;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long warncnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal avgqian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal ratioqian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal avgshen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal ratioshen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal avgsleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal ratiosleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long lowsleepeffcnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long middlesleepeffcnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long hightsleepeffcnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long lowsleeptimecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long middlesleeptimecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long hightsleeptimecnt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long level1sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long level2sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long level3sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long level4sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long level5sleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long sumtotaltime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long sumtotalsleept;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private BigDecimal sumsleepeff;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long sumqian;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long sumshen;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long sumsleeptime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer mainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String unfinishrecords;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer sumtype;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer haveedit;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;
}