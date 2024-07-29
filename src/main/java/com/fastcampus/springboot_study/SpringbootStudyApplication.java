package com.fastcampus.springboot_study;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class SpringbootStudyApplication implements CommandLineRunner {
    @Autowired
    EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootStudyApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("emf = " + emf);
    }
}
