package com.example.jobsuserservice.service;

import com.example.jobsuserservice.dto.JobUserFeignDTO;

import java.util.List;

public interface JobUserServiceFeign {
    public List<JobUserFeignDTO> getAllJob();
    public JobUserFeignDTO getJobById(String id);
}
