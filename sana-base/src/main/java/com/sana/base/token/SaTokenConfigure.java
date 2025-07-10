package com.sana.base.token;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LON
 * @create 2025/4/15 16:05
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/sys/captcha/getCaptcha",
                        "/sys/captcha/enabled",
                        "/sys/auth/login",
                        "/sys/auth/token",
                        "/sys/auth/logout",
                        "/sys/auth/send/code",
                        "/upload/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/swagger/**",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/doc.html");
    }
    // Sa-Token 整合 jwt (Simple 简单模式)
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

}
