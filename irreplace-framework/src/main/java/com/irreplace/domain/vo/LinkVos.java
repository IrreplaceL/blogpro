package com.irreplace.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/18 20:47
 * @Description:友情链接返回字段
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVos {
    private Long id;
    private String name;
    private String logo;
    private String description;
    //网站地址
    private String address;
}
