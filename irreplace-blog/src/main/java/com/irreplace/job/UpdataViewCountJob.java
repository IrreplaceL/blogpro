package com.irreplace.job;

import com.irreplace.domain.entity.Article;
import com.irreplace.service.ArticleService;
import com.irreplace.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Me
 * @version 1.0
 * @date 2024/10/22 17:27
 * @Description:定时将redis的数据发送到mysql
 */

@Component
public class UpdataViewCountJob {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/55 * * * * ?")
    public  void updataViewCount(){
    //获取redis中的浏览量
        Map<String,Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");
        List<Article> articleList = viewCountMap.entrySet()
                .stream().map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());

        //更新到数据库中
        articleService.updateBatchById(articleList);
    }

}















