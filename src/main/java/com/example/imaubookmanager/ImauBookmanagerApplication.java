package com.example.imaubookmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.imaubookmanager.dao")
public class ImauBookmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImauBookmanagerApplication.class, args);
    }

}
