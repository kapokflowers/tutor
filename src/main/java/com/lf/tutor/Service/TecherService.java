package com.lf.tutor.Service;

import com.lf.tutor.domain.Techer;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public interface TecherService {
    //新增教员
    void insert(Techer techer);
    //多条件查询教员
    List<Techer> getTecherList(Techer techer,int currentPage,int pageSize);
    //根据教员ID获取教员
    Techer getTecherById(String techerId);
    //更新教员
    void update(Techer techer);
    //获取匹配度最高的3个教员
    List<Techer> getMatchTecherList(Techer techer);
    //获取评分最高的3个教员
    List<Techer> getTop3TecherList();
}
