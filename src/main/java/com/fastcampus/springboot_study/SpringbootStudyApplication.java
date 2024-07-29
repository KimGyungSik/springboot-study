package com.fastcampus.springboot_study;

import com.fastcampus.springboot_study.ch4.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Date;

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
        EntityManager em = emf.createEntityManager();
        System.out.println("em = " + em);
        EntityTransaction tx = em.getTransaction();

        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("Lee");
        user.setEmail("aaa@aaa.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());

        tx.begin(); // 트랜잭션 시작
        // 1. 저장
        em.persist(user); // user엔티티를 em에 영속화(저장)
        em.persist(user); // 같은 user엔티티를 여러번 저장해도 한번만 insert

        // 2. 변경
        user.setPassword("2321"); // PersistenceContext가 변경감지, update
        user.setEmail("bbb@bbbb.com");
        tx.commit(); // 트랜잭션 종료(DB에 반영)

        // 3. 조회
        User user2 = em.find(User.class,"aaa"); // em에 있으면 DB조회 안함
        System.out.println("user == user2 = " + (user == user2));
        User user3 = em.find(User.class,"bbb"); // em에 없으면 DB조회
        System.out.println("user3 = " + user3); //null , DB에 없음

        // 4. 삭제
        tx.begin(); // 위에 tx와 다른 tx임
        em.remove(user); //em에서 user엔티티를 삭제, commit을 안하면 em에서만 삭제됨
        tx.commit(); // commit에 의해 DB에 반영

    }
}
