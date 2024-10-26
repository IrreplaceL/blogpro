package com.irreplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Me
 * @version 1.0
 * @date 2024/10/11 17:10
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.irreplace.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("zjj哥牛逼", "http://www.my.com",
                "lirreplace@gmail.com");
        return new ApiInfoBuilder()
                .title("后台接口文档")
                .description("依赖swagger搭建，查阅接口以及测试接口获取响应")
                .contact(contact) // 联系方式
                .version("1.1.0") // 版本
                .build();
    }
}
