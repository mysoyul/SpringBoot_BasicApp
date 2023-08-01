package com.basic.myspringboot.runner;

import com.basic.myspringboot.dto.AccountDto;
import com.basic.myspringboot.property.MyBootProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements ApplicationRunner {
    @Autowired
    private MyBootProperties properties;

    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;

    @Autowired
    private AccountDto accountDto;

    private Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Logger 클래스이름 = {}", logger.getClass().getName());
        logger.debug("Account = {}", accountDto);
        logger.debug("myboot.name  = {}", name);
        logger.debug("myboot.age  = {}" , age);
        logger.info("MyBootProperties getName() = {}", properties.getName());
        logger.info("MyBootProperties getAge() = {}", properties.getAge());
        logger.info("MyBootProperties getFullName() = {}", properties.getFullName());
        logger.info("VM Arguments foo : {}", args.containsOption("foo"));
        logger.info("Program Arguments bar : {}", args.containsOption("bar"));
    }
}
