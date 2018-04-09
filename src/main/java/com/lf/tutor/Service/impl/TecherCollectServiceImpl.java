package com.lf.tutor.Service.impl;

import com.github.pagehelper.PageHelper;
import com.lf.tutor.Service.CommonService;
import com.lf.tutor.Service.TecherCollectService;
import com.lf.tutor.domain.*;
import com.lf.tutor.mapper.TecherCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Service("TecherCollectService")
public class TecherCollectServiceImpl implements TecherCollectService {
    @Autowired
    private TecherCollectMapper techerCollectMapper;
    @Autowired
    private CommonService commonService;
    @Override
    public void insert(TecherCollect techerCollect) {
        techerCollectMapper.insert(techerCollect);
    }

    @Override
    public List<TecherCollect> getListBySId(String techerId,int currentPage,int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        return techerCollectMapper.getListBySId(techerId);
    }

    @Override
    public TecherCollect checkExist(TecherCollect techerCollect) {
        return techerCollectMapper.checkExist(techerCollect);
    }


    @Override
    public void update(TecherCollect techerCollect) {
        techerCollectMapper.update(techerCollect);
    }

    @Override
    public List<EduOrder> getOrderByTecherId(String techerId, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<EduOrder> result = techerCollectMapper.getOrderByTecherId(techerId);
        for(int i = 0; i < result.size(); i++){
            result.get(i).setGradeName(commonService.getGradeById(result.get(i).getGradeId()).getGradeDesc());
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
