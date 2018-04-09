package com.lf.tutor.domain;

/*
 * @Description: 学生收藏
 * @Athor:
 * @version: v1.0
 */
public class StudentCollect {
    private Integer studentId;
    private Integer techerId;
    private String isValid;
    private String createdDate;
    private String updatedDate;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTecherId() {
        return techerId;
    }

    public void setTecherId(Integer techerId) {
        this.techerId = techerId;
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
