package com.yt.entity.mybatis;

import com.yt.util.yt.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table(name = "成功日志表")
public class Log implements Serializable {
    private Integer id;

    private String className;

    private Integer createUser;

    private Date createDate;

    private Integer isSuccess;

    private Integer spendTime;

    private String entityName;

    private String actions;

    private byte[] logInfo;

    public Log() {
    }

    public Log(String className, Integer createUser, Date createDate, Integer isSuccess, String entityName, String actions, byte[] logInfo, Integer spendTime) {
        this.className = className;
        this.createUser = createUser;
        this.createDate = createDate;
        this.isSuccess = isSuccess;
        this.entityName = entityName;
        this.actions = actions;
        this.logInfo = logInfo;
        this.spendTime = spendTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Integer spendTime) {
        this.spendTime = spendTime;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName == null ? null : entityName.trim();
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions == null ? null : actions.trim();
    }

    public byte[] getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(byte[] logInfo) {
        this.logInfo = logInfo;
    }
}