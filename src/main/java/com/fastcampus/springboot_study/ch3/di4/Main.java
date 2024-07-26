package com.fastcampus.springboot_study.ch3.di4;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;


class Car {
    public String toString() {
        return "Car{}";
    }
}

class SportsCar extends Car {
    public String toString() {
        return "SportsCar{}";
    }
}

class SportsCar2 extends Car {
    public String toString() {
        return "SportsCar2{}";
    }
}
@Component
@Conditional(TrueCondition.class)
class Engine {
    public String toString() {
        return "Engine{}";
    }
}

@Component
@Conditional(OSCondition.class)
class Door {
    public String toString() {
        return "Door{}";
    }
}

class TrueCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}

class OSCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
//        System.out.println("System.getProperties() = " + System.getProperties());
//        return env.getProperty("sun.desktop").equals("windows");
        return env.getProperty("mode").equals("dev");
    }
}
@Configuration // 자바 Bean 설정파일이라는 뜻
@Import({Config1.class, Config2.class})
//@Import(MyImportSelector.class)
//@EnableMyAutoConfiguration("test2")
class MainConfig { @Bean Car car() { return new Car();} }
class Config1 { @Bean Car sportsCar() { return new SportsCar();}}
class Config2 { @Bean Car sportsCar() { return new SportsCar2();}}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Import(MyImportSelector.class)
@interface EnableMyAutoConfiguration {
    String value() default "";
}

class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attr = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(EnableMyAutoConfiguration.class.getName(), false));
//        String mode = "test";
        String mode = attr.getString("value");
        if(mode.equals("test"))
            return new String[]{Config1.class.getName()};
        else
            return new String[]{Config2.class.getName()};
    }
}
@EnableConfigurationProperties({MyProperties.class})
@Configuration //@SpringBootConfiguration  // @Configuration하고 동일
//@EnableAutoConfiguration
@ComponentScan
public class Main implements CommandLineRunner {
    @Autowired
    MyProperties prop;  // 인스턴스 변수. 자동 주입

    @Autowired
    ApplicationContext ac;

    @Override
    public void run(String... args) throws Exception { // 인스턴스 메서드
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        Arrays.sort(beanDefinitionNames); // 빈 목록이 담긴 배열을 정렬
        Arrays.stream(beanDefinitionNames) // 배열을 스트림을 변환
                .filter(b->!b.startsWith("org")) // org로 시작하는 빈의 이름을 제외
                .forEach(System.out::println); // 스트림의 요소를 하나씩 꺼내서 출력.

        //        MyProperties prop = ac.getBean(MyProperties.class);  // 수동 빈 검색해서 주입
        System.out.println("prop.getEmail() = " + prop.getEmail());
        System.out.println("prop.getDomain() = " + prop.getDomain());
    }
    public static void main(String[] args) { // static메서드
        ApplicationContext ac = SpringApplication.run(Main.class, args);
//        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class, Config1.class, Config2.class); // 자바 설정을 이용하는 AC
//        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class); // 자바 설정을 이용하는 AC
//        System.out.println("ac = " + ac);


//        System.out.println("ac.getBean(\"sportsCar\") = " + ac.getBean("sportsCar"));
//        MyProperties prop = ac.getBean(MyProperties.class);  // 수동 빈 검색해서 주입
//        System.out.println("prop.getDomain() = " + prop.getDomain());
//        System.out.println("prop.getEmail() = " + prop.getEmail());

    }

    @Bean
    MyBean myBean() { return new MyBean();}


}

class MyBean {}

