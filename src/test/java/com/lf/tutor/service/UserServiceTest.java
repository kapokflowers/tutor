package com.lf.tutor.service;

import com.lf.tutor.Service.UserService;
import com.lf.tutor.TutorApplicationTests;
import com.lf.tutor.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public class UserServiceTest extends TutorApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void insertTest(){
        User user = new User();
        user.setUserName("abc");
        user.setPwd("123");
        user.setRoleId("1");
        userService.insert(user);
    }
}
