package com.irreplace.service;

import com.irreplace.domain.entity.User;
import com.irreplace.domain.entity.domain.ResponseResult;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/19 16:53
 * @Description:自定义登陆
 */

public interface BlogLoginService {
    ResponseResult login(User user);
}
