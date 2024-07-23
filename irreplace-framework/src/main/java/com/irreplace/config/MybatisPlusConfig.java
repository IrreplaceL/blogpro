package com.irreplace.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/18 14:36
 * @Description:配置和注册一个 MyBatis-Plus 拦截器，用于在 MyBatis 执行 SQL 时自动拦截并实现分页功能。
 */

@Configuration
public class MybatisPlusConfig {
    /**
     * 3.4.0之后版本
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new
                MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new
                PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

}