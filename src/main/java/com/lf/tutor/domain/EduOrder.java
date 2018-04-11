package com.lf.tutor.domain;

import java.util.List;

/*
 * @Description: 订单
 * @Athor:
 * @version: v1.0
 */
public class EduOrder {
    private Integer orderId;
    private Integer studentId;
    private String studentSex;
    private String studentSexDesc;
    private Integer techerId;
    private String techerSex;
    private String techerSexDesc;
    private String address;
    private String status;
    private String statusDesc;
    private double studentScore;
    private String studentComment;
    private double techerScore;
    private String techerComment;
    private String isValid;
    private String createdDate;
    private String updatedDate;
    private String gradeId;
    private String gradeName;
    private String timeTypeId;
    private String subjectId;
    private List<Grade> gradeList;
    private List<Subject> subjectList;
    private List<TimeType> timeTypeList;
    private List<Techer> followTecherList;
    private String techerName;
    private String studentName;
    private String isRecommend;
    private String wxOrderId;

    public String getWxOrderId() {
        return wxOrderId;
    }

    public void setWxOrderId(String wxOrderId) {
        this.wxOrderId = wxOrderId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getTecherName() {
        return techerName;
    }

    public void setTecherName(String techerName) {
        this.techerName = techerName;
    }

    public String getStudentSexDesc() {
        return studentSexDesc;
    }

    public void setStudentSexDesc(String studentSexDesc) {
        this.studentSexDesc = studentSexDesc;
    }

    public String getTecherSexDesc() {
        return techerSexDesc;
    }

    public void setTecherSexDesc(String techerSexDesc) {
        this.techerSexDesc = techerSexDesc;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public List<Techer> getFollowTecherList() {
        return followTecherList;
    }

    public void setFollowTecherList(List<Techer> followTecherList) {
        this.followTecherList = followTecherList;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<TimeType> getTimeTypeList() {
        return timeTypeList;
    }

    public void setTimeTypeList(List<TimeType> timeTypeList) {
        this.timeTypeList = timeTypeList;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getTimeTypeId() {
        return timeTypeId;
    }

    public void setTimeTypeId(String timeTypeId) {
        this.timeTypeId = timeTypeId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
        if(studentSex != null && "1".equals(studentSex)){
            studentSexDesc = "男";
        }else if(studentSex != null && "2".equals(studentSex)){
            studentSexDesc = "女";
        }else{
            studentSexDesc = "不限";
        }
    }

    public Integer getTecherId() {
        return techerId;
    }

    public void setTecherId(Integer techerId) {
        this.techerId = techerId;
    }

    public String getTecherSex() {
        return techerSex;
    }

    public void setTecherSex(String techerSex) {
        this.techerSex = techerSex;
        if(techerSex != null && "1".equals(techerSex)){
            this.techerSexDesc = "男";
        }else if(techerSex != null && "2".equals(techerSex)){
            this.techerSexDesc = "女";
        }else{
            this.techerSexDesc = "不限";
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(double studentScore) {
        this.studentScore = studentScore;
    }

    public String getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    public double getTecherScore() {
        return techerScore;
    }

    public void setTecherScore(double techerScore) {
        this.techerScore = techerScore;
    }

    public String getTecherComment() {
        return techerComment;
    }

    public void setTecherComment(String techerComment) {
        this.techerComment = techerComment;
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
