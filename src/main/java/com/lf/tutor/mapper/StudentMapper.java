package com.lf.tutor.mapper;

import com.lf.tutor.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Mapper
public interface StudentMapper {
    /**
     * 新增学生
     * @param student
     */
    @Insert("insert into student (student_id,student_name,sex,grade_id,subject_id,student_age" +
            ",is_valid,score,time_type_id,created_date,updated_date,address,phone,techer_sex,is_approve) values(#{studentId},#{studentName},#{sex},#{gradeId},#{subjectId}," +
            "#{studentAge},'Y',#{score},#{timeTypeId},now(),now(),#{address},#{phone},#{techerSex},'N')")
    void insert(Student student);

    /**
     * 多条件查询学生
     * @param student
     * @return
     */
    @Select("<script>" +
            "select * from student where is_valid = 'Y' " +
            "<if test=\"sex != null\">and sex=#{sex} </if>" +
            "<if test=\"isApprove != null\">and is_approve=#{isApprove} </if>" +
            "<if test=\"gradeId != null\">and find_in_set(#{gradeId},grade_id} </if>" +
            "<if test=\"subjectId != null\">and find_in_set(#{subjectId},subject_id)  </if>" +
            "<if test=\"timeTypeId != null\">and find_in_set(#{timeTypeId},time_type_id)  </if>" +
            "order by updated_date desc" +
            "</script>")
    List<Student> getStudentList(Student student);

    /**
     * 根据ID获取学生信息
     * @param studentId
     * @return
     */
    @Select("select * from student where student_id = #{studentId} and is_valid = 'Y'")
    Student getStudentById(String studentId);

    /**
     * 更新学生信息
     * @param student
     */
    @Update("update student set student_name=#{studentName}," +
            "sex=#{sex}," +
            "grade_id=#{gradeId}," +
            "subject_id=#{subjectId}," +
            "student_age=#{studentAge}," +
            "is_valid=#{isValid}," +
            "score=#{score}," +
            "time_type_id=#{timeTypeId}," +
            "address=#{address}," +
            "phone=#{phone}," +
            "is_approve=#{isApprove}," +
            "techer_sex=#{techerSex}," +
            "updated_date=now() " +
            "where student_id = #{studentId} and is_valid='Y'")
    void update(Student student);
}
