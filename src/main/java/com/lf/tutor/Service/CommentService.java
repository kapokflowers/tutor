package com.lf.tutor.Service;

import com.lf.tutor.domain.Comment;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
public interface CommentService {
    //新增帖子
    void insert(Comment comment);
    //根据ID获取帖子内容
    Comment getCommentById(String commentId);
    //根据帖子ID获取评论内容
    List<Comment> getCommentListByPId(String parentCommentId);
    //获取所有帖子
    List<Comment> getCommentList(int currentPage,int pageSize);
    //更新帖子内容
    void update(Comment comment);
}
