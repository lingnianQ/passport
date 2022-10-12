package com.syt.passport.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 管理员登录的DTO类
 * @author sytsnb@gmail.com
 * @Date 2022 2022/10/12 14:31
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLoginDTO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（原文）
     */
    private String password;

}

