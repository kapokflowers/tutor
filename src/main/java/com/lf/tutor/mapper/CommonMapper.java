package com.lf.tutor.mapper;

import com.lf.tutor.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Mapper
public interface CommonMapper {
    /**
     * 获取年级列表
     * @return
     */
    @Select("select * from grade where is_valid = 'Y'")
    List<Grade> getGradeList();

    /**
     * 根据年级ID获取年级
     * @return
     */
    @Select("select * from grade where grade_id = #{gradeId} and is_valid = 'Y'")
    Grade getGradeById(String gradeId);

    /**
     * 获取学院列表
     * @return
     */
    @Select("select * from institute where is_valid = 'Y'")
    List<Institute> getInstituteList();

    /**
     * 根据学院ID获取学院信息
     * @return
     */
    @Select("select * from institute where institute_id = #{instituteId} and is_valid = 'Y'")
    Institute getInstituteById(String instituteId);

    /**
     * 获取科目列表
     * @return
     */
    @Select("select * from subject where is_valid = 'Y'")
    List<Subject> getSubjectList();

    /**
     * 根据科目ID获取科目
     * @return
     */
    @Select("select * from subject where subject_id = #{subjectId} and is_valid = 'Y'")
    Subject getSubjectById(String subjectId);

    /**
     * 获取教员等级列表
     * @return
     */
    @Select("select * from techer_level where is_valid = 'Y'")
    List<TecherLevel> getTecherLevelList();

    /**
     * 根据教员等级ID获取教员等级
     * @return
     */
    @Select("select * from techer_level where techer_level_id = #{techerLevelId} and is_valid = 'Y'")
    TecherLevel getTecherLevelById(String techerLevelId);

    /**
     * 获取授课时间列表
     * @return
     */
    @Select("select * from time_type where is_valid = 'Y'")
    List<TimeType> getTimeTypeList();

    /**
     * 根据授课时间ID获取授课时间
     * @return
     */
    @Select("select * from time_type where time_type_id = #{timeTypeId} and is_valid = 'Y'")
    TimeType getTimeTypeById(String timeTypeId);
}
