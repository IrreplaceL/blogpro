package com.irreplace.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Me
 * @version 1.0
 * @date 2024/10/11 16:26
 * @Description:测试CommandLineRunner，实现项目启动预处理
 */

@Slf4j
@Component
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("程序初始化");
    }
}