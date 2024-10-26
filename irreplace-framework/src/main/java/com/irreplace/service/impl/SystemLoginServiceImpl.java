package com.irreplace.service.impl;

import com.irreplace.domain.entity.LoginUser;
import com.irreplace.domain.entity.User;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.domain.vo.BlogUserLoginVo;
import com.irreplace.domain.vo.UserInfoVo;
import com.irreplace.service.BlogLoginService;
import com.irreplace.service.LoginService;
import com.irreplace.utils.BeanCopyUtils;
import com.irreplace.utils.JwtUtil;
import com.irreplace.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/19 16:54
 * @Description:用户自定义登陆接口实现类
 */
@Service
public class SystemLoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate =
                authenticationManager.authenticate(authenticationToken);
//判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
//获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
//把用户信息存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
//把token封装 返回
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.successResult(map);
    }
}