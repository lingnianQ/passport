package com.syt.passport.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginInfoVO implements Serializable {
    /**
     * 数据id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（密文）
     */
    private String password;

    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;

    /**
     * 管理员的权限列表
     */
    private List<String> permissions;
}
