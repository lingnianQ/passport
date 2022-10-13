package com.syt.passport.security;

import lombok.Data;

/**
 * 登录当事人
 * @author sytsnb@gmail.com
 * @Date 2022 2022/10/13 15:53
 */

@Data
public class LoginPrincipal {
    private Long id;
    private String username;
}
