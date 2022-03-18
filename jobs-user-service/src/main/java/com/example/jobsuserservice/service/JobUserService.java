package com.example.jobsuserservice.service;

import com.example.jobsuserservice.model.Job;

import java.util.List;

public interface JobUserService {
    public List<Job> getAllJob();
    public Job getJobById(String id);
}
