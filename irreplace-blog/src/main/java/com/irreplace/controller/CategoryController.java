package com.irreplace.controller;

import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/18 11:03
 * @Description:分类查询
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/getCategoryList")
    public ResponseResult  getCategoryList(){
        return categoryService.getCategoryList();
    }
}
