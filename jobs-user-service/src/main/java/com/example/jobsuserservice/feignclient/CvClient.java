package com.example.jobsuserservice.feignclient;

import com.example.common.Response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("cv-service")
public interface CvClient {
    @GetMapping("/cv")
    public BaseResponse getAllCv();
    @GetMapping("/cv/{id}")
    public BaseResponse getCV(@PathVariable("id") String id);

}
