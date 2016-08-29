package com.user.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sergey on 05.09.15.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        System.setProperty("spring.data.mongodb.uri", "mongodb://0.0.0.0:27017/micros");
        SpringApplication.run(Application.class, args);
    }
}
