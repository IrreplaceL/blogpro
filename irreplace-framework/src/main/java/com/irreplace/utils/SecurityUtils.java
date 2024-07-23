package com.irreplace.utils;

import com.irreplace.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/23 21:48
 * @Description:调用登陆验证，从token中获取用户信息
 */

public class SecurityUtils {
    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        return (LoginUser) getAuthentication().getPrincipal();
    }
    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public static Boolean isAdmin(){
        Long id = getLoginUser().getUser().getId();
        return id != null && 1L == id;
    }
    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }

}
