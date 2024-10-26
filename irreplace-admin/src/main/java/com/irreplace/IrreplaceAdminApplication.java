package com.irreplace;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.irreplace.mapper")
@EnableSwagger2
public class IrreplaceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(IrreplaceAdminApplication.class, args);
    }
}