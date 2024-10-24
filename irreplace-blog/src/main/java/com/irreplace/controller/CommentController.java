package com.irreplace.controller;

import com.irreplace.constants.SystemConstants;
import com.irreplace.domain.dto.AddCommentDto;
import com.irreplace.domain.entity.Comment;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.service.CategoryService;
import com.irreplace.service.CommentService;
import com.irreplace.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "评论",description = "评论相关接口" )
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
      return   commentService.commentList(SystemConstants.COMMENT_TYPE_ARTICLE,articleId,pageNum,pageSize);
    }
    @PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto){
    Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }
    @GetMapping ("/linkCommentList")
    @ApiOperation(value = "友链评论列表" ,notes = "获取一页友链信息")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "pageNum", value = "页号"),
           @ApiImplicitParam(name = "pageSize", value = "页号")
   })
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return  commentService.commentList(SystemConstants.COMMENT_TYPE_LINK,null,pageNum,pageSize);
    }
}
