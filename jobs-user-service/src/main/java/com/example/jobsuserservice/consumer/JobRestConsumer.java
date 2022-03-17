package com.example.jobsuserservice.consumer;


import com.example.jobsuserservice.model.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("jobs-service")
public interface JobRestConsumer {
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJob();
    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id);
    @PostMapping("/jobs")
    public ResponseEntity<?> createJob();
    @PostMapping("/jobs/{id}")
    public ResponseEntity<?> updateJob(@PathVariable("id") String id);
    @DeleteMapping("/jobs/{id}")
    public  ResponseEntity<?> deleteJob(@PathVariable("id") String id);
}
