package com.irreplace;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.irreplace.mapper")
public class IrreplaceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(IrreplaceAdminApplication.class, args);
    }
}