package com.lf.tutor.Service.impl;

import com.github.pagehelper.PageHelper;
import com.lf.tutor.Service.CommonService;
import com.lf.tutor.Service.EduOrderService;
import com.lf.tutor.Service.TecherOrderService;
import com.lf.tutor.Service.TecherService;
import com.lf.tutor.domain.EduOrder;
import com.lf.tutor.domain.Grade;
import com.lf.tutor.domain.Subject;
import com.lf.tutor.domain.TimeType;
import com.lf.tutor.mapper.EduOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Service("EduOrderService")
public class EduOrderServiceImpl implements EduOrderService {
    @Autowired
    private EduOrderMapper eduOrderMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private TecherOrderService techerOrderService;
    @Autowired
    private TecherService techerService;
    @Override
    public void insert(EduOrder eduOrder) {
        eduOrderMapper.insert(eduOrder);
    }

    @Override
    public List<EduOrder> getEduList(EduOrder eduOrder,int currentPage,int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<EduOrder> result = eduOrderMapper.getEduList(eduOrder);
        for(int i = 0; i < result.size(); i++){
            result.get(i).setGradeName(commonService.getGradeById(result.get(i).getGradeId()).getGradeDesc());
            if(result.get(i).getGradeId() != null){
                //构建订单的年级信息
                String[] gradeStrList = result.get(i).getGradeId().split(",");
                List<Grade> gradeList = new ArrayList<Grade>();
                for(int j = 0; j < gradeStrList.length; j++){
                    Grade grade = commonService.getGradeById(gradeStrList[j]);
                    gradeList.add(grade);
                }
                result.get(i).setGradeList(gradeList);
            }
            if(result.get(i).getSubjectId() != null){
                //构建订单的科目信息
                String[] subjectStrList = result.get(i).getSubjectId().split(",");
                List<Subject> subjectList = new ArrayList<Subject>();
                for(int j = 0; j < subjectStrList.length; j++){
                    Subject subject = commonService.getSubjectById(subjectStrList[j]);
                    subjectList.add(subject);
                }
                result.get(i).setSubjectList(subjectList);
            }
            if(result.get(i).getTimeTypeId() != null){
                //构建订单的辅导时间信息
                String[] timeTypeStrList = result.get(i).getTimeTypeId().split(",");
                List<TimeType> timeTypeList = new ArrayList<TimeType>();
                for(int j = 0; j < timeTypeStrList.length; j++){
                    TimeType timeType = commonService.getTimeTypeById(timeTypeStrList[j]);
                    timeTypeList.add(timeType);
                }
                result.get(i).setTimeTypeList(timeTypeList);
            }
            if(result.get(i).getTecherId() != null){
                //构建订单的教员姓名信息
                result.get(i).setTecherName(techerService.getTecherById(String.valueOf(result.get(i).getTecherId())).getTecherName());
            }
            //构建订单的已预约教员信息
            result.get(i).setFollowTecherList(techerOrderService.getTecherByOrderId(String.valueOf(result.get(i).getOrderId())));
        }
        return result;
    }

    @Override
    public EduOrder getEduById(String orderId) {
        EduOrder result = eduOrderMapper.getEduById(orderId);
        if(result == null){return null;}
        result.setGradeName(commonService.getGradeById(result.getGradeId()).getGradeDesc());
        if(result.getGradeId() != null){
            //构建订单的年级信息
            String[] gradeStrList = result.getGradeId().split(",");
            List<Grade> gradeList = new ArrayList<Grade>();
            for(int j = 0; j < gradeStrList.length; j++){
                Grade grade = commonService.getGradeById(gradeStrList[j]);
                gradeList.add(grade);
            }
            result.setGradeList(gradeList);
        }
        if(result.getSubjectId() != null){
            //构建订单的科目信息
            String[] subjectStrList = result.getSubjectId().split(",");
            List<Subject> subjectList = new ArrayList<Subject>();
            for(int j = 0; j < subjectStrList.length; j++){
                Subject subject = commonService.getSubjectById(subjectStrList[j]);
                subjectList.add(subject);
            }
            result.setSubjectList(subjectList);
        }
        if(result.getTimeTypeId() != null){
            //构建订单的辅导时间信息
            String[] timeTypeStrList = result.getTimeTypeId().split(",");
            List<TimeType> timeTypeList = new ArrayList<TimeType>();
            for(int j = 0; j < timeTypeStrList.length; j++){
                TimeType timeType = commonService.getTimeTypeById(timeTypeStrList[j]);
                timeTypeList.add(timeType);
            }
            result.setTimeTypeList(timeTypeList);
        }
        if(result.getTecherId() != null){
            //构建订单的教员姓名信息
            result.setTecherName(techerService.getTecherById(String.valueOf(result.getTecherId())).getTecherName());
        }
        //构建订单的已预约教员信息
        result.setFollowTecherList(techerOrderService.getTecherByOrderId(String.valueOf(result.getOrderId())));
        return result;
    }

    @Override
    public void update(EduOrder eduOrder) {
        eduOrderMapper.update(eduOrder);
    }

    @Override
    public Integer getCountOrder(String studentId, String month) {
        return eduOrderMapper.getCountOrder(studentId,month);
    }
}
