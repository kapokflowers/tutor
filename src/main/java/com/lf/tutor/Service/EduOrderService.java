package com.lf.tutor.Service;

import com.lf.tutor.domain.EduOrder;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public interface EduOrderService {
    //新增订单
    void insert(EduOrder eduOrder);
    //多条件获取订单
    List<EduOrder> getEduList(EduOrder eduOrder,int currentPage,int pageSize);
    //根据ID获取订单
    EduOrder getEduById(String orderId);
    //更新订单
    void update(EduOrder eduOrder);
    //获取某个月某个学生发布的订单个数
    Integer getCountOrder(String studentId,String month);
}
