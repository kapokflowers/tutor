package com.lf.tutor.mapper;

import com.lf.tutor.domain.Techer;
import com.lf.tutor.domain.TecherCollect;
import com.lf.tutor.domain.TecherOrder;
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
public interface TecherOrderMapper {
    /**
     * 新增教员订单
     * @param techerCollect
     */
    @Insert("insert into techer_order(techer_id,order_id,is_valid,created_date,updated_date,status,is_recommend) values (" +
            "#{techerId},#{orderId},'Y',now(),now(),'Z',#{isRecommend})")
    void insert(TecherOrder techerCollect);

    /**
     * 根据教员ID获取收藏的订单
     * @param techerId
     * @return
     */
    @Select("select * from techer_order where techer_id = #{techerId} and is_valid='Y'")
    List<TecherOrder> getListBySId(String techerId);

    /**
     * 根据教员ID和订单ID获取订单信息
     * @param techerId
     * @param orderId
     * @return
     */
    @Select("select * from techer_order where techer_id = #{arg0} and order_id = #{arg1} and is_valid='Y'")
    TecherOrder getListByTecherOrderId(String techerId,String orderId);

    /**
     * 根据订单ID获取教员订单列表
     * @param orderId
     * @return
     */
    @Select("select * from techer_order where order_id = #{orderId} and is_valid='Y'")
    List<TecherOrder> getListByOrderId(String orderId);

    /**
     * 根据订单ID获取收藏此订单的教员列表
     * @param orderId
     * @return
     */
    @Select("select t.*, t1.status status, t1.is_recommend isRecommend from techer_order t1, techer t where t.techer_id = t1.techer_id and t1.order_id = #{orderId} and t1.is_valid='Y'")
    List<Techer> getTecherByOrderId(String orderId);

    /**
     * 更新教员订单信息
     * @param techerCollect
     */
    @Update("update techer_order set is_valid = #{isValid},status=#{status},is_recommend=#{isRecommend} where techer_id=#{techerId} and order_id=#{orderId}")
    void update(TecherOrder techerCollect);

    /**
     * 根据教员ID和订单ID获取教员信息
     * @param techerOrder
     * @return
     */
    @Select("select * from techer_order where techer_id = #{techerId} and order_id = #{orderId} and is_valid='Y'")
    Techer getTecherOrderByCondition(TecherOrder techerOrder);

    /**
     * 根据学生ID获取收藏他发布订单的教员列表
     * @param studentId
     * @return
     */
    @Select("select t.*, t1.status status from techer_order t1, techer t, edu_order t2 " +
            "where t2.student_id = #{studentId} " +
            "and t2.order_id = t1.order_id " +
            "and t.techer_id = t1.techer_id "  +
            "and t1.is_valid='Y'")
    List<Techer> getTecherByStudentId(String studentId);
}
