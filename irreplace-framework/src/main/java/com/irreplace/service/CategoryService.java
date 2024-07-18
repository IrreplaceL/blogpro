package com.irreplace.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.irreplace.domain.entity.Category;
import com.irreplace.domain.entity.domain.ResponseResult;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-07-17 22:42:54
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();
}
