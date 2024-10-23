package com.irreplace;

import com.irreplace.utils.AliOSSProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.irreplace.mapper")
@EnableScheduling
@EnableConfigurationProperties(AliOSSProperties.class)
@EnableSwagger2
public class IrreplaceBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(IrreplaceBlogApplication.class,args);
    }
}