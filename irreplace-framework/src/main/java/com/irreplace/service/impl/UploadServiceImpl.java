package com.irreplace.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.irreplace.domain.entity.domain.ResponseResult;
import com.irreplace.service.UploadService;
import com.irreplace.utils.AliOSSUtils;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/25 18:21
 * @Description:
 */

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    /**
     * 上传头像
     *
     * @param img
     * @return
     */
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Override
    public ResponseResult uploadImg(@NonNull MultipartFile img) throws IOException {
        if (ObjectUtils.isNull()) {
            //这个拦截似乎没有用，因为全都被注解@NonNull拦截了
            throw new RuntimeException("上传的图片不能为空！");

        } else {
            String upload = aliOSSUtils.upload(img);
            log.info("上传的图片链接：{}",upload);
            return ResponseResult.successResult(upload);
        }
    }
}