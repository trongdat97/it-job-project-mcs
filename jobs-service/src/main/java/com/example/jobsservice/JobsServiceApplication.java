package com.example.jobsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JobsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobsServiceApplication.class, args);
    }

}
