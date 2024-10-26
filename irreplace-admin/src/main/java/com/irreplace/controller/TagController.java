package com.irreplace.controller;

import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**

 * @version 1.0
 * @date 2024/10/24 17:57
 * @Description:Tag的controller
 */

@RestController
@RequestMapping("/content/tag")
@Api(tags = "后台接口测试",description = "这只是一个测试")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    @ApiOperation(value = "后台接口测试",notes = "获取评论（好像）")
    public ResponseResult list(){
        return ResponseResult.successResult(tagService.list());
    }
}
