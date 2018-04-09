package com.lf.tutor.Service.impl;

import com.lf.tutor.Service.CommonService;
import com.lf.tutor.domain.*;
import com.lf.tutor.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Service("CommonService")
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonMapper commonMapper;
    @Override
    public List<Grade> getGradeList() {
        return commonMapper.getGradeList();
    }

    @Override
    public Grade getGradeById(String gradeId) {
        return commonMapper.getGradeById(gradeId);
    }

    @Override
    public List<Institute> getInstituteList() {
        return commonMapper.getInstituteList();
    }

    @Override
    public Institute getInstituteById(String instituteId) {
        return commonMapper.getInstituteById(instituteId);
    }

    @Override
    public List<Subject> getSubjectList() {
        return commonMapper.getSubjectList();
    }

    @Override
    public Subject getSubjectById(String subjectId) {
        return commonMapper.getSubjectById(subjectId);
    }

    @Override
    public List<TecherLevel> getTecherLevelList() {
        return commonMapper.getTecherLevelList();
    }

    @Override
    public TecherLevel getTecherLevelById(String techerLevelId) {
        return commonMapper.getTecherLevelById(techerLevelId);
    }

    @Override
    public List<TimeType> getTimeTypeList() {
        return commonMapper.getTimeTypeList();
    }

    @Override
    public TimeType getTimeTypeById(String timeTypeId) {
        return commonMapper.getTimeTypeById(timeTypeId);
    }
}
