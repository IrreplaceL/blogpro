package com.irreplace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.irreplace.domain.entity.Article;
import com.irreplace.domain.entity.domain.ResponseResult;

public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
