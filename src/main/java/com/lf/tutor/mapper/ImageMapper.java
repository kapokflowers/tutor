package com.lf.tutor.mapper;

import com.lf.tutor.domain.Image;
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
public interface ImageMapper {
    /**
     * 新增图片
     * @param image
     */
    @Insert("insert into image(user_id,image_type,image_content,is_valid,created_date,updated_date) " +
            "values (#{userId},#{imageType},#{imageContent},'Y',now(),now())")
    void insert(Image image);

    /**
     * 获取图片列表
     * @param image
     * @return
     */
    @Select("<script>" +
            "select * from image where is_valid = 'Y' " +
            "<if test=\"userId != null\">and user_id=#{userId} </if>" +
            "<if test=\"imageType != null\">and image_type=#{imageType} </if>" +
            "order by updated_date desc" +
            "</script>")
    List<Image> getImageList(Image image);

    /**
     * 根据用户ID和图片类型获取图片
     * @param image
     * @return
     */
    @Select("select * from image where user_id = #{userId} and image_type=#{imageType} and is_valid ='Y'")
    Image getImage(Image image);

    /**
     * 更新图片
     * @param image
     */
    @Update("update image set image_content = #{imageContent}, " +
            "is_valid= #{isValid} " +
            "where user_id = #{userId} and image_type=#{imageType} and is_valid = 'Y'")
    void update(Image image);
}
