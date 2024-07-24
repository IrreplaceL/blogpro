package com.irreplace.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irreplace.domain.entity.User;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.domain.vo.UserInfoVo;
import com.irreplace.mapper.UserMapper;
import com.irreplace.service.UserService;
import com.irreplace.utils.BeanCopyUtils;
import com.irreplace.utils.SecurityUtils;
import org.springframework.stereotype.Service;
/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-07-21 22:45:35
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public ResponseResult userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成UserInfo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.successResult(userInfoVo);
    }
}
