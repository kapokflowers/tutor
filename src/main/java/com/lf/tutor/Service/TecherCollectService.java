package com.lf.tutor.Service;

import com.lf.tutor.domain.EduOrder;
import com.lf.tutor.domain.TecherCollect;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public interface TecherCollectService {
    //新增教员收藏
    void insert(TecherCollect techerCollect);
    //根据教员ID获取收藏列表
    List<TecherCollect> getListBySId(String techerId,int currentPage,int pageSize);
    //根据教员ID和订单ID判断是否存在收藏
    TecherCollect checkExist(TecherCollect techerCollect);
    //根据教员ID获取收藏的订单列表
    void update(TecherCollect techerCollect);
    //更新教员收藏
    List<EduOrder> getOrderByTecherId(String techerId, int currentPage, int pageSize);
}
