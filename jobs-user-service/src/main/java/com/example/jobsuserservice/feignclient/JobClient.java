package com.example.jobsuserservice.feignclient;


import com.example.jobsuserservice.model.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("jobs-service")
public interface JobClient {
    @GetMapping("/jobs")
    public List<Job> getAllJob();
    @GetMapping("/jobs/{id}")
    public Job getById(@PathVariable("id") String id);
}
