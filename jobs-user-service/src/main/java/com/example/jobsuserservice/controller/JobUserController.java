package com.example.jobsuserservice.controller;


import com.example.jobsuserservice.model.Job;
import com.example.jobsuserservice.service.implement.JobUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class JobUserController {
    @Autowired
    private JobUserServiceImpl jobUserServiceImpl;


    @GetMapping("/show")
    public List<Job> showJob(){
        return jobUserServiceImpl.getAllJob();
    }
    @GetMapping("/show/{id}")
    public  Job getJobById(@PathVariable("id") String id){
        return jobUserServiceImpl.getJobById(id);
    }


}
