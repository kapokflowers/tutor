package com.lf.tutor.controller;

import com.lf.tutor.Service.EduOrderService;
import com.lf.tutor.Service.TecherCollectService;
import com.lf.tutor.Service.TecherOrderService;
import com.lf.tutor.Service.TecherService;
import com.lf.tutor.config.ReturnCfg;
import com.lf.tutor.domain.EduOrder;
import com.lf.tutor.domain.Techer;
import com.lf.tutor.domain.TecherCollect;
import com.lf.tutor.domain.TecherOrder;
import com.lf.tutor.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/*
 * @Description: 订单路由
 * @Athor:
 * @version: v1.0
 */
@RestController
public class OrderController {
    @Autowired
    private EduOrderService eduOrderService;
    @Autowired
    private TecherService techerService;
    @Autowired
    private TecherCollectService techerCollectService;
    @Autowired
    private TecherOrderService techerOrderService;

    /**
     * 待接订单列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/i/order/list")
    public ResultDTO getTecher(int currentPage,int pageSize){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        EduOrder order = new EduOrder();
        order.setStatus("Z");
        order.setIsValid("Y");
        resultDTO.setData(eduOrderService.getEduList(order,currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 多条件查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/i/order/condition")
    public ResultDTO getTecherByCondition(int currentPage,int pageSize,EduOrder order){
        transNull(order);
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(eduOrderService.getEduList(order,currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 订单收藏
     * @param request
     * @param orderId
     * @return
     */
    @RequestMapping("/i/order/collect")
    public ResultDTO techerCollect(HttpServletRequest request, String orderId){
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        if(userId == null|| userId.equals("null")){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        Techer techer = techerService.getTecherById(userId);
        if(techer == null){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("不是教员不能收藏订单");
            return resultDTO;
        }
        if("N".equals(techer.getIsApprove())){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("教员资料没通过审核");
            return resultDTO;
        }

        TecherCollect techerCollect = new TecherCollect();
        techerCollect.setTecherId(Integer.valueOf(userId));
        techerCollect.setOrderId(Integer.valueOf(orderId));
        techerCollect.setIsValid("Y");
        if(techerCollectService.checkExist(techerCollect) != null){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("已经收藏成功，不能重复收藏");
            return resultDTO;
        }
        techerCollectService.insert(techerCollect);
        return resultDTO;
    }

    /**
     * 教员预约订单
     * @param request
     * @param orderId
     * @return
     */
    @RequestMapping("/i/order/techer/get")
    public ResultDTO techerOrder(HttpServletRequest request, String orderId){
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        if(userId == null|| userId.equals("null")){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        Techer techer = techerService.getTecherById(userId);
        if(techer == null){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("不是教员不能预约订单");
            return resultDTO;
        }
        if("N".equals(techer.getIsApprove())){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("教员资料没通过审核");
            return resultDTO;
        }

        TecherOrder techerOrder = new TecherOrder();
        techerOrder.setTecherId(Integer.valueOf(userId));
        techerOrder.setOrderId(Integer.valueOf(orderId));
        if(techerOrderService.getTecherOrderByCondition(techerOrder) != null){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("已经预约过，不能重复预约");
            return resultDTO;
        }

        techerOrderService.insert(techerOrder);
        return resultDTO;
    }


    /**
     * 把订单对象中的""转为null
     * @param order
     */
    public void transNull(EduOrder order){
        if(order.getStudentSex() != null && order.getStudentSex().equals("")){
            order.setStudentSex(null);
        }
        if(order.getTecherSex() != null && order.getTecherSex().equals("")){
            order.setTecherSex(null);
        }
        if(order.getGradeId() != null && order.getGradeId().equals("")){
            order.setGradeId(null);
        }
        if(order.getTimeTypeId() != null && order.getTimeTypeId().equals("")){
            order.setTimeTypeId(null);
        }
        if(order.getSubjectId() != null && order.getSubjectId().equals("")){
            order.setSubjectId(null);
        }
        if(order.getStatus() != null && order.getStatus().equals("")){
            order.setStatus(null);
        }
    }
}
