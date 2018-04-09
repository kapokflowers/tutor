package com.lf.tutor.domain;

/*
 * @Description: 教员收藏
 * @Athor:
 * @version: v1.0
 */
public class TecherCollect {
    private Integer techerId;
    private Integer orderId;
    private String isValid;
    private String createdDate;
    private String updatedDate;

    public Integer getTecherId() {
        return techerId;
    }

    public void setTecherId(Integer techerId) {
        this.techerId = techerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
