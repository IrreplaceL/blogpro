package com.irreplace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irreplace.constants.SystemConstants;
import com.irreplace.domain.entity.Article;
import com.irreplace.domain.entity.Category;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.domain.vo.ArticleDetailVo;
import com.irreplace.domain.vo.ArticleListVo;
import com.irreplace.domain.vo.HotArticleVo;
import com.irreplace.domain.vo.PageVo;
import com.irreplace.mapper.ArticleMapper;
import com.irreplace.service.ArticleService;
import com.irreplace.service.CategoryService;
import com.irreplace.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/16 15:07
 * @Description:
 */


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只能查询十条，采用分页的方法
        Page<Article> page = new Page(1,10);
        page(page,queryWrapper);
        //封装响应
        List<Article> articles = page.getRecords();

//        //使用Bean拷贝
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for (Article article:articles) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }
            //利用工具类
        List<HotArticleVo> articleVos =
                BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.successResult(articleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //构建查询条件
        LambdaQueryWrapper<Article> articleWrapper  = new LambdaQueryWrapper<>();
        //如果有categoryId，需查询
        //Objects.nonNull()如果对象不为 null，则返回 true；如果对象为 null，则返回 false。
        articleWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //状态是正式发布的
        articleWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行降序
        articleWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,articleWrapper);
        //查询categoryName
        List<Article> articles = page.getRecords();
        articles.stream()
                .map(new Function<Article, Article>() {
                    @Override
                    public Article apply(Article article){
                        //获取分类id，通过分类id查询分类信息
                        Category category = categoryService.getById(article.getCategoryId());
                        String categoryName = category.getName();
                        return  article.setCategoryName(categoryName);  //此处在Article类中注解@Accessors(chain = true)
                    }
                })
                .collect(Collectors.toList());

        //简洁版stream流调用
//        articles.stream()
//        .map(article ->
//        article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
//        .collect(Collectors.toList());
        //封装查询结果
        List<ArticleListVo> articleListVos =
                BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());

        return ResponseResult.successResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //转化为vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询类名
        Category category = categoryService.getById(article.getCategoryId());
        if(category != null)
            articleDetailVo.setCategoryName(category.getName());
        //封装响应返回
        return ResponseResult.successResult(articleDetailVo);
    }
}
