package com.lf.tutor.Service;

import com.lf.tutor.domain.StudentCollect;
import com.lf.tutor.domain.Techer;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public interface StudentCollectService {
    //新增学生收藏
    void insert(StudentCollect studentCollect);
    //根据学生ID获取收藏列表
    List<StudentCollect> getListBySId(String studentId,int currentPage,int pageSize);
    //根据学生ID和教员ID检查是否存在收藏
    StudentCollect checkExist(StudentCollect studentCollect);
    //更新学生收藏
    void update(StudentCollect studentCollect);
    //根据学生ID获取收藏中的教员
    List<Techer> getTecherList(String studentId,int currentPage,int pageSize);
}
