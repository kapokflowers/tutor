package com.lf.tutor.mapper;

import com.lf.tutor.domain.EduOrder;
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
public interface EduOrderMapper {
    /**
     * 新增订单
     * @param eduOrder
     */
    @Insert("insert into edu_order (student_id, student_sex, techer_id, techer_sex," +
            "address,status,time_type_id,student_score,techer_score,student_comment,techer_comment,is_valid," +
            "created_date,updated_date,subject_id,grade_id,is_recommend) values (#{studentId},#{studentSex},#{techerId},#{techerSex},#{address},'Z'," +
            "#{timeTypeId},#{studentScore},#{techerScore},#{studentComment},#{techerComment},'Y',now(),now(),#{subjectId},#{gradeId},'N')")
    void insert(EduOrder eduOrder);

    /**
     * 多条件获取订单
     * @param eduOrder
     * @return
     */
    @Select("<script>" +
            "select * from edu_order where is_valid = 'Y' " +
            "<if test=\"status != null\">and status=#{status} </if>" +
            "<if test=\"isRecommend != null\">and is_recommend=#{isRecommend} </if>" +
            "<if test=\"studentSex != null\">and student_sex=#{studentSex} </if>" +
            "<if test=\"studentId != null\">and student_id=#{studentId} </if>" +
            "<if test=\"techerSex != null\">and techer_sex=#{techerSex} </if>" +
            "<if test=\"subjectId != null\">and find_in_set(#{subjectId},subject_id)  </if>" +
            "<if test=\"gradeId != null\">and find_in_set(#{gradeId},grade_id)  </if>" +
            "order by updated_date desc" +
            "</script>")
    List<EduOrder> getEduList(EduOrder eduOrder);

    /**
     * 根据ID获取订单
     * @param orderId
     * @return
     */
    @Select("select * from edu_order where order_id=#{orderId} and is_valid = 'Y'")
    EduOrder getEduById(String orderId);

    /**
     * 更新订单
     * @param eduOrder
     */
    @Update("update edu_order set student_id=#{studentId}," +
            "student_sex=#{studentSex}," +
            "techer_id=#{techerId}," +
            "techer_sex=#{techerSex}," +
            "address=#{address}," +
            "status=#{status}," +
            "time_type_id=#{timeTypeId}," +
            "grade_id=#{gradeId}," +
            "subject_id=#{subjectId}," +
            "student_score=#{studentScore}," +
            "techer_score=#{techerScore}," +
            "student_comment=#{studentComment}," +
            "techer_comment=#{techerComment}," +
            "is_valid=#{isValid}," +
            "is_recommend=#{isRecommend}," +
            "wx_order_id=#{wxOrderId}," +
            "updated_date=now()" +
            " where order_id = #{orderId} and is_valid = 'Y'")
    void update(EduOrder eduOrder);

    /**
     * 获取某个月某个学生发布的订单个数
     * @param studentId
     * @param month
     * @return
     */
    @Select("select count(*) from edu_order where is_valid = 'Y' and student_id=#{arg0} " +
            "and created_date < date_add(str_to_date(#{arg1},'%Y-%m-%d'),interval 1 month) " +
            "and created_date >= str_to_date(#{arg1},'%Y-%m-%d')")
    Integer getCountOrder(String studentId,String month);
}
