package com.lf.tutor.Service.impl;

import com.github.pagehelper.PageHelper;
import com.lf.tutor.Service.CommentService;
import com.lf.tutor.domain.Comment;
import com.lf.tutor.mapper.CommentMapper;
import com.lf.tutor.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Service("CommentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public Comment getCommentById(String commentId) {
        Comment comment = commentMapper.getCommentById(commentId);
        comment.setUserName(userMapper.getUserById(comment.getUserId()).getUserName());
        List<Comment> followList = commentMapper.getCommentListByPId(String.valueOf(comment.getCommentId()));
        for(int i = 0; i < followList.size(); i++){
            //获取用户名
            followList.get(i).setUserName(userMapper.getUserById(followList.get(i).getUserId()).getUserName());
        }
        //获取评论
        comment.setFollow(followList);
        return comment;
    }

    @Override
    public List<Comment> getCommentList(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Comment> result = commentMapper.getCommentList();
        for(int i = 0; i < result.size(); i++){
            //获取用户名
            result.get(i).setUserName(userMapper.getUserById(result.get(i).getUserId()).getUserName());
            //获取评论
            List<Comment> followList = commentMapper.getCommentListByPId(String.valueOf(result.get(i).getCommentId()));
            result.get(i).setFollow(followList);
        }
        return result;
    }

    @Override
    public List<Comment> getCommentListByPId(String parentCommentId) {
        return commentMapper.getCommentListByPId(parentCommentId);
    }

    @Override
    public void update(Comment comment) {
        commentMapper.update(comment);
    }
}
