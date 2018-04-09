package com.lf.tutor.Service.impl;

import com.github.pagehelper.PageHelper;
import com.lf.tutor.Service.StudentService;
import com.lf.tutor.domain.Student;
import com.lf.tutor.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public List<Student> getStudentList(Student student,int currentPage,int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        return studentMapper.getStudentList(student);
    }

    @Override
    public Student getStudentById(String studentId) {
        Student student = studentMapper.getStudentById(studentId);
        if(student != null){
            //设置身份证请求路径
            student.setIdImg("./getPhoto?imageType=2&uId="+student.getStudentId());
        }
        return student;
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }
}
