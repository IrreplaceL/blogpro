package com.irreplace.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irreplace.constants.SystemConstants;
import com.irreplace.domain.entity.Comment;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.domain.vo.CommentVo;
import com.irreplace.domain.vo.PageVo;
import com.irreplace.enums.AppHttpCodeEnum;
import com.irreplace.exception.SystemException;
import com.irreplace.mapper.CommentMapper;
import com.irreplace.service.CommentService;
import com.irreplace.service.UserService;
import com.irreplace.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2024-07-21 22:06:27
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
   @Autowired
   private UserService userService;
    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //对articleId进行判断
        commentLambdaQueryWrapper.eq(Comment::getArticleId,articleId);
        //根评论 rootId为-1
        commentLambdaQueryWrapper.eq(Comment::getRootId, SystemConstants.Comment_ID_isROOTID);
        commentLambdaQueryWrapper.orderByDesc(Comment::getCreateTime);
        //分页查询
        Page<Comment> commentPage = new Page<>(pageNum,pageSize);
        page(commentPage,commentLambdaQueryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(commentPage.getRecords());
        //查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVo commentVo :
                commentVoList) {
         List<CommentVo> children = getChildrend(commentVo.getId());
         commentVo.setChildren(children);
        }
        //返回的结果是一个分页PageVo
        return ResponseResult.successResult(new PageVo(commentVoList,commentPage.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //对评论做一些判断，如评论内容不能为空
        if (!StringUtils.hasText(comment.getContent())){
            throw new SystemException(AppHttpCodeEnum.COMMENT_CONTENT_ISNULL);
        }
        save(comment);
        return ResponseResult.successResult();
    }

    private List<CommentVo> getChildrend(Long id) {
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getRootId,id);
        commentLambdaQueryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> commentList = list(commentLambdaQueryWrapper);
        //todo list是什么，还有上面的page
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        return commentVos;
    }

    private List<CommentVo> toCommentVoList(List<Comment> commentList) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        for (CommentVo commentVo: commentVos) {
            //通过createBy查询用户昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询
            if(commentVo.getToCommentUserId() != -1){
                String toCommentName = userService.getById(commentVo.getToCommentId()).getNickName();
                commentVo.setToCommentUserName(toCommentName);
            }
            //
        }
        return commentVos;
            //todo 用stream重写
    }
}
