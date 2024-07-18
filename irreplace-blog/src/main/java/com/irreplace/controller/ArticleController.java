package com.irreplace.controller;

import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Me
 * @version 1.0
 * @date 2024/7/16 15:11
 * @Description:没有东西
 */

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

//    @GetMapping("/111")
//    public String test(){
//      List<Article>  list= articleService.list();
//            return list.toString();
//    }
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        ResponseResult result = articleService.hotArticleList();
        return result;
    }
    @GetMapping("/articleList")
    private ResponseResult articleList(Integer pageNum,Integer pageSize,Long
            categoryId){
       return articleService.articleList(pageNum,pageSize,categoryId);
    }
}
