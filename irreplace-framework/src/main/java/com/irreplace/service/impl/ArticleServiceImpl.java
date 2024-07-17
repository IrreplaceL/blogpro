package com.irreplace.service.impl;

import ch.qos.logback.core.net.SyslogConstants;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irreplace.constants.SystemConstants;
import com.irreplace.domain.entity.Article;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.domain.vo.HotArticleVo;
import com.irreplace.mapper.ArticleMapper;
import com.irreplace.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/16 15:07
 * @Description:
 */


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


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

        //使用Bean拷贝
        List<HotArticleVo> articleVos = new ArrayList<>();
        for (Article article:articles) {
            HotArticleVo vo = new HotArticleVo();
            BeanUtils.copyProperties(article,vo);
            articleVos.add(vo);
        }
        return ResponseResult.successResult(articleVos);
    }
}
