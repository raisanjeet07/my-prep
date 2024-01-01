package com.san.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MainExecutor {
    public static void main(String[] args){
        SpringApplication.run(MainExecutor.class, args);
    }
}
