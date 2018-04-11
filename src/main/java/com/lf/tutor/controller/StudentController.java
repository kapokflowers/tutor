package com.lf.tutor.controller;

import com.lf.tutor.Service.*;
import com.lf.tutor.config.ReturnCfg;
import com.lf.tutor.domain.*;
import com.lf.tutor.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @Description: 学生控制器
 * @Athor:
 * @version: v1.0
 */
@RestController
public class StudentController {
    @Autowired
    private UserService userService;
    @Autowired
    private TecherService techerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private StudentCollectService studentCollectService;
    @Autowired
    private EduOrderService eduOrderService;
    @Autowired
    private TecherOrderService techerOrderService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

    /**
     * 获取学生信息
     * @param request
     * @return
     */
    @RequestMapping("/student/info/get")
    public ResultDTO getStudent(HttpServletRequest request){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if(uId == null || uId.equals("null")){
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        Student student = studentService.getStudentById(uId);
        if(student == null){
            resultDTO = new ResultDTO(ReturnCfg.ERROR_CODE,ReturnCfg.ERROR_MSG);
            return resultDTO;
        }else{
            resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
            resultDTO.setData(student);
            return resultDTO;
        }
    }

    /**
     * 保存学生信息
     * @param student
     * @param request
     * @return
     */
    @RequestMapping("/student/info/save")
    public ResultDTO saveStudent(Student student,HttpServletRequest request){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if(uId == null || uId.equals("null")){
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);;
        student.setStudentId(Integer.valueOf(uId));

        Student temp = studentService.getStudentById(uId);
        if(temp == null){
            studentService.insert(student);
        }else{
            temp.setStudentName(student.getStudentName());
            temp.setSex(student.getSex());
            temp.setGradeId(student.getGradeId());
            temp.setSubjectId(student.getSubjectId());
            temp.setTimeTypeId(student.getTimeTypeId());
            temp.setAddress(student.getAddress());
            temp.setPhone(student.getPhone());
            studentService.update(temp);
        }
        return resultDTO;
    }

    /**
     * 发布订单
     * @param order
     * @param request
     * @return
     */
    @RequestMapping("/student/order/add")
    public ResultDTO addOrder(EduOrder order,HttpServletRequest request){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        Student student = studentService.getStudentById(uId);
        if(student == null){
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先完善资料！");
            return resultDTO;
        }
        if("N".equals(student.getIsApprove())){
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("审核未通过不能发布订单！");
            return resultDTO;
        }
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        order.setStudentId(Integer.valueOf(uId));
        eduOrderService.insert(order);
        return resultDTO;
    }

    /**
     * 获取学生收藏列表
     * @param request
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/student/collect/list")
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
        //获取收藏的教员
        List<Techer> techerList = studentCollectService.getTecherList(uId,currentPage,pageSize);
        resultDTO.setData(techerList);
        return resultDTO;
    }

    /**
     * 删除收藏
     * @param request
     * @param studentCollect
     * @return
     */
    @RequestMapping("/student/collect/delete")
    public ResultDTO delCollect(HttpServletRequest request,StudentCollect studentCollect){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        studentCollect.setStudentId(Integer.valueOf(uId));
        studentCollect.setIsValid("N");
        studentCollectService.update(studentCollect);

        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        //获取收藏的教员
        List<Techer> techerList = studentCollectService.getTecherList(uId,1,5);
        resultDTO.setData(techerList);
        return resultDTO;
    }

    /**
     * 获取已完成订单列表
     * @param request
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/student/order/list")
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
        order.setStudentId(Integer.valueOf(uId));
        order.setStatus("C");
        order.setIsValid("Y");
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        List<EduOrder> orderList = eduOrderService.getEduList(order,currentPage,pageSize);
        resultDTO.setData(orderList);
        return resultDTO;
    }

    /**
     * 获取待接订单列表
     * @param request
     * @param orderId
     * @return
     */
    @RequestMapping("/student/unorder/techer/list")
    public ResultDTO getTecherByOrderId(HttpServletRequest request,String orderId){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        EduOrder order = eduOrderService.getEduById(orderId);
        if(!order.getStudentId().equals(Integer.valueOf(uId))){
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("不是您的订单不能查看");
            return resultDTO;
        }

        List<Techer> techerList = techerOrderService.getTecherByOrderId(orderId);
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
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
    @RequestMapping("/student/order/accept")
    public ResultDTO acceptOrder(String orderId,String techerId,HttpServletRequest request){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        EduOrder order = eduOrderService.getEduById(orderId);
        if(!order.getStudentId().equals(Integer.valueOf(uId))){
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("不是您的订单不能查看");
            return resultDTO;
        }

        TecherOrder techerOrder = new TecherOrder();
        techerOrder.setOrderId(Integer.valueOf(orderId));
        techerOrder.setTecherId(Integer.valueOf(techerId));
        techerOrder.setIsValid("Y");
        techerOrder.setStatus("S");
        techerOrderService.update(techerOrder);
        //edu_order表也要更新数据
        order.setTecherId(Integer.valueOf(techerId));
        order.setStatus("S");
        order.setIsRecommend("N");
        eduOrderService.update(order);
        //把这个order对应的数据都置为拒绝
        List<TecherOrder> orderList = techerOrderService.getListByOrderId(orderId);
        for(int i = 0; i < orderList.size(); i++){
            if(!orderList.get(i).getTecherId().equals(techerId)){
                orderList.get(i).setStatus("N");
                techerOrderService.update(orderList.get(i));
            }
        }

        List<Techer> techerList = techerOrderService.getTecherByOrderId(orderId);
        resultDTO.setData(techerList);
        return resultDTO;
    }

    /**
     * 获取待接订单列表
     * @param request
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/student/unorder/list")
    public ResultDTO getUnOrderList(HttpServletRequest request,int currentPage,int pageSize){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        EduOrder order = new EduOrder();
        order.setStudentId(Integer.valueOf(uId));
        order.setStatus("Z");
        order.setIsValid("Y");
        List<EduOrder> unorderList = eduOrderService.getEduList(order,currentPage,pageSize);
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(unorderList);
        return resultDTO;
    }

    /**
     * 获取正在试课的订单列表
     * @param request
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/student/ingorder/list")
    public ResultDTO getIngOrderList(HttpServletRequest request,int currentPage,int pageSize){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        EduOrder order = new EduOrder();
        order.setStudentId(Integer.valueOf(uId));
        order.setStatus("S");
        order.setIsValid("Y");
        List<EduOrder> ingorderList = eduOrderService.getEduList(order,currentPage,pageSize);
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(ingorderList);
        return resultDTO;
    }

    /**
     * 获取前12个月的订单数据绘制条形图
     * @param request
     * @return
     */
    @RequestMapping("/student/chart/1")
    public ResultDTO part1Chart(HttpServletRequest request){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        Calendar calendar = Calendar.getInstance();
        List<String> timeList = new ArrayList<String>(12);
        for(int i = 0; i < 12; i++){
            Date date = calendar.getTime();
            String time = sdf.format(date);
            timeList.add(time);
            calendar.add(Calendar.MONTH,-1);
        }

        List<Integer> countList = new ArrayList<Integer>(12);
        for(String i:timeList){
            countList.add(eduOrderService.getCountOrder(uId,i+"-01"));
        }
        Map resultMap = new HashMap(2);
        resultMap.put("time",timeList);
        resultMap.put("count",countList);
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(resultMap);
        return resultDTO;
    }

    /**
     * 获取订单的教员学历信息绘制饼图
     * @param request
     * @return
     */
    @RequestMapping("/student/chart/2")
    public ResultDTO part2Chart(HttpServletRequest request){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        List<Techer> techerList = techerOrderService.getTecherByStudentId(uId);
        List<String> dataList = new ArrayList<>();
        List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String,Object> resultMap = new HashMap<String,Object>(2);

        for(int i = 0; i < techerList.size(); i++){
            if(!dataList.contains(techerList.get(i).getEduLevelDesc())){
                dataList.add(techerList.get(i).getEduLevelDesc());
            }
        }
        for(int i = 0; i < dataList.size(); i++){
            Map<String,Object> tempMap = new HashMap<String,Object>(2);
            int tempValue = 0;
            for(int j = 0; j < techerList.size(); j++){
                if(dataList.get(i).equals(techerList.get(j).getEduLevelDesc())){
                    tempValue+=1;
                }
            }
            tempMap.put("value",tempValue);
            tempMap.put("name",techerList.get(i).getEduLevelDesc());
            mapList.add(tempMap);
        }
        resultMap.put("dataList",dataList);
        resultMap.put("mapList",mapList);
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(resultMap);
        return resultDTO;
    }

    /**
     * 获取教员的学院信息绘制饼图
     * @param request
     * @return
     */
    @RequestMapping("/student/chart/3")
    public ResultDTO part3Chart(HttpServletRequest request){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        List<Techer> techerList = techerOrderService.getTecherByStudentId(uId);
        List<String> dataList = new ArrayList<>();
        List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String,Object> resultMap = new HashMap<String,Object>(2);

        for(int i = 0; i < techerList.size(); i++){
            if(!dataList.contains(techerList.get(i).getInstituteDesc())){
                dataList.add(techerList.get(i).getInstituteDesc());
            }
        }
        for(int i = 0; i < dataList.size(); i++){
            Map<String,Object> tempMap = new HashMap<String,Object>(2);
            int tempValue = 0;
            for(int j = 0; j < techerList.size(); j++){
                if(dataList.get(i).equals(techerList.get(j).getInstituteDesc())){
                    tempValue+=1;
                }
            }
            tempMap.put("value",tempValue);
            tempMap.put("name",techerList.get(i).getInstituteDesc());
            mapList.add(tempMap);
        }
        resultMap.put("dataList",dataList);
        resultMap.put("mapList",mapList);
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(resultMap);
        return resultDTO;
    }

    /**
     * 给教员评价
     * @param request
     * @return
     */
    @RequestMapping("/student/comment/add")
    public ResultDTO addComment(HttpServletRequest request){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }

        String techerId = request.getParameter("techerId");
        String orderId = request.getParameter("orderId");
        String score = request.getParameter("score");
        String techerComment = request.getParameter("techerComment");

        //更新订单
        EduOrder order = eduOrderService.getEduById(orderId);
        order.setStatus("C");
        order.setTecherScore(Double.valueOf(score));
        order.setTecherComment(techerComment);
        eduOrderService.update(order);
        //更新老师评分
        Techer techer = techerService.getTecherById(techerId);
        Double newScore = Double.valueOf(score)*0.25 + techer.getScore()*0.75;
        if(newScore > 4.5){
            techer.setTecherLevelId("1");
        }else if(newScore > 4){
            techer.setTecherLevelId("2");
        }else {
            techer.setTecherLevelId("3");
        }
        techer.setScore(newScore);
        techerService.update(techer);
        //更新老师订单关联
        TecherOrder techerOrder = new TecherOrder();
        techerOrder.setTecherId(Integer.valueOf(techerId));
        techerOrder.setOrderId(Integer.valueOf(orderId));
        techerOrder.setIsValid("Y");
        techerOrder = techerOrderService.getByTecherOrderId(techerId,orderId);
        techerOrder.setStatus("C");
        techerOrderService.update(techerOrder);

        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(null);
        return resultDTO;
    }

    /**
     * 请求对订单推荐教员
     * @param orderId
     * @return
     */
    @RequestMapping("/student/recommend/add")
    public ResultDTO getRecommend(HttpServletRequest request,String orderId,String wxOrderId){
        String uId = String.valueOf(request.getSession().getAttribute("userid"));
        ResultDTO resultDTO = null;
        if (uId == null || uId.equals("null")) {
            resultDTO = new ResultDTO();
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        EduOrder order = eduOrderService.getEduById(orderId);
        order.setIsRecommend("Y");
        order.setWxOrderId(wxOrderId);
        eduOrderService.update(order);

        EduOrder temp = new EduOrder();
        temp.setStudentId(Integer.valueOf(uId));
        temp.setStatus("Z");
        temp.setIsValid("Y");
        List<EduOrder> unorderList = eduOrderService.getEduList(temp,1,5);
        resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE, ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(unorderList);
        return resultDTO;
    }
}
