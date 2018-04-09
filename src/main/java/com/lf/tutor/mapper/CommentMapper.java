package com.lf.tutor.mapper;

import com.lf.tutor.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*
 * @Description:
 * @Athor:
 * @version: v1.0
 */
@Mapper
public interface CommentMapper {
    /**
     * 新增帖子
     * @param comment
     */
    @Insert("insert into comment(title,content, is_valid, user_id, parent_comment_id, created_date, updated_date) " +
            "values (#{title},#{content}, 'Y', #{userId}, #{parentCommentId}, now(), now())")
    void insert(Comment comment);

    /**
     * 根据ID获取帖子内容
     * @param commentId
     * @return
     */
    @Select("select * from comment where comment_id = #{commentId} and is_valid = 'Y' ")
    Comment getCommentById(String commentId);

    /**
     * 根据帖子ID获取评论内容
     * @param parentCommentId
     * @return
     */
    @Select("select * from comment where parent_comment_id = #{parentCommentId} and is_valid = 'Y' order by updated_date desc")
    List<Comment> getCommentListByPId(String parentCommentId);

    /**
     * 获取所有帖子
     * @return
     */
    @Select("select * from comment where is_valid = 'Y' and parent_comment_id is null order by updated_date desc")
    List<Comment> getCommentList();

    /**
     * 更新帖子内容
     * @param comment
     */
    @Update("update comment set title=#{title},content=#{content},is_valid=#{isValid},updated_date=now() where comment_id = #{commentId}")
    void update(Comment comment);
}
