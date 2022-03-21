package com.example.cvservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class CvServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvServiceApplication.class, args);
    }

}
