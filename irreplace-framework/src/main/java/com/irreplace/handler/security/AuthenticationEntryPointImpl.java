package com.irreplace.handler.security;

import com.alibaba.fastjson.JSON;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.enums.AppHttpCodeEnum;
import com.irreplace.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/21 16:15
 * @Description:授权接口实现类,未通过授权或认证阶段
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException athenticationException) throws IOException, ServletException {
        athenticationException.printStackTrace();
        //InsufficientAuthenticationException 没限访问，需登陆
        //BadCredentialsException  //  用户名或密码错误
        ResponseResult result = null;
        if(athenticationException instanceof BadCredentialsException){
            result=ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(),athenticationException.getMessage());
        }else if(athenticationException instanceof InsufficientAuthenticationException){
            //athenticationException.getMessage(),默认的错误信息
            result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }else {
            result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"认证或授权失败");
        }
        //响应给前端
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
    }
}
