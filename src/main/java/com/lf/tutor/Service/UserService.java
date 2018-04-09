package com.lf.tutor.Service;

import com.lf.tutor.domain.User;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public interface UserService {
    //新增用户
    void insert(User user);
    //根据用户名获取用户
    User getUserByName(String userName);
    //更新用户
    void update(User user);
    //根据用户ID获取用户
    User getUserById(String userId);
}
