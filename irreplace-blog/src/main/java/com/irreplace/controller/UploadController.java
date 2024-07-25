package com.irreplace.controller;

import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/25 18:20
 * @Description:用户上传
 */
@RestController
@RequestMapping
public class UploadController {

    @Autowired
    private UploadService uploadService;
    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img) throws IOException {
        return uploadService.uploadImg(img);
    }

}
