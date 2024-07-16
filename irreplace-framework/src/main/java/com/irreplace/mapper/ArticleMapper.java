package com.irreplace.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.irreplace.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/16 13:59
 * @Description:
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
