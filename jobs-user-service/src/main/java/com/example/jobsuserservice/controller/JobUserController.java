package com.example.jobsuserservice.controller;


import com.example.jobsuserservice.consumer.JobRestConsumer;
import com.example.jobsuserservice.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class JobUserController {
    @Autowired
    private JobRestConsumer consumer;


    @GetMapping("/show")
    public ResponseEntity<List<Job>> showJob(){
        return consumer.getAllJob();
    }

}
