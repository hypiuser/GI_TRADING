package com.lsjgitrading.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); //访问地址
        corsConfiguration.addAllowedHeader("*"); //请求头
        corsConfiguration.addAllowedMethod("*"); //请求方法
        source.registerCorsConfiguration("/**", corsConfiguration);//配置接口跨域设置
        return new CorsFilter(source);
    }
}