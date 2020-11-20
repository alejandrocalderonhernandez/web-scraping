package com.alejandro.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InfoApplication {

    public static void main(String[] args) {
        System.out.println("Version 2.1");
        SpringApplication.run(InfoApplication.class, args);
    }

}
