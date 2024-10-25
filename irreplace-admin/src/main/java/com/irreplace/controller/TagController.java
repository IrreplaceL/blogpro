package com.irreplace.controller;

import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.service.TagService;
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
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(){
        return ResponseResult.successResult(tagService.list());
    }
}
