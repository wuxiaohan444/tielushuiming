package com.jiangkai.framework.extdata.model;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class InfoLeave implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer mainid;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date onbeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date leavebeddate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date leavetime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getMainid() {
        return mainid;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMainid(Integer mainid) {
        this.mainid = mainid;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getOnbeddate() {
        return onbeddate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOnbeddate(Date onbeddate) {
        this.onbeddate = onbeddate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getLeavebeddate() {
        return leavebeddate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLeavebeddate(Date leavebeddate) {
        this.leavebeddate = leavebeddate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getLeavetime() {
        return leavetime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLeavetime(Date leavetime) {
        this.leavetime = leavetime;
    }
}