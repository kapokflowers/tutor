package com.lf.tutor.controller;

import com.lf.tutor.Service.*;
import com.lf.tutor.config.NormalCfg;
import com.lf.tutor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * @Description:路由控制器
 * @Athor:
 * @version: v1.0
 */
@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TecherService techerService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private EduOrderService eduOrderService;
    @Autowired
    private StudentService studentService;

    /**
     * 首页路由
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request,ModelAndView model){
        model.setViewName("index");
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId != null && !userId.equals("null")){
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
        }
        return model;
    }

    /**
     * 登录页路由
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/loginhtml")
    public String loginhtml(HttpServletRequest request,ModelAndView model){
        return "login";
    }

    /**
     * 注册页路由
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/registerhtml")
    public String registerhtml(HttpServletRequest request,ModelAndView model){
        return "register";
    }

    /**
     * 帖子路由
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/discusshtml")
    public ModelAndView discusshtml(HttpServletRequest request,ModelAndView model){
        model.setViewName("discuss");
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId != null && !userId.equals("null")){
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
        }
        return model;
    }

    /**
     * 帖子详细页路由
     * @param request
     * @param p
     * @param model
     * @return
     */
    @RequestMapping("/discussdetailhtml")
    public ModelAndView discussdetailhtml(HttpServletRequest request,String p,ModelAndView model){
        model.setViewName("discuss_detail");
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId != null && !userId.equals("null")){
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
        }
        model.addObject("comment", commentService.getCommentById(p));
        return model;
    }

    /**
     * 教员页路由
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/techerhtml")
    public ModelAndView techerhtml(HttpServletRequest request,ModelAndView model){
        model.setViewName("techer");
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId != null && !userId.equals("null")){
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
        }
        model.addObject("gradeList", commonService.getGradeList());
        model.addObject("subjectList", commonService.getSubjectList());
        model.addObject("timeTypeList", commonService.getTimeTypeList());
        model.addObject("instituteList", commonService.getInstituteList());
        model.addObject("techerLevelList", commonService.getTecherLevelList());
        return model;
    }

    /**
     * 教员信息页路由
     * @param request
     * @param p
     * @param model
     * @return
     */
    @RequestMapping("/techerdetailhtml")
    public ModelAndView techerdetailhtml(HttpServletRequest request,String p,ModelAndView model){
        model.setViewName("techer_detail");
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId != null && !userId.equals("null")){
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
        }
        model.addObject("techer", techerService.getTecherById(p));
        return model;
    }

    /**
     * 订单页路由
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/orderhtml")
    public ModelAndView orderhtml(HttpServletRequest request,ModelAndView model){
        model.setViewName("order");
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId != null && !userId.equals("null")){
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
        }
        model.addObject("gradeList", commonService.getGradeList());
        model.addObject("subjectList", commonService.getSubjectList());
        model.addObject("timeTypeList", commonService.getTimeTypeList());
        return model;
    }

    /**
     * 个人页路由
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/person")
    public ModelAndView person(HttpServletRequest request,ModelAndView model){
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId == null|| userId.equals("null")){
            model.setViewName("forbid");
            return model;
        }
        User user = userService.getUserById(userId);
        if(user == null){
            model.setViewName("forbid");
            return model;
        }
        if(user.getRoleId().equals(NormalCfg.ADMIN)){
            model.setViewName("admin");
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
            return model;
        }else if(user.getRoleId().equals(NormalCfg.TECHER)){
            model.setViewName("person_techer");
            Techer techer = techerService.getTecherById(userId);
            if(techer != null){
                if("Y".equals(techer.getIsApprove())){
                    model.addObject("isApprove","Y");
                }else{
                    model.addObject("isApprove",null);
                }
            }
            model.addObject("userId",userId);
            model.addObject("gradeList", commonService.getGradeList());
            model.addObject("userName",userService.getUserById(userId).getUserName());
            model.addObject("instituteList", commonService.getInstituteList());
            model.addObject("subjectList", commonService.getSubjectList());
            model.addObject("timeTypeList", commonService.getTimeTypeList());
            return model;
        }else if(user.getRoleId().equals(NormalCfg.STUDENT)){
            model.setViewName("person_student");
            Student  student = studentService.getStudentById(userId);
            if(student != null){
                if("Y".equals(student.getIsApprove())){
                    model.addObject("isApprove","Y");
                }else{
                    model.addObject("isApprove",null);
                }
            }
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
            model.addObject("gradeList", commonService.getGradeList());
            model.addObject("subjectList", commonService.getSubjectList());
            model.addObject("timeTypeList", commonService.getTimeTypeList());
            return model;
        }
        model.setViewName("error");
        return model;
    }

    /**
     * 管理员页面路由
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/adminhtml")
    public ModelAndView adminhtml(HttpServletRequest request,ModelAndView model){
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId == null|| userId.equals("null")){
            model.setViewName("forbid");
            return model;
        }
        User user = userService.getUserById(userId);
        if(user == null || !user.getRoleId().equals(NormalCfg.ADMIN)){
            model.setViewName("forbid");
            return model;
        }
        model.setViewName("admin");
        model.addObject("userId",userId);
        model.addObject("userName",userService.getUserById(userId).getUserName());
        return model;
    }

    /**
     * 教员个人页路由
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/persontecherhtml")
    public ModelAndView persontecherhtml(ModelAndView model,HttpServletRequest request){
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId == null|| userId.equals("null")){
            model.setViewName("forbid");
            return model;
        }
        User user = userService.getUserById(userId);
        if(user == null || !user.getRoleId().equals(NormalCfg.TECHER)){
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
            model.setViewName("forbid");
            return model;
        }
        Techer techer = techerService.getTecherById(userId);
        if(techer != null){
            if("Y".equals(techer.getIsApprove())){
                model.addObject("isApprove","Y");
            }else{
                model.addObject("isApprove",null);
            }
        }
        model.setViewName("person_techer");
        model.addObject("userId",userId);
        model.addObject("userName",userService.getUserById(userId).getUserName());
        model.addObject("instituteList", commonService.getInstituteList());
        model.addObject("subjectList", commonService.getSubjectList());
        model.addObject("gradeList", commonService.getGradeList());
        model.addObject("timeTypeList", commonService.getTimeTypeList());
        return model;
    }

    /**
     * 学生个人页面路由
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/personstudenthtml")
    public ModelAndView personstudenthtml(ModelAndView model,HttpServletRequest request){
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId == null|| userId.equals("null")){
            model.setViewName("forbid");
            return model;
        }
        User user = userService.getUserById(userId);
        if(user == null || !user.getRoleId().equals(NormalCfg.STUDENT)){
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
            model.setViewName("forbid");
            return model;
        }
        Student  student = studentService.getStudentById(userId);
        if(student != null){
            if("Y".equals(student.getIsApprove())){
                model.addObject("isApprove","Y");
            }else{
                model.addObject("isApprove",null);
            }
        }
        model.setViewName("person_student");
        model.addObject("userId",userId);
        model.addObject("userName",userService.getUserById(userId).getUserName());
        model.addObject("gradeList", commonService.getGradeList());
        model.addObject("subjectList", commonService.getSubjectList());
        model.addObject("timeTypeList", commonService.getTimeTypeList());
        return model;
    }

    /**
     * 获取图片
     * @param imageType
     * @param uId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value="/getPhoto")
    public void getPhotoById (String imageType, String uId,HttpServletRequest request,final HttpServletResponse response) throws IOException {
        if(uId == null || uId.equals("")){
            uId = String.valueOf(request.getSession().getAttribute("userid"));
            if(uId == null|| uId.equals("null")){
                return;
            }
        }

        Image image = new Image();
        image.setImageType(imageType);
        image.setUserId(Integer.valueOf(uId));
        Image entity = imageService.getImage(image);
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputSream = response.getOutputStream();
        if(entity == null){
            outputSream.close();
            return;
        }
        byte[] data = entity.getImageContent();
        InputStream in = new ByteArrayInputStream(data);
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            outputSream.write(buf, 0, len);
        }
        outputSream.close();
    }

    /**
     * 登出路由
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView model,HttpServletRequest request){
        request.getSession().invalidate();
        model.setViewName("index");
        return model;
    }

    /**
     * 订单详情页路由
     * @param p
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/orderdetailhtml")
    public ModelAndView orderdetailhtml(String p,ModelAndView model,HttpServletRequest request){
        model.setViewName("/order_detail");
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId == null|| userId.equals("null")){
//            model.addObject("userId",userId);
//            model.addObject("userName",userService.getUserById(userId).getUserName());
            model.setViewName("forbid");
            return model;
        }

        model.addObject("orderId",p);
        model.addObject("userId",userId);
        EduOrder order = eduOrderService.getEduById(p);
        if(order.getTecherId() != null){
            model.addObject("flag","1");
        }else{
            model.addObject("flag","2");
        }
        return model;
    }

    /**
     * 教员推荐页路由
     * @param p
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/recommendhtml")
    public ModelAndView recommendhtml(String p,ModelAndView model,HttpServletRequest request){
        model.setViewName("/recommend");
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId == null|| userId.equals("null")){
//            model.addObject("userId",userId);
//            model.addObject("userName",userService.getUserById(userId).getUserName());
            model.setViewName("forbid");
            return model;
        }

        EduOrder order = eduOrderService.getEduById(p);
        if(order.getTecherId() != null){
            model.addObject("userId",userId);
            model.addObject("userName",userService.getUserById(userId).getUserName());
            model.setViewName("forbid");
            return model;
        }
        model.addObject("userId",userId);
        model.addObject("order",order);
        model.addObject("orderId",order.getOrderId());
        return model;
    }
}
