package com.irreplace.controller;

import com.irreplace.domain.entity.User;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.enums.AppHttpCodeEnum;
import com.irreplace.exception.SystemException;
import com.irreplace.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/19 16:50
 * @Description:自定义登陆接口
 */
@RestController
@Api(tags = "后台登陆接口"  )
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/user/login")
    @ApiOperation(value = "后台登陆")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
        //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }
}