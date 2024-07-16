package com.irreplace.controller;

import com.irreplace.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

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
    @GetMapping("/111")
    public Object test(){
        articleService.list()

            return "11111";
    }
}
