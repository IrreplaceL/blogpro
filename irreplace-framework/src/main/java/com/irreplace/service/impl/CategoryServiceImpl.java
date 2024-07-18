package com.irreplace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irreplace.constants.SystemConstants;
import com.irreplace.domain.entity.Article;
import com.irreplace.domain.entity.Category;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.domain.vo.CategoryVo;
import com.irreplace.mapper.CategoryMapper;
import com.irreplace.service.ArticleService;
import com.irreplace.service.CategoryService;
import com.irreplace.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/18 12:19
 * @Description:分类
 */
@Service
@Slf4j
public class CategoryServiceImpl  extends ServiceImpl<CategoryMapper,Category> implements CategoryService {
   @Autowired
   private ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        //先查询文章，返回正常显示的文章
        LambdaQueryWrapper<Article> lambdaWrapper = new LambdaQueryWrapper<>();
        lambdaWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(lambdaWrapper);

        //筛选出正常显示的文章id号,并且去除重复
        Set<Long> collectIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        //查询分类表,stream过滤出状态为正常的文章分类
        List<Category> categories = listByIds(collectIds);
        categories = categories.stream()
                .filter(category -> SystemConstants.Category_STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
       //log.info(categoryVos.toString());
        return ResponseResult.successResult(categoryVos);
    }
}
