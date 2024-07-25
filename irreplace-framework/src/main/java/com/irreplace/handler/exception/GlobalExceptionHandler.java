package com.irreplace.handler.exception;

import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.enums.AppHttpCodeEnum;
import com.irreplace.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/21 16:55
 * @Description:全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
    //打印异常信息
        log.info("出现了异常，{}",e.getMessage(),e);
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e){
        //打印异常信息
        log.info("出现了异常：{}",e.getMessage(),e);
        return  ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }
}
