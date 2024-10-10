package com.irreplace.annotation;

/**
 * @author Me
 * @version 1.0
 * @date 2024/9/29 20:13
 * @Description:日志的注解
 *
 */

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface SystemLog {
    String businessName();
}
