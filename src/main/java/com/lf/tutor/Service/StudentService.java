package com.lf.tutor.Service;

import com.lf.tutor.domain.Student;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public interface StudentService {
    //新增学生
    void insert(Student student);
    //多条件查询学生
    List<Student> getStudentList(Student student,int currentPage,int pageSize);
    //根据ID获取学生信息
    Student getStudentById(String studentId);
    //更新学生信息
    void update(Student student);
}
