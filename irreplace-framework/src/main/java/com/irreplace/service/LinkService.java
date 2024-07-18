package com.irreplace.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.irreplace.domain.entity.Link;
import com.irreplace.domain.entity.domain.ResponseResult;

/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-07-18 20:36:01
 */
public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();
}
