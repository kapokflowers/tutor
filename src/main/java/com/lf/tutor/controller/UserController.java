package com.lf.tutor.controller;

import com.lf.tutor.Service.UserService;
import com.lf.tutor.config.NormalCfg;
import com.lf.tutor.config.ReturnCfg;
import com.lf.tutor.domain.User;
import com.lf.tutor.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/register")
    public ResultDTO register(User user, HttpServletRequest request, HttpServletResponse response){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //先检查账号是否存在
        if(userService.getUserByName(user.getUserName()) != null){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("账号已经存在");
            return resultDTO;
        }
        //如果账号不存在则新增
        userService.insert(user);
        user = userService.getUserByName(user.getUserName());
        //（前端控制）把账号ID放进session，如果是教员则跳转至教员页面，如果是学生则跳转至学生页面
        request.getSession().setAttribute("userid",user.getUserId());
        if(user.getRoleId().equals(NormalCfg.STUDENT)){
            resultDTO.setData("student");
            return resultDTO;
        }else{
            resultDTO.setData("techer");
            return resultDTO;
        }

    }

    /**
     * 用户登录
     * @param user
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public ResultDTO login(User user, HttpServletRequest request, HttpServletResponse response){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.ERROR_CODE,ReturnCfg.ERROR_MSG);
        if(user == null){
            return resultDTO;
        }
        //查询用户是否存在以及密码是否正确
        User temp = userService.getUserByName(user.getUserName());
        if(temp == null || !temp.getPwd().equals(user.getPwd())){
            return resultDTO;
        }
        resultDTO.setCode(ReturnCfg.SUCCESS_CODE);
        resultDTO.setMsg(ReturnCfg.SUCCESS_MSG);
        //（前端控制）如果是管理员则跳至管理员页面，如果是教员则跳转至教员页面，如果是学生则跳转至学生页面
        if(temp.getRoleId().equals(NormalCfg.ADMIN)){
            resultDTO.setData("admin");
        }else if(temp.getRoleId().equals(NormalCfg.TECHER)){
            resultDTO.setData("techer");
        }else if(temp.getRoleId().equals(NormalCfg.STUDENT)){
            resultDTO.setData("student");
        }
        request.getSession().setAttribute("userid",temp.getUserId());
        return resultDTO;
    }
}
