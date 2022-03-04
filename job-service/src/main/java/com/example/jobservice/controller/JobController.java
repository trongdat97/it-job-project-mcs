package com.example.jobservice.controller;


import com.example.jobservice.model.Job;
import com.example.jobservice.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/jobs")
    public String saveJob(@Valid @RequestBody Job job){
        jobService.saveJob(job);
        return "add thanh cong";
    }
    @GetMapping("/jobs")
    public List<Job> ftechJobList(){
        return jobService.ftechJobList();
    }
    @PutMapping("/jobs/{id}")
    public Job updateJob(@RequestBody Job job, @PathVariable("id") Long jobId){
        return jobService.updateJob(job,jobId);
    }
    @DeleteMapping("/jobs/{id}")
    public String deleteJobById(@PathVariable("id") Long jobId){
        jobService.deleteJob(jobId);
        return "Delete Successfully";
    }
    // @GetMapping("/jobs/{jobnameorcompanyname}")
    // public List<Job> searchJob(@PathVariable("jobnameorcompanyname") String object){
    //     return jobService.searchJobList(object);
    // }

}
