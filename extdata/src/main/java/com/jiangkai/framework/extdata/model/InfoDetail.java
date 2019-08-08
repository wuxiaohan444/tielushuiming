package com.jiangkai.framework.extdata.model;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

public class InfoDetail implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer recordNo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer interfaceMainid;

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
    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getRecordNo() {
        return recordNo;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRecordNo(Integer recordNo) {
        this.recordNo = recordNo;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getInterfaceMainid() {
        return interfaceMainid;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setInterfaceMainid(Integer interfaceMainid) {
        this.interfaceMainid = interfaceMainid;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getState() {
        return state;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setState(String state) {
        this.state = state;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getHeartbeat() {
        return heartbeat;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setHeartbeat(Integer heartbeat) {
        this.heartbeat = heartbeat;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getBreath() {
        return breath;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setBreath(Integer breath) {
        this.breath = breath;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getWet() {
        return wet;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setWet(String wet) {
        this.wet = wet;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getWeight() {
        return weight;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPosition() {
        return position;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPosition(String position) {
        this.position = position;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getOd() {
        return od;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOd(Integer od) {
        this.od = od;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getUpdatetime() {
        return updatetime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getSleepstatus() {
        return sleepstatus;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSleepstatus(String sleepstatus) {
        this.sleepstatus = sleepstatus;
    }
}