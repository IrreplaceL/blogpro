package com.irreplace.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @author Me
 * @version 1.0
 * @date 2024/7/20 12:53
 * @Description:
 */


@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    private String sex;
    private String email;
}