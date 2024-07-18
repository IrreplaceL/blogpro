package com.irreplace.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/18 20:18
 * @Description:文章详情字段
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {
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
     * 所属分类id
     */
    private Long categoryId;

    private String categoryName;

    private Long viewCount;
    /**
     * 是否允许评论 1是，0否
     */
    private String isComment;
    /**
     * 创建的时间
     */
    private Date createTime;
}
