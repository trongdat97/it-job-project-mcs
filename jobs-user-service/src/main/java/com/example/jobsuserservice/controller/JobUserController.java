package com.example.jobsuserservice.controller;


import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import com.example.jobsuserservice.dto.JobUserDTO;
import com.example.jobsuserservice.model.Job;
import com.example.jobsuserservice.service.JobUserService;
import com.example.jobsuserservice.service.implement.JobUserServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class JobUserController {
    @Autowired
    private JobUserService jobUserService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JobUserController.class);

    public BaseResponse defaultCall(){
        return new ResponseData("hello");
    }


    @GetMapping("/show")
    @HystrixCommand(fallbackMethod = "defaultCall")
    public BaseResponse showJob(){
        try {
            List<JobUserDTO> jobUserDTOS = jobUserService.getAllJob();
            if(jobUserDTOS == null){
                return new ResponseEmpty();
            }
            return new ResponseData(jobUserDTOS);
        }catch (Exception e){
            return new ResponseError("Error "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/show/{id}")
    public  BaseResponse getJobById(@PathVariable("id") String id){
        try {
            JobUserDTO jobUserDTO = jobUserService.getJobById(id);
            if(jobUserDTO == null){
                return new ResponseEmpty();
            }
            return new ResponseData(jobUserDTO);
        }catch (Exception e){
            return new ResponseError("error" + e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
