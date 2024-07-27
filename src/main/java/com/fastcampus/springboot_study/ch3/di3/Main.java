package com.fastcampus.springboot_study.ch3.di3;


import jakarta.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
//import javax.inject.Inject;
import java.util.Arrays;
import java.util.Map;

@Component
class Car {
//    @Resource(name="engine") // byName으로 빈을 검색
//    @Resource(name="superEngine")

//    @Autowired // byType으로 빈을 검색
//    @Qualifier("superEngine") // 검색된 빈 중에서 이름이 superEngine을 주입

    @Inject  // @Autowired와 거의 같음. required=false
    Engine engine;
    //    @Autowired
    @Resource
    Door door;

//    public Car() {}
//
//    @Autowired
//    public Car(Engine[] engine, Door door) {
//        this.engine = engine;
//        this.door = door;
//    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

@Component
class Engine {}

//@Component("superEngine")
//@Component
//class SuperEngine extends Engine {}

@Component
class TubroEngine extends Engine {}

@Component
class Door {}
public class Main {
    public static void main(String[] args) {
        // AC를 생성 - AC의 설정파일은 AppConfig.class 자바설정
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        Car car = (Car)ac.getBean("car"); // byName 객체(빈)을 조회
        System.out.println("car = " + car);

        Map<String,String> env = System.getenv();
        System.out.println("enc = "+env);
    }
}

