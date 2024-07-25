package com.irreplace.utils;


import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Me
 * @version 1.0
 * @date 2024/1/6 21:57
 * @Description:为阿里云工具类的属性注入注解
 */

@Data
@Component
@ConfigurationProperties(prefix ="aliyun.oos")
public class AliOSSProperties {

    private String endpoint;

    private String accessKeyId ;

    private String accessKeySecret ;

    private String bucketName;
}
