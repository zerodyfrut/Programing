package com.shopping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ShopConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //모든 경로에 대해 CORS 설정 적용
                .allowedOrigins("http://localhost:5173", "http://localhost:3000") 
                //프론트 개발 서버 주소 (예: Vite 기본 5173, React 기본 3000)에서 오는 요청 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                //허용할 HTTP 메서드 지정
                .allowedHeaders("*"); //모든 헤더 허용
    }

}
