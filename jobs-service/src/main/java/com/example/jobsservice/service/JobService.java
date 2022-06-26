package com.example.jobsservice.service;

import com.example.jobsservice.dto.JobDTO;
import com.example.jobsservice.dto.request.JobCreateRequest;
import com.example.jobsservice.dto.request.JobUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface JobService {
    public JobDTO createJob(JobCreateRequest jobCreateRequest);
    public List<JobDTO> getAllJob();
    public JobDTO getJobById(String id);
    public JobDTO updateJob(String id, JobUpdateRequest jobUpdateRequest);
    public void deleteJob(String id);
    public List<JobDTO> searchJob(String name);
 //   public List<JobDTO> searchByTitle(String name);
    public List<JobDTO> getJobDeleted();
    List<JobDTO> findJobByUserName(String username);
}
