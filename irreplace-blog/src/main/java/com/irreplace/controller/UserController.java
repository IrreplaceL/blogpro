package com.irreplace.controller;

import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/24 15:16
 * @Description:用户信息请求
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

}
