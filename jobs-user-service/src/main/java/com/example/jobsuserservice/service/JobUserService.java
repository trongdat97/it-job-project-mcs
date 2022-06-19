package com.example.jobsuserservice.service;

import com.example.jobsuserservice.dto.request.ApplyJobForm;
import com.example.jobsuserservice.model.JobUserDTO;



public interface JobUserService{
    JobUserDTO AppLyJob(ApplyJobForm applyJobForm);
}
