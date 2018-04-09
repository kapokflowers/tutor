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

@RestController
public class TecherController {
    @Autowired
    private TecherService techerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentCollectService studentCollectService;
    @Autowired
    private TecherCollectService techerCollectService;
    @Autowired
    private EduOrderService eduOrderService;
    @Autowired
    private TecherOrderService techerOrderService;

    /**
     * 教员列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/i/techer/list")
    public ResultDTO getTecher(int currentPage,int pageSize){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(techerService.getTecherList(new Techer(),currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 教员多条件查询
     * @param currentPage
     * @param pageSize
     * @param techer
     * @return
     */
    @RequestMapping("/i/techer/condition")
    public ResultDTO getTecherByCondition(int currentPage,int pageSize,Techer techer){
        transNull(techer);
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(techerService.getTecherList(techer,currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 收藏教员
     * @param request
     * @param techerId
     * @return
     */
    @RequestMapping("/i/techer/collect")
    public ResultDTO techerCollect(HttpServletRequest request,String techerId){
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        if(userId == null|| userId.equals("null")){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        Student student = studentService.getStudentById(userId);
        if(student == null){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("不是学生用户不能收藏教员");
            return resultDTO;
        }
        if("N".equals(student.getIsApprove())){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("学生没有通过审核");
            return resultDTO;
        }

        StudentCollect studentCollect = new StudentCollect();
        studentCollect.setStudentId(Integer.valueOf(userId));
        studentCollect.setTecherId(Integer.valueOf(techerId));
        studentCollect.setIsValid("Y");
        if(studentCollectService.checkExist(studentCollect) != null){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("已经收藏成功，不能重复收藏");
            return resultDTO;
        }
        studentCollectService.insert(studentCollect);
        return resultDTO;
    }

    /**
     * 获取教员的详细信息
     * @param request
     * @return
     */
    @RequestMapping("/techer/info/get")
    public ResultDTO getTecher(HttpServletRequest request){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if(uId == null || uId.equals("null")){
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        Techer techer = techerService.getTecherById(uId);
        if(techer == null){
            resultDTO = new ResultDTO(ReturnCfg.ERROR_CODE,ReturnCfg.ERROR_MSG);
            return resultDTO;
        }else{
            resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
            resultDTO.setData(techer);
            return resultDTO;
        }
    }

    /**
     * 保存教员的信息
     * @param techer
     * @param request
     * @return
     */
    @RequestMapping("/techer/info/save")
    public ResultDTO saveTecher(Techer techer,HttpServletRequest request) {
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);

        techer.setTecherId(Integer.valueOf(uId));

        Techer temp = techerService.getTecherById(uId);
        if(temp == null){
            techerService.insert(techer);
        }else{
            temp.setTecherName(techer.getTecherName());
            temp.setTecherSex(techer.getTecherSex());
            temp.setEduLevelId(techer.getEduLevelId());
            temp.setEduThId(techer.getEduThId());
            temp.setInstituteId(techer.getInstituteId());
            temp.setPhone(techer.getPhone());
            temp.setGradeId(techer.getGradeId());
            temp.setSubjectId(techer.getSubjectId());
            temp.setTimeTypeId(techer.getTimeTypeId());
            temp.setIntro(techer.getIntro());
            temp.setExp(techer.getExp());
            temp.setSelfComment(techer.getSelfComment());
            techerService.update(temp);
        }
        return resultDTO;
    }

    /**
     * 教员收藏列表
     * @param request
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/techer/collect/list")
    public ResultDTO getCollect(HttpServletRequest request,int currentPage,int pageSize){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        List<EduOrder> orderList = techerCollectService.getOrderByTecherId(uId,currentPage,pageSize);
        resultDTO.setData(orderList);
        return resultDTO;
    }

    /**
     * 删除教员某个收藏
     * @param request
     * @param techerCollect
     * @return
     */
    @RequestMapping("/techer/collect/delete")
    public ResultDTO delCollect(HttpServletRequest request,TecherCollect techerCollect){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        techerCollect.setTecherId(Integer.valueOf(uId));
        techerCollect.setIsValid("N");
        techerCollectService.update(techerCollect);

        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        List<EduOrder> orderList = techerCollectService.getOrderByTecherId(uId,1,5);
        resultDTO.setData(orderList);
        return resultDTO;
    }

    /**
     * 教员已经完成的订单列表
     * @param request
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/techer/order/list")
    public ResultDTO getOrder(HttpServletRequest request,int currentPage,int pageSize){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        EduOrder order = new EduOrder();
        order.setTecherId(Integer.valueOf(uId));
        order.setStatus("C");
        order.setIsValid("Y");
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        List<EduOrder> orderList = eduOrderService.getEduList(order,currentPage,pageSize);
        resultDTO.setData(orderList);
        return resultDTO;
    }

    /**
     * 获取教员的所有订单
     * @param request
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/techer/date/list")
    public ResultDTO getDateList(HttpServletRequest request,int currentPage,int pageSize){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        List<TecherOrder> techerOrderList = techerOrderService.getListBySId(uId,currentPage,pageSize);
        resultDTO.setData(techerOrderList);
        return resultDTO;
    }

    /**
     * 把教员对象中的""转为null
     * @param techer
     */
    public void transNull(Techer techer){
        if(techer.getTecherSex() != null && techer.getTecherSex().equals("")){
            techer.setTecherSex(null);
        }
        if(techer.getTecherName() != null && techer.getTecherName().equals("")){
            techer.setTecherName(null);
        }
        if(techer.getInstituteId() != null && techer.getInstituteId().equals("")){
            techer.setInstituteId(null);
        }
        if(techer.getEduLevelId() != null && techer.getEduLevelId().equals("")){
            techer.setEduLevelId(null);
        }
        if(techer.getIsApprove() != null && techer.getIsApprove().equals("")){
            techer.setIsApprove(null);
        }
        if(techer.getTecherLevelId() != null && techer.getTecherLevelId().equals("")){
            techer.setTecherLevelId(null);
        }
        if(techer.getTimeTypeId() != null && techer.getTimeTypeId().equals("")){
            techer.setTimeTypeId(null);
        }
        if(techer.getSubjectId() != null && techer.getSubjectId().equals("")){
            techer.setSubjectId(null);
        }
        if(techer.getGradeId() != null && techer.getGradeId().equals("")){
            techer.setGradeId(null);
        }
    }
}
