package com.syt.passport.security;

import com.syt.passport.mapper.AdminMapper;
import com.syt.passport.pojo.vo.AdminLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        if (loginInfo == null) {
            log.debug("此用户名【{}】不存在，即将抛出异常", s);
            String message = "登录失败，用户名不存在！";
            throw new BadCredentialsException(message);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("root权限标识");
        authorities.add(authority);

        AdminDetails adminDetails = new AdminDetails(
                loginInfo.getUsername(), loginInfo.getPassword(),
                loginInfo.getEnable() == 1, authorities
        );
        adminDetails.setId(loginInfo.getId());

/*        UserDetails userDetails = User.builder()
                .username(loginInfo.getUsername())
                .password(loginInfo.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .disabled(loginInfo.getEnable() == 0)
                .authorities("root权限标识")
                .build();*/

        log.debug("即将向Spring Security返回UserDetails对象：{}", adminDetails);
        return adminDetails;
    }
}
