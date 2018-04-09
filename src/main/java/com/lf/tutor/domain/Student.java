package com.lf.tutor.domain;

/*
 * @Description: 学生
 * @Athor:
 * @version: v1.0
 */
public class Student {
    private Integer studentId;
    private String studentName;
    private String sex;
    private String phone;
    private String gradeId;
    private String subjectId;
    private Integer studentAge;
    private String isValid;
    private String timeTypeId;
    private String address;
    private double score;
    private String createdDate;
    private String updatedDate;
    private String techerSex;
    private String isApprove;
    private String idImg;

    public String getIdImg() {
        return idImg;
    }

    public void setIdImg(String idImg) {
        this.idImg = idImg;
    }

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public String getTecherSex() {
        return techerSex;
    }

    public void setTecherSex(String techerSex) {
        this.techerSex = techerSex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimeTypeId() {
        return timeTypeId;
    }

    public void setTimeTypeId(String timeTypeId) {
        this.timeTypeId = timeTypeId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
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
