package com.irreplace.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * 文章表(IrrArticle)实体类
 *
 * @author makejava
 * @since 2024-07-16 13:53:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("irr_article")
@Accessors(chain = true)  //set方法同步返回该实体对象
public class Article {
    private static final long serialVersionUID = 633836125669055427L;
    @TableId
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 所属分类id
     */
    private Long categoryId;
    @TableField(exist = false)  //表示该字段不存在于数据表中
    private String categoryName;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 是否置顶（0否，1是）
     */
    private String isTop;
    /**
     * 状态（0已发布，1 草稿）
     */
    private String status;
    /**
     * 访问量
     */
    private Long viewCount;
    /**
     * 是否允许评论 1是，0否
     */
    private String isComment;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    /**
     * 删除标志（0 代表未删除，1 代表已删除）
     */
    private Integer delFlag;


}

