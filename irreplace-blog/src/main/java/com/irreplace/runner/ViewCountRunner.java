package com.irreplace.runner;

import com.irreplace.domain.entity.Article;
import com.irreplace.mapper.ArticleMapper;
import com.irreplace.utils.RedisCache;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Me
 * @version 1.0
 * @date 2024/10/11 18:27
 * @Description:  一个在启动时将用户浏览量发送给redis的预启动代码执行
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    //使用articleMapper的方法进行查询
    @Autowired
   private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;
    public ViewCountRunner() {
        super();
    }


    @Override
    public void run(String... args) throws Exception {
       //查询博客讯息 id viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(new Function<Article, String>() {
                    @Override
                    public String apply(Article article) {
                        return article.getId().toString();
                    }
                }, new Function<Article, Integer>() {
                    @Override
                    public Integer apply(Article article) {
                        return article.getViewCount().intValue();
                    }
                }));
        //存储到redis中
            redisCache.setCacheMap("article:viewCount", viewCountMap);

    }
}
