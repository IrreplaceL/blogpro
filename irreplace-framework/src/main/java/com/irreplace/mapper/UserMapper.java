package com.irreplace.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.irreplace.domain.entity.User;
import org.springframework.stereotype.Component;

/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-19 17:32:51
 */
@Component
public interface UserMapper extends BaseMapper<User> {
}
