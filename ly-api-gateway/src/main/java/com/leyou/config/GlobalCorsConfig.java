package com.leyou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        //1、添加cors配置信息
        CorsConfiguration configuration = new CorsConfiguration();
        //1)、允许跨域的域，不要写*，否则cookie无法使用
        configuration.addAllowedOrigin("http://manage.leyou.com");
        configuration.addAllowedOrigin("http://api.leyou.com");
        //2)、是否允许发送cookie信息
        configuration.setAllowCredentials(true);
        //3)、允许的请求方式
        configuration.addAllowedMethod(HttpMethod.OPTIONS);
        configuration.addAllowedMethod(HttpMethod.HEAD);
        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedMethod(HttpMethod.PUT);
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.DELETE);
        configuration.addAllowedMethod(HttpMethod.PATCH);
        //4)、允许的头信息,实际情况需要根据自定义的头进行配置
        configuration.addAllowedHeader("*");
        //5)、允许的时长
        configuration.setMaxAge(3600L);
        //2、添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",configuration);
        //3、返回新的CrosFilter
        return new CorsFilter(configurationSource);
    }
}
