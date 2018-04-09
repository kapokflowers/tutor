package com.lf.tutor.mapper;

import com.lf.tutor.domain.EduOrder;
import com.lf.tutor.domain.StudentCollect;
import com.lf.tutor.domain.TecherCollect;
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
public interface TecherCollectMapper {
    /**
     * 新增教员收藏
     * @param techerCollect
     */
    @Insert("insert into techer_collect(techer_id,order_id,is_valid,created_date,updated_date) values (" +
            "#{techerId},#{orderId},'Y',now(),now())")
    void insert(TecherCollect techerCollect);

    /**
     * 根据教员ID获取收藏列表
     * @param techerId
     * @return
     */
    @Select("select * from techer_collect where techer_id = #{techerId} and is_valid='Y'")
    List<TecherCollect> getListBySId(String techerId);

    /**
     * 根据教员ID和订单ID判断是否存在收藏
     * @param techerCollect
     * @return
     */
    @Select("select * from techer_collect where techer_id = #{techerId} and order_id = #{orderId} and is_valid = 'Y'")
    TecherCollect checkExist(TecherCollect techerCollect);

    /**
     * 根据教员ID获取收藏的订单列表
     * @param techerId
     * @return
     */
    @Select("select eo.* from techer_collect tc, edu_order eo where tc.order_id = eo.order_id and tc.techer_id = #{techerId} and tc.is_valid = 'Y' and eo.is_valid='Y'")
    List<EduOrder> getOrderByTecherId(String techerId);

    /**
     * 更新教员收藏
     * @param techerCollect
     */
    @Update("update techer_collect set is_valid = #{isValid} where techer_id=#{techerId} and order_id=#{orderId}")
    void update(TecherCollect techerCollect);
}
