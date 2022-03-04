package com.example.jobservice.service;

import com.example.jobservice.model.Job;

import java.util.List;


public interface JobService {


    Job saveJob(Job job);

    List<Job> ftechJobList();

    Job updateJob(Job job,Long jobId);

    void deleteJob(Long jobId);

    // List<Job> searchJobList(String jobName);


}
