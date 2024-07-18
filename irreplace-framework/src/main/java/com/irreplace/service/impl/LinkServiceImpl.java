package com.irreplace.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irreplace.constants.SystemConstants;
import com.irreplace.domain.entity.Link;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.domain.vo.LinkVos;
import com.irreplace.mapper.LinkMapper;
import com.irreplace.service.LinkService;
import com.irreplace.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2024-07-18 20:36:01
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的链接，status为0
        LambdaQueryWrapper<Link> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Link::getStatus, SystemConstants.Link_STATUS_NORMAL);
        List<Link> linkList = list(lambdaQueryWrapper);
        //转换为vo
        List<LinkVos> linkVos = BeanCopyUtils.copyBeanList(linkList, LinkVos.class);
        //封装返回
        return ResponseResult.successResult(linkVos);
    }
}
