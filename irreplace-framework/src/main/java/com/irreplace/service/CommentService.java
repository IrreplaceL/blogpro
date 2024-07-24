package com.irreplace.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.irreplace.domain.entity.Comment;
import com.irreplace.domain.entity.domain.ResponseResult;

/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2024-07-21 22:06:25
 */
public interface CommentService extends IService<Comment> {
    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
