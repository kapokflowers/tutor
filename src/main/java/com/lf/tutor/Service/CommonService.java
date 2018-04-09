package com.lf.tutor.Service;

import com.lf.tutor.domain.*;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public interface CommonService {
    //获取年级列表
    List<Grade> getGradeList();
    //根据年级ID获取年级
    Grade getGradeById(String gradeId);
    //获取学院列表
    List<Institute> getInstituteList();
    //根据学院ID获取学院信息
    Institute getInstituteById(String instituteId);
    //获取科目列表
    List<Subject> getSubjectList();
    //根据科目ID获取科目
    Subject getSubjectById(String subjectId);
    //获取教员等级列表
    List<TecherLevel> getTecherLevelList();
    //根据教员等级ID获取教员等级
    TecherLevel getTecherLevelById(String techerLevelId);
    //获取授课时间列表
    List<TimeType> getTimeTypeList();
    //根据授课时间ID获取授课时间
    TimeType getTimeTypeById(String timeTypeId);
}
