package com.syt.passport.filter;

import com.syt.passport.ex.ServiceException;
import com.syt.passport.security.LoginPrincipal;
import com.syt.passport.web.ServiceCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sytsnb@gmail.com
 * @Date 2022 2022/10/13 12:00
 */

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${passport.jwt.secret-key}")
    String secretKey;

    public static final int JWT_MIN_LENGTH = 100;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 清除SecurityContext中原有的数据（认证信息）
        SecurityContextHolder.clearContext();

        //尝试获取客户端请求时可能携带的JWT
        String jwt = request.getHeader("Authorization");
        log.debug("接收到JWT数据：{}", jwt);

        //判断是否 接收到 有效的jwt数据
        if (!StringUtils.hasText(jwt) || jwt.length() < JWT_MIN_LENGTH) {
            //直接放行
            String message = "未读取到有效的jwt数据,将直接放行";
            log.warn(message);
            filterChain.doFilter(request, response);
            return;
        }

        log.debug("尝试解析JWT...");
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        log.debug("从JWT中解析得到数据：id={}", id);
        log.debug("从JWT中解析得到数据：username={}", username);

        // 将根据从JWT中解析得到的数据来创建认证信息
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("root权限标识");
        authorities.add(authority);
        LoginPrincipal loginPrincipal = new LoginPrincipal();
        loginPrincipal.setId(id);
        loginPrincipal.setUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginPrincipal, null, authorities
        );

        //将认证信息存储到SecurityContext中
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);

        //放行
        filterChain.doFilter(request, response);
    }
}
