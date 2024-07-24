package com.fastcampus.springboot_study.ch2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 설정파일이라는 것을 알려주기 위한 애너테이션
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor())
                .addPathPatterns("/**") // 인터셉터를 적용할 대상
                .excludePathPatterns("/css/**", "/js/**"); // 인터셉터 적용 제외대상
    }
}
