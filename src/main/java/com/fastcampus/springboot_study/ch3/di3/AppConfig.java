package com.fastcampus.springboot_study.ch3.di3;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan //    @Component가 붙은 클래스들을 Bean으로 등록
public class AppConfig {

//    @Bean
////    @Scope("singleton")
//    Car car() {
//        return new Car();
//    }
//    @Bean
//    @Scope("prototype")
//    Engine engine() {
//        return new Engine();
//    }
//
//    @Bean
////    @Scope("singleton")
//    Door door() {
//        return new Door();
//    }

}

