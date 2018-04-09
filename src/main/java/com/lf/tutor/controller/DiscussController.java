package com.lf.tutor.controller;

import com.lf.tutor.Service.CommentService;
import com.lf.tutor.config.ReturnCfg;
import com.lf.tutor.domain.Comment;
import com.lf.tutor.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/*
 * @Description: 帖子控制器
 * @Athor:
 * @version: v1.0
 */
@RestController
public class DiscussController {
    @Autowired
    private CommentService commentService;

    /**
     * 获取帖子
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/i/comment/list")
    public ResultDTO getCommentList(int currentPage,int pageSize){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        resultDTO.setData(commentService.getCommentList(currentPage,pageSize));
        return resultDTO;
    }

    /**
     * 新增帖子
     * @param request
     * @param comment
     * @return
     */
    @RequestMapping("/i/comment/add")
    public ResultDTO addComment(HttpServletRequest request,Comment comment){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //先检查sesson中是否有用户ID
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId == null || userId.equals("null")){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        commentService.insert(comment);
        resultDTO.setData(commentService.getCommentList(1,10));
        return resultDTO;
    }

    /**
     * 对帖子新增评论
     * @param request
     * @param comment
     * @return
     */
    @RequestMapping("/i/comment/follow/add")
    public ResultDTO addFollow(HttpServletRequest request,Comment comment){
        ResultDTO resultDTO = new ResultDTO(ReturnCfg.SUCCESS_CODE,ReturnCfg.SUCCESS_MSG);
        //先检查sesson中是否有用户ID
        String userId = String.valueOf(request.getSession().getAttribute("userid"));
        if(userId == null || userId.equals("null")){
            resultDTO.setCode(ReturnCfg.ERROR_CODE);
            resultDTO.setMsg("请先登录");
            return resultDTO;
        }
        comment.setUserId(userId);
        commentService.insert(comment);
        resultDTO.setData(commentService.getCommentById(comment.getParentCommentId()));
        return resultDTO;
    }
}
