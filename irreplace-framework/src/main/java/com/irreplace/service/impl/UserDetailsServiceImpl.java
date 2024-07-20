package com.irreplace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.irreplace.domain.entity.LoginUser;
import com.irreplace.domain.entity.User;
import com.irreplace.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/19 17:22
 * @Description:重写UserDetailsService
 * loadUserByUsername最终返回到BlogLoginServiceImpl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        //判断用户是否存在
        if(Objects.isNull(user))
            throw new RuntimeException("用户不存在");
        //返回用户信息
            return new LoginUser(user);
        //TODO 查询权限信息封装


    }
}
