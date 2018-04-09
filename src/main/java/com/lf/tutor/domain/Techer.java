package com.lf.tutor.domain;

import java.util.List;

/*
 * @Description: 教员
 * @Athor:
 * @version: v1.0
 */
public class Techer {
    private Integer techerId;
    private String techerName;
    private String techerSex;
    private String techerSexStr;
    private String techerAge;
    private String techerLevelId;
    private String techerLevelDesc;
    private String isValid;
    private double score;
    private String instituteId;
    private String instituteDesc;
    private String eduLevelId;
    private String eduLevelDesc;
    private String eduThId;
    private String subjectId;
    private List<Subject> subjectList;
    private String gradeId;
    private List<Grade> gradeList;
    private String timeTypeId;
    private List<TimeType> timeTypeList;
    private String selfComment;
    private String createdDate;
    private String updatedDate;
    private String isApprove;
    private String phone;
    private String headImg;
    private String intro;
    private String exp;
    private String status;
    private String statusDesc;
    private String idImg;
    private String sIdImg;

    public String getIdImg() {
        return idImg;
    }

    public void setIdImg(String idImg) {
        this.idImg = idImg;
    }

    public String getsIdImg() {
        return sIdImg;
    }

    public void setsIdImg(String sIdImg) {
        this.sIdImg = sIdImg;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getTecherSexStr() {
        return techerSexStr;
    }

    public void setTecherSexStr(String techerSexStr) {
        this.techerSexStr = techerSexStr;
    }

    public String getTecherLevelDesc() {
        return techerLevelDesc;
    }

    public void setTecherLevelDesc(String techerLevelDesc) {
        this.techerLevelDesc = techerLevelDesc;
    }

    public String getInstituteDesc() {
        return instituteDesc;
    }

    public void setInstituteDesc(String instituteDesc) {
        this.instituteDesc = instituteDesc;
    }

    public String getEduLevelDesc() {
        return eduLevelDesc;
    }

    public void setEduLevelDesc(String eduLevelDesc) {
        this.eduLevelDesc = eduLevelDesc;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public List<TimeType> getTimeTypeList() {
        return timeTypeList;
    }

    public void setTimeTypeList(List<TimeType> timeTypeList) {
        this.timeTypeList = timeTypeList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public Integer getTecherId() {
        return techerId;
    }

    public void setTecherId(Integer techerId) {
        this.techerId = techerId;
    }

    public String getTecherName() {
        return techerName;
    }

    public void setTecherName(String techerName) {
        this.techerName = techerName;
    }

    public String getTecherSex() {
        return techerSex;
    }

    public void setTecherSex(String techerSex) {
        this.techerSex = techerSex;
        if(techerSex != null && techerSex.equals("1")){
            this.techerSexStr = "男";
        }else if(techerSex != null && techerSex.equals("2")){
            this.techerSexStr = "女";
        }else {
            this.techerSexStr = "未知";
        }
    }

    public String getTecherAge() {
        return techerAge;
    }

    public void setTecherAge(String techerAge) {
        this.techerAge = techerAge;
    }

    public String getTecherLevelId() {
        return techerLevelId;
    }

    public void setTecherLevelId(String techerLevelId) {
        this.techerLevelId = techerLevelId;
        if(techerLevelId != null && techerLevelId.equals("1")){
            this.techerLevelDesc = "金牌教员";
        }else if(techerLevelId != null && techerLevelId.equals("2")){
            this.techerLevelDesc = "银牌教员";
        }else if(techerLevelId != null && techerLevelId .equals("3")){
            this.techerLevelDesc = "铜牌教员";
        }
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    public String getEduLevelId() {
        return eduLevelId;
    }

    public void setEduLevelId(String eduLevelId) {
        this.eduLevelId = eduLevelId;
        if(eduLevelId != null && eduLevelId.equals("1")){
            this.eduLevelDesc = "博士后";
        }else if(eduLevelId != null && eduLevelId.equals("2")){
            this.eduLevelDesc = "博士";
        }else if(eduLevelId != null && eduLevelId.equals("3")){
            this.eduLevelDesc ="硕士";
        }else if(eduLevelId != null && eduLevelId.equals("4")){
            this.eduLevelDesc = "本科";
        }else if(eduLevelId != null && eduLevelId.equals("5")){
            this.eduLevelDesc = "高中";
        }else if(eduLevelId != null && eduLevelId.equals("6")){
            this.eduLevelDesc = "初中";
        }else if(eduLevelId != null && eduLevelId.equals("7")){
            this.eduLevelDesc = "小学";
        }
    }

    public String getEduThId() {
        return eduThId;
    }

    public void setEduThId(String eduThId) {
        this.eduThId = eduThId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
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

    public String getSelfComment() {
        return selfComment;
    }

    public void setSelfComment(String selfComment) {
        this.selfComment = selfComment;
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
