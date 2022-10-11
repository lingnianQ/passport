package com.syt.passport.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 创建用户
 *
 * @author sytsnb@gmail.com
 * @Date 2022 2022/10/11 17:45
 */
@Slf4j
@Service
public class UserDetailsServicesImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security调用了loadUserByUsername()方法，参数：{}", s);
        // 暂时使用模拟数据来处理登录认证，假设正确的用户名和密码分别是root和123456
        if ("root".equals(s)) {
            UserDetails userDetails = User.builder()
                    .username("root")
                    .password("root")
                    .accountExpired(false)
                    .accountLocked(false)
                    .disabled(false)
                    .authorities("root权限标识")
                    .build();
            log.debug("即将向Spring Security返回UserDetails对象：{}", userDetails);
            return userDetails;
        }
        return null;
    }
}
