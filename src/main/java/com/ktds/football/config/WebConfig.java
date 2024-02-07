package com.ktds.football.config;

import com.ktds.football.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 가로채는 경로 설정 가능
        registry.addInterceptor(new AuthInterceptor())
                // 모든 Path에 대해서 가로챌것이다.
                // .addPathPatterns("/sample") // /sample경로에 대해서만 가로챌것이다.
                .excludePathPatterns("/user/*") // /sample 경로에 대해서는 Interceptor 가로채지 않을것이다.
                .addPathPatterns("/*", "/board/**", "/request/**");
    }
}
