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
        return techerOrderMapper.getTecherByOrderId(orderId);
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
