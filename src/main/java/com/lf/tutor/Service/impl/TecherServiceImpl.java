package com.lf.tutor.Service.impl;

import com.github.pagehelper.PageHelper;
import com.lf.tutor.Service.CommonService;
import com.lf.tutor.Service.ImageService;
import com.lf.tutor.Service.TecherService;
import com.lf.tutor.domain.*;
import com.lf.tutor.mapper.TecherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Service("TecherService")
public class TecherServiceImpl implements TecherService {
    @Autowired
    private TecherMapper techerMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ImageService imageService;
    @Override
    public void insert(Techer techer) {
        techerMapper.insert(techer);
    }

    @Override
    public List<Techer> getTecherList(Techer techer,int currentPage,int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Techer> result = techerMapper.getTecherList(techer);
        for(int i = 0; i < result.size(); i++){
            result.get(i).setInstituteDesc(commonService.getInstituteById(result.get(i).getInstituteId()).getInstituteName());
            if(result.get(i).getGradeId() != null){
                //构建年级信息
                String[] gradeStrList = result.get(i).getGradeId().split(",");
                List<Grade> gradeList = new ArrayList<Grade>();
                for(int j = 0; j < gradeStrList.length; j++){
                    Grade grade = commonService.getGradeById(gradeStrList[j]);
                    gradeList.add(grade);
                }
                if(gradeList.size() == 0){
                    result.get(i).setGradeList(null);
                }else{
                    result.get(i).setGradeList(gradeList);
                }
            }
            if(result.get(i).getSubjectId() != null){
                //构建科目信息
                String[] subjectStrList = result.get(i).getSubjectId().split(",");
                List<Subject> subjectList = new ArrayList<Subject>();
                for(int j = 0; j < subjectStrList.length; j++){
                    Subject subject = commonService.getSubjectById(subjectStrList[j]);
                    subjectList.add(subject);
                }
                if(subjectList.size() == 0){
                    result.get(i).setSubjectList(null);
                }else{
                    result.get(i).setSubjectList(subjectList);
                }
            }
            if(result.get(i).getTimeTypeId() != null){
                //构建授课时间信息
                String[] timeTypeStrList = result.get(i).getTimeTypeId().split(",");
                List<TimeType> timeTypeList = new ArrayList<TimeType>();
                for(int j = 0; j < timeTypeStrList.length; j++){
                    TimeType timeType = commonService.getTimeTypeById(timeTypeStrList[j]);
                    timeTypeList.add(timeType);
                }
                if(timeTypeList.size() == 0){
                    result.get(i).setTimeTypeList(null);
                }else{
                    result.get(i).setTimeTypeList(timeTypeList);
                }
            }
            //设置头像请求路径
            result.get(i).setHeadImg("./getPhoto?imageType=1&uId="+result.get(i).getTecherId());
        }
        return result;
    }

    @Override
    public Techer getTecherById(String techerId) {
        Techer techer = techerMapper.getTecherById(techerId);
        if(techer == null){
            return null;
        }
        techer.setInstituteDesc(commonService.getInstituteById(techer.getInstituteId()).getInstituteName());
        if(techer.getGradeId() != null){
            //构建年级信息
            String[] gradeStrList = techer.getGradeId().split(",");
            List<Grade> gradeList = new ArrayList<Grade>();
            for(int j = 0; j < gradeStrList.length; j++){
                Grade grade = commonService.getGradeById(gradeStrList[j]);
                gradeList.add(grade);
            }
            if(gradeList.size() == 0){
                techer.setGradeList(null);
            }else{
                techer.setGradeList(gradeList);
            }
        }
        if(techer.getSubjectId() != null){
            //构建科目信息
            String[] subjectStrList = techer.getSubjectId().split(",");
            List<Subject> subjectList = new ArrayList<Subject>();
            for(int j = 0; j < subjectStrList.length; j++){
                Subject subject = commonService.getSubjectById(subjectStrList[j]);
                subjectList.add(subject);
            }
            if(subjectList.size() == 0){
                techer.setSubjectList(null);
            }else{
                techer.setSubjectList(subjectList);
            }
        }

        if(techer.getTimeTypeId() != null){
            //构建授课时间信息
            String[] timeTypeStrList = techer.getTimeTypeId().split(",");
            List<TimeType> timeTypeList = new ArrayList<TimeType>();
            for(int j = 0; j < timeTypeStrList.length; j++){
                TimeType timeType = commonService.getTimeTypeById(timeTypeStrList[j]);
                timeTypeList.add(timeType);
            }
            if(timeTypeList.size() == 0){
                techer.setTimeTypeList(null);
            }else{
                techer.setTimeTypeList(timeTypeList);
            }
        }
        //设置头像请求路径
        techer.setHeadImg("./getPhoto?imageType=1&uId="+techerId);
        //设置身份证请求路径
        techer.setIdImg("./getPhoto?imageType=2&uId="+techerId);
        //设置学生证请求路径
        techer.setsIdImg("./getPhoto?imageType=3&uId="+techerId);
        return techer;
    }

    @Override
    public void update(Techer techer) {
        techerMapper.update(techer);
    }

    @Override
    public List<Techer> getMatchTecherList(Techer techer) {
        return techerMapper.getMatchTecherList(techer);
    }

    @Override
    public List<Techer> getTop3TecherList() {
        return techerMapper.getTop3TecherList();
    }
}
