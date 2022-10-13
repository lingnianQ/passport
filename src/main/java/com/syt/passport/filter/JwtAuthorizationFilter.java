package com.syt.passport.filter;

import com.syt.passport.ex.ServiceException;
import com.syt.passport.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sytsnb@gmail.com
 * @Date 2022 2022/10/13 12:00
 */

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");

        log.debug("接收到JWT数据：{}", jwt);

//        if (jwt == null) {
//            String message = "jwt不存在";
//            log.warn(message);
//            throw new ServiceException(ServiceCode.ERR_UNAUTHORIZED_DISABLED, message);
//        }
        //放行
        filterChain.doFilter(request, response);
    }
}
