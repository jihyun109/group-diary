package com.example.demo.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정을 적용
                .allowedOrigins("*") // 허용할 Origin 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드 설정
                .allowedHeaders("*")
                .allowCredentials(true); // 인증정보를 포함할지 여부 설정
    }

}
