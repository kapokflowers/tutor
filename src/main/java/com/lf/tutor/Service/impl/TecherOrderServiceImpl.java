package com.lf.tutor.Service.impl;

import com.github.pagehelper.PageException;
import com.github.pagehelper.PageHelper;
import com.lf.tutor.Service.CommonService;
import com.lf.tutor.Service.EduOrderService;
import com.lf.tutor.Service.TecherOrderService;
import com.lf.tutor.domain.*;
import com.lf.tutor.mapper.TecherOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Service("TecherOrderService")
public class TecherOrderServiceImpl implements TecherOrderService {
    @Autowired
    private TecherOrderMapper techerOrderMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private EduOrderService eduOrderService;
    @Override
    public void insert(TecherOrder techerCollect) {
        techerOrderMapper.insert(techerCollect);
    }

    @Override
    public List<TecherOrder> getListBySId(String techerId,int currentPage,int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<TecherOrder> techerOrderList = techerOrderMapper.getListBySId(techerId);
        for(int i = 0; i < techerOrderList.size(); i++){
            techerOrderList.get(i).setEduOrder(eduOrderService.getEduById(String.valueOf(techerOrderList.get(i).getOrderId())));
        }
        return techerOrderList;
    }

    @Override
    public List<Techer> getTecherByOrderId(String orderId) {
        List<Techer> result = techerOrderMapper.getTecherByOrderId(orderId);
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
    public void update(TecherOrder techerCollect) {
        techerOrderMapper.update(techerCollect);
    }

    @Override
    public Techer getTecherOrderByCondition(TecherOrder techerOrder) {
        return techerOrderMapper.getTecherOrderByCondition(techerOrder);
    }

    @Override
    public List<TecherOrder> getListByOrderId(String orderId) {
        return techerOrderMapper.getListByOrderId(orderId);
    }

    @Override
    public List<Techer> getTecherByStudentId(String studentId) {
        List<Techer> techerList = techerOrderMapper.getTecherByStudentId(studentId);
        for(int i = 0; i < techerList.size(); i++){
            techerList.get(i).setInstituteDesc(commonService.getInstituteById(techerList.get(i).getInstituteId()).getInstituteName());
        }

        return techerList;
    }

    @Override
    public TecherOrder getByTecherOrderId(String techerId, String orderId) {
        return techerOrderMapper.getListByTecherOrderId(techerId,orderId);
    }
}
