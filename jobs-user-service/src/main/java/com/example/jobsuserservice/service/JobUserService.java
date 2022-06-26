package com.example.jobsuserservice.service;

import com.example.jobsuserservice.dto.request.ApplyJobForm;
import com.example.jobsuserservice.model.JobUserDTO;



public interface JobUserService{
    JobUserDTO AppLyJob(ApplyJobForm applyJobForm);
    JobUserDTO acceptCv(Long id);
    JobUserDTO rejectCv(Long id);

    JobUserDTO getJobUserById(Long id);
}
