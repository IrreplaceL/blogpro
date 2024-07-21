package com.irreplace.exception;

import com.irreplace.enums.AppHttpCodeEnum;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/21 16:50
 * @Description:统一异常处理实体类
 */

public class SystemException extends RuntimeException{
    private int code;
    private String msg;
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
