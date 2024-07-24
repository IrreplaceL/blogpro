package com.irreplace.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.irreplace.domain.entity.User;
import com.irreplace.domain.entity.domain.ResponseResult;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2024-07-21 22:42:02
 */

public interface UserService extends IService<User> {
    ResponseResult userInfo();
}
