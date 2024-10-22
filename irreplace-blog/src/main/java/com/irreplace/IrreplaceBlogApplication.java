package com.irreplace;

import com.irreplace.utils.AliOSSProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.irreplace.mapper")
@EnableScheduling
@EnableConfigurationProperties(AliOSSProperties.class)
public class IrreplaceBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(IrreplaceBlogApplication.class,args);
    }
}