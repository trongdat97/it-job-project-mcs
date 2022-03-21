package com.example.jobsuserservice.service;

import com.example.jobsuserservice.dto.JobUserDTO;
import com.example.jobsuserservice.model.Job;

import java.util.List;

public interface JobUserService {
    public List<JobUserDTO> getAllJob();
    public JobUserDTO getJobById(String id);
}
