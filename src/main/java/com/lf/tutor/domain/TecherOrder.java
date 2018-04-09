package com.lf.tutor.domain;

/*
 * @Description: 教员订单
 * @Athor:
 * @version: v1.0
 */
public class TecherOrder {
    private Integer techerId;
    private Integer orderId;
    private String status;
    private String statusDesc;
    private String isValid;
    private String createdDate;
    private String updatedDate;
    private EduOrder eduOrder;

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public EduOrder getEduOrder() {
        return eduOrder;
    }

    public void setEduOrder(EduOrder eduOrder) {
        this.eduOrder = eduOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        if(status != null && "Y".equals(status)){
            this.statusDesc = "接受";
        }else if(status != null && "N".equals(status)){
            this.statusDesc = "拒绝";
        }else if(status != null && "S".equals(status)){
            this.statusDesc = "试课";
        }else if(status != null && "Z".equals(status)){
            this.statusDesc = "待接";
        }else if(status != null && "C".equals(status)){
            this.statusDesc = "完成";
        }
    }

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
