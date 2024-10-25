package com.irreplace.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.irreplace.domain.entity.Tag;
import com.irreplace.mapper.TagMapper;
import com.irreplace.service.TagService;
import org.springframework.stereotype.Service;
/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2024-10-24 17:25:48
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}
