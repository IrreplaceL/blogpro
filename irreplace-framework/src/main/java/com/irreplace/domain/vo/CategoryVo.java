package com.irreplace.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Me
 * @version 1.0
 * @date 2024/7/18 12:48
 * @Description:文章分类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {

    private Long id;

    //分类名
    private String name;
}
