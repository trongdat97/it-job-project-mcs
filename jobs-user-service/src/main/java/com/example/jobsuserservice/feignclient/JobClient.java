package com.example.jobsuserservice.feignclient;


import com.example.common.Response.BaseResponse;
import com.example.jobsservice.dto.JobDTO;
import com.example.jobsuserservice.model.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("jobs-service")
public interface JobClient {
    @GetMapping("/jobs")
    public BaseResponse<List<JobDTO>> getAllJob();
    @GetMapping("/jobs/{id}")
    public BaseResponse<JobDTO> getById(@PathVariable("id") String id);
}
