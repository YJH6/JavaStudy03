package com.yjh.messageborad;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan("com.yjh.mapper")
public class MessageboradApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageboradApplication.class, args);
    }
}
