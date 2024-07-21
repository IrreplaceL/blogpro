package com.irreplace.service.impl;

import com.irreplace.domain.entity.LoginUser;
import com.irreplace.domain.entity.User;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.domain.vo.BlogUserLoginVo;
import com.irreplace.domain.vo.UserInfoVo;
import com.irreplace.service.BlogLoginService;
import com.irreplace.utils.BeanCopyUtils;
import com.irreplace.utils.JwtUtil;
import com.irreplace.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/19 16:54
 * @Description:用户自定义登陆接口实现类
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {
   @Autowired
    private RedisCache redisCache;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //由UserDetailsServiceImpl.loadUserByUsername返回
        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);  //随后经过一系列，传到UserDetailsServiceImpl
        //判断是否认证通过
        if(Objects.isNull(authentication))
            throw new RuntimeException("用户名或密码不存在");
        //获取userId，生成token
        //将authentication强转为LoginUser
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //将用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);
        //把token 和userInfo封装，vo，返回
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt,userInfoVo);
        return ResponseResult.successResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {
        //获取token，解析获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userId
        Long userId = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.successResult();
    }
}
