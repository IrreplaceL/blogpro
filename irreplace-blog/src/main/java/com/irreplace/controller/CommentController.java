package com.irreplace.controller;

import com.irreplace.constants.SystemConstants;
import com.irreplace.domain.entity.Comment;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.service.CategoryService;
import com.irreplace.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/21 22:09
 * @Description:评论信息
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
      return   commentService.commentList(SystemConstants.COMMENT_TYPE_ARTICLE,articleId,pageNum,pageSize);
    }
    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
    @GetMapping ("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return  commentService.commentList(SystemConstants.COMMENT_TYPE_LINK,null,pageNum,pageSize);
    }
}
