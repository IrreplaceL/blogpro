package com.irreplace.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irreplace.domain.entity.User;
import com.irreplace.mapper.UserMapper;
import com.irreplace.service.UserService;
import org.springframework.stereotype.Service;
/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-07-21 22:45:35
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
