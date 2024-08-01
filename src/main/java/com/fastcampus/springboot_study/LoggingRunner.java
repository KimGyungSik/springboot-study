package com.fastcampus.springboot_study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
@Service
public class LoggingRunner implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(LoggingRunner.class);


    public void run(ApplicationArguments args) throws Exception {
        logger.trace("TRACE 로그 메세지");
        logger.trace("DEBUG 로그 메세지");
        logger.trace("INFO 로그 메세지");
        logger.trace("WARN 로그 메세지");
        logger.trace("ERROR 로그 메세지");
    }
}
