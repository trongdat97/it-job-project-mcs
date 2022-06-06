package com.example.accountservice.feignclient;

import com.example.accountservice.model.JobDTO;
import com.example.common.Response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("jobs-service")
public interface JobClient {
    @GetMapping("/jobs")
    public BaseResponse<List<JobDTO>> getAllJob();
    @GetMapping("/jobs/{id}")
    public BaseResponse getJobById(@PathVariable("id") String id);
}
