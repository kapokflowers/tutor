package com.lf.tutor.mapper;

import com.lf.tutor.domain.StudentCollect;
import com.lf.tutor.domain.Techer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Mapper
public interface StudentCollectMapper {
    /**
     * 新增学生收藏
     * @param studentCollect
     */
    @Insert("insert into student_collect(student_id,techer_id,is_valid,created_date,updated_date) values (" +
            "#{studentId},#{techerId},'Y',now(),now())")
    void insert(StudentCollect studentCollect);

    /**
     * 根据学生ID获取收藏列表
     * @param studentId
     * @return
     */
    @Select("select * from student_collect where student_id = #{studentId} and is_valid='Y'")
    List<StudentCollect> getListBySId(String studentId);

    /**
     * 根据学生ID和教员ID检查是否存在收藏
     * @param studentCollect
     * @return
     */
    @Select("select * from student_collect where student_id = #{studentId} and techer_id = #{techerId} and is_valid ='Y'")
    StudentCollect checkExist(StudentCollect studentCollect);

    /**
     * 更新学生收藏
     * @param studentCollect
     */
    @Update("update student_collect set is_valid = #{isValid} where student_id=#{studentId} and techer_id=#{techerId}")
    void update(StudentCollect studentCollect);

    /**
     * 根据学生ID获取收藏中的教员
     * @param studentId
     * @return
     */
    @Select("select t.* from student_collect sc, techer t " +
            "where t.techer_id = sc.techer_id and sc.student_id=#{studentId} and sc.is_valid ='Y' and t.is_valid='Y'")
    List<Techer> getTecherList(String studentId);
}
