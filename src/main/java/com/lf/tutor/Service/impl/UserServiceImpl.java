package com.lf.tutor.Service.impl;

import com.lf.tutor.Service.UserService;
import com.lf.tutor.domain.User;
import com.lf.tutor.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }
}
