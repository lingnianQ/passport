package com.syt.passport.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author sytsnb@gmail.com
 * @Date 2022 2022/10/11 15:07
 */
@Slf4j
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("创建@Bean方法定义的对象：PasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 【配置白名单】
        // 在配置路径时，星号是通配符
        // 1个星号只能匹配任何文件夹或文件的名称，但不能跨多个层级
        // 例如：/*/test.js，可以匹配到 /a/test.js 和 /b/test.js，但不可以匹配到 /a/b/test.js
        // 2个连续的星号可以匹配若干个文件夹的层级
        // 例如：/**/test.js，可以匹配 /a/test.js 和 /b/test.js 和 /a/b/test.js
        String[] urls = {
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",
                "/v2/api-docs"
        };

        http.authorizeRequests() // 对请求执行认证与授权
                .antMatchers(urls) // 匹配某些请求路径
                .permitAll() // （对此前匹配的请求路径）不需要通过认证即允许访问
                .anyRequest() // 除以上配置过的请求路径以外的所有请求路径
                .authenticated(); // 要求是已经通过认证的
//        http.formLogin();//开启表单验证,未认证时,重定向到登录表单

    }
}
