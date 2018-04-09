package com.lf.tutor.Service;

import com.lf.tutor.domain.EduOrder;
import com.lf.tutor.domain.Techer;
import com.lf.tutor.domain.TecherOrder;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public interface TecherOrderService {
    //新增教员订单
    void insert(TecherOrder techerCollect);
    //根据教员ID获取教员订单
    List<TecherOrder> getListBySId(String techerId,int currentPage,int pageSize);
    //根据订单ID获取教员信息
    List<Techer> getTecherByOrderId(String orderId);
    //更新教员订单
    void update(TecherOrder techerCollect);
    //多条件获取教员
    Techer getTecherOrderByCondition(TecherOrder techerOrder);
    //根据订单ID获取教员订单
    List<TecherOrder> getListByOrderId(String orderId);
    //根据学生ID获取收藏他发布订单的教员列表
    List<Techer> getTecherByStudentId(String studentId);
    //根据教员ID和订单ID获取教员订单信息
    TecherOrder getByTecherOrderId(String techerId,String orderId);
}
