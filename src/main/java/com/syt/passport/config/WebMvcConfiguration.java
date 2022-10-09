package com.syt.passport.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/28 16:30
 */
@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    public WebMvcConfiguration() {
        log.info("加载配置类：WebMvcConfiguration");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
