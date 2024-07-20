package com.irreplace.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.irreplace.domain.entity.LoginUser;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.enums.AppHttpCodeEnum;
import com.irreplace.utils.JwtUtil;
import com.irreplace.utils.RedisCache;
import com.irreplace.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/20 15:36
 * @Description:登陆校验的过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //请求头中的token
        String token = httpServletRequest.getHeader("token");
        //判断请求头是否为空
        if(!StringUtils.hasText(token)){
            //说明该接口不需要登陆，直接放行
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //解析获取userId
        Claims claims = null;
        try {
             claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //token超时或者非法
            //响应需重新登陆
            ResponseResult responseResult =
                    ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(responseResult));
            return;
        }
        String userId = claims.getSubject();
        //从redis中获取登陆信息
        LoginUser loginUser = redisCache.getCacheObject("bloglogin:"+userId);
        //如果获取不到
        if(Objects.isNull(loginUser)) {
            //说明登录过期 提示重新登录
            ResponseResult result =
                    ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
            return;
        }
            //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //过滤器放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
