package com.lf.tutor.mapper;

import com.lf.tutor.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Mapper
public interface UserMapper {
    /**
     * 新增用户
     * @param user
     */
    @Insert("insert into user(user_name,pwd,role_id,is_valid,created_date,updated_date) " +
            "values(#{userName},#{pwd},#{roleId},'Y',now(),now())")
    void insert(User user);

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    @Select("select * from user where user_name=#{userName} and is_valid='Y'")
    User getUserByName(String userName);

    /**
     * 根据用户ID获取用户
     * @param userId
     * @return
     */
    @Select("select * from user where user_id=#{userId} and is_valid='Y'")
    User getUserById(String userId);

    /**
     * 更新用户信息
     * @param user
     */
    @Update("update user set user_name=#{userName}," +
            "pwd=#{pwd}," +
            "role_id=#{roleId}," +
            "is_valid=#{isValid}," +
            "updated_date=now()" +
            " where user_id = #{userId} and is_valid='Y'")
    void update(User user);
}
