package com.syt.passport.security;

import com.syt.passport.mapper.AdminMapper;
import com.syt.passport.pojo.vo.AdminLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 创建用户
 * @author sytsnb@gmail.com
 * @Date 2022 2022/10/11 17:45
 */
@Slf4j
@Service
public class UserDetailsServicesImpl implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security调用了loadUserByUsername()方法，参数：{}", s);
        AdminLoginInfoVO loginInfo = adminMapper.getLoginInfoByUsername(s);
        log.debug("loginInfo查询到数据: {}", loginInfo);
        // 暂时使用模拟数据来处理登录认证，假设正确的用户名和密码分别是root和123456
        if (loginInfo != null) {
            UserDetails userDetails = User.builder()
                    .username(loginInfo.getUsername())
                    .password(loginInfo.getPassword())
                    .accountExpired(false)
                    .accountLocked(false)
                    .disabled(loginInfo.getEnable() == 0)
                    .authorities("root权限标识")
                    .build();
            log.debug("即将向Spring Security返回UserDetails对象：{}", userDetails);
            return userDetails;
        }
        log.debug("此用户不存在：{}", s);
        return null;
    }
}
