package com.lf.tutor.domain;

/*
 * @Description: 授课类型
 * @Athor:
 * @version: v1.0
 */
public class TimeType {
    private Integer timeTypeId;
    private String timeTypeDesc;
    private String isValid;
    private String createdDate;
    private String updatedDate;

    public Integer getTimeTypeId() {
        return timeTypeId;
    }

    public void setTimeTypeId(Integer timeTypeId) {
        this.timeTypeId = timeTypeId;
    }

    public String getTimeTypeDesc() {
        return timeTypeDesc;
    }

    public void setTimeTypeDesc(String timeTypeDesc) {
        this.timeTypeDesc = timeTypeDesc;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
