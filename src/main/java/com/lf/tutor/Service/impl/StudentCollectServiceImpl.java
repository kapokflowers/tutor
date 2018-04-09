package com.lf.tutor.Service.impl;

import com.github.pagehelper.PageHelper;
import com.lf.tutor.Service.CommonService;
import com.lf.tutor.Service.StudentCollectService;
import com.lf.tutor.domain.*;
import com.lf.tutor.mapper.StudentCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Service("StudentCollectService")
public class StudentCollectServiceImpl implements StudentCollectService {
    @Autowired
    private StudentCollectMapper studentCollectMapper;
    @Autowired
    private CommonService commonService;
    @Override
    public void insert(StudentCollect studentCollect) {
        studentCollectMapper.insert(studentCollect);
    }

    @Override
    public List<StudentCollect> getListBySId(String studentId,int currentPage,int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        return studentCollectMapper.getListBySId(studentId);
    }

    @Override
    public StudentCollect checkExist(StudentCollect studentCollect) {
        return studentCollectMapper.checkExist(studentCollect);
    }

    @Override
    public void update(StudentCollect studentCollect) {
        studentCollectMapper.update(studentCollect);
    }

    @Override
    public List<Techer> getTecherList(String studentId,int currentPage,int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Techer> result = studentCollectMapper.getTecherList(studentId);
        for(int i = 0; i < result.size(); i++){
            //构建学院信息
            result.get(i).setInstituteDesc(commonService.getInstituteById(result.get(i).getInstituteId()).getInstituteName());
            if(result.get(i).getGradeId() != null){
                //构建年级信息
                String[] gradeStrList = result.get(i).getGradeId().split(",");
                List<Grade> gradeList = new ArrayList<Grade>();
                for(int j = 0; j < gradeStrList.length; j++){
                    Grade grade = commonService.getGradeById(gradeStrList[j]);
                    gradeList.add(grade);
                }
                result.get(i).setGradeList(gradeList);
            }
            if(result.get(i).getSubjectId() != null){
                //构建科目信息
                String[] subjectStrList = result.get(i).getSubjectId().split(",");
                List<Subject> subjectList = new ArrayList<Subject>();
                for(int j = 0; j < subjectStrList.length; j++){
                    Subject subject = commonService.getSubjectById(subjectStrList[j]);
                    subjectList.add(subject);
                }
                result.get(i).setSubjectList(subjectList);
            }
            if(result.get(i).getTimeTypeId() != null){
                //构建授课时间信息
                String[] timeTypeStrList = result.get(i).getTimeTypeId().split(",");
                List<TimeType> timeTypeList = new ArrayList<TimeType>();
                for(int j = 0; j < timeTypeStrList.length; j++){
                    TimeType timeType = commonService.getTimeTypeById(timeTypeStrList[j]);
                    timeTypeList.add(timeType);
                }
                result.get(i).setTimeTypeList(timeTypeList);
            }
        }
        return result;
    }
}
