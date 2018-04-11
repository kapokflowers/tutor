package com.lf.tutor.controller;

import com.lf.tutor.Service.*;
import com.lf.tutor.config.ReturnCfg;
import com.lf.tutor.domain.*;
import com.lf.tutor.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * @Description: 管理员请求
 * @Athor:
 * @version: v1.0
 */
@RestController
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private TecherService techerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private EduOrderService eduOrderService;
    @Autowired
    private TecherOrderService techerOrderService;


    /**
     * 删除教员
     * @param techerId
     * @return
     */
    @RequestMapping("/admin/techer/delete")
    public ResultDTO deleteTecher(String techerId){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //先获取教员信息
        Techer techer = techerService.getTecherById(techerId);
        //把标志置为N，代表删除
        techer.setIsValid("N");
        techerService.update(techer);

        //同时把user表中的记录也置为无效
        User user = userService.getUserById(techerId);
        user.setIsValid("N");
        userService.update(user);

        //返回最新记录集合
        resultDTO.setData(techerService.getTecherList(new Techer(),1,10));
        return resultDTO;
    }

    /**
     * 删除学生
     * @param studentId
     * @return
     */
    @RequestMapping("/admin/student/delete")
    public ResultDTO deleteStudent(String studentId){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //先获取学生信息
        Student student = studentService.getStudentById(studentId);
        //把标志置为N，代表删除
        student.setIsValid("N");
        studentService.update(student);

        //同时把user表中的记录也置为无效
        User user = userService.getUserById(studentId);
        user.setIsValid("N");
        userService.update(user);
        //返回最新记录集合
        resultDTO.setData(studentService.getStudentList(new Student(),1,10));
        return resultDTO;
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @RequestMapping("/admin/comment/delete")
    public ResultDTO deleteComment(String commentId){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //获取评论信息
        Comment comment = commentService.getCommentById(commentId);
        //标志位置为无效
        comment.setIsValid("N");
        commentService.update(comment);
        //返回最新记录集合
        resultDTO.setData(commentService.getCommentList(1,10));
        return resultDTO;
    }

    /**
     * 获取教员信息
     * @param techerId
     * @return
     */
    @RequestMapping("/admin/techer/get")
    public ResultDTO techerInfo(String techerId){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(techerService.getTecherById(techerId));
        return resultDTO;
    }

    /**
     * 获取教员信息
     * @param studentId
     * @return
     */
    @RequestMapping("/admin/student/get")
    public ResultDTO studentInfo(String studentId){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(studentService.getStudentById(studentId));
        return resultDTO;
    }

    /**
     * 教员审批通过
     * @param techerId
     * @return
     */
    @RequestMapping("/admin/techer/approve")
    public ResultDTO techerApprove(String techerId){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //获取教员信息，更新审核标志位，更新
        Techer techer = techerService.getTecherById(techerId);
        techer.setIsApprove("Y");
        techerService.update(techer);

        //返回待审核记录集合
        Techer temp = new Techer();
        temp.setIsApprove("N");
        resultDTO.setData(techerService.getTecherList(temp,1,10));
        return resultDTO;
    }

    /**
     * 学员审批通过
     * @param studentId
     * @return
     */
    @RequestMapping("/admin/student/approve")
    public ResultDTO studentApprove(String studentId){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //获取学生信息，更新审核标志位，更新
        Student student = studentService.getStudentById(studentId);
        student.setIsApprove("Y");
        studentService.update(student);
        //返回待审核记录集合
        Student temp = new Student();
        temp.setIsApprove("N");
        resultDTO.setData(studentService.getStudentList(temp,1,10));
        return resultDTO;
    }

    /**
     * 获取教员审核列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/admin/approve/list")
    public ResultDTO getApprove(int currentPage,int pageSize){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //返回待审核标志为N的记录集合
        Techer techer = new Techer();
        techer.setIsApprove("N");
        resultDTO.setData(techerService.getTecherList(techer,currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 获取学生审核列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/admin/approve/student/list")
    public ResultDTO getStudentApprove(int currentPage,int pageSize){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //返回待审核标志为N的记录集合
        Student student = new Student();
        student.setIsApprove("N");
        resultDTO.setData(studentService.getStudentList(student,currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 获取教员列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/admin/techer/list")
    public ResultDTO getTecher(int currentPage,int pageSize){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(techerService.getTecherList(new Techer(),currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 获取学生列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/admin/student/list")
    public ResultDTO getStudent(int currentPage,int pageSize){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(studentService.getStudentList(new Student(),currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 获取评论列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/admin/comment/list")
    public ResultDTO getComment(int currentPage,int pageSize){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(commentService.getCommentList(currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 获取待推荐的订单
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/admin/recommend/list")
    public ResultDTO getRecommendList(int currentPage,int pageSize){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        EduOrder order = new EduOrder();
        //返回是否推荐标志为N且有效的记录集合
        order.setIsRecommend("Y");
        order.setIsValid("Y");
        resultDTO.setData(eduOrderService.getEduList(order,currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 获取推荐教员
     * @param orderId
     * @return
     */
    @RequestMapping("/admin/recommend/techer")
    public ResultDTO getRecommendTecher(String orderId){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //先获取订单信息
        EduOrder order = eduOrderService.getEduById(orderId);
        Techer techer = new Techer();
        //根据订单中的性别、授课时间、年级、科目信息对老师进行筛选
        techer.setTecherSex(order.getTecherSex());
        techer.setTimeTypeId(order.getTimeTypeId());
        techer.setGradeId(order.getGradeId());
        techer.setSubjectId(order.getSubjectId());
        List<Techer> techerList = techerService.getMatchTecherList(techer);
        //如果没有相关的教员信息，则把评分最高的前3返回
        if(techerList == null || techerList.size() == 0){
            techerList = techerService.getTop3TecherList();
        }
        resultDTO.setData(techerList);
        return resultDTO;
    }

    /**
     * 选择教员为此订单的教员
     * @param orderId
     * @param techerId
     * @param request
     * @return
     */
    @RequestMapping("/admin/recommend/accept")
    public ResultDTO acceptOrder(String orderId,String techerId,HttpServletRequest request){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        //获取教员信息以及订单信息
        EduOrder order = eduOrderService.getEduById(orderId);
        //用教员信息更新订单中的教员信息
        order.setIsRecommend("N");
        eduOrderService.update(order);

        TecherOrder techerOrder = new TecherOrder();
        techerOrder.setOrderId(Integer.valueOf(orderId));
        techerOrder.setTecherId(Integer.valueOf(techerId));
        techerOrder.setStatus("Z");
        techerOrder.setIsRecommend("Y");
        techerOrderService.insert(techerOrder);
        return resultDTO;
    }
}
