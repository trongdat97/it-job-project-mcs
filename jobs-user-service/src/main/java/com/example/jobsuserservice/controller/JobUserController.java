package com.example.jobsuserservice.controller;


import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import com.example.jobsuserservice.dto.request.ApplyJobForm;
import com.example.jobsuserservice.dto.response.CvDTO;
import com.example.jobsuserservice.dto.JobUserFeignDTO;
import com.example.jobsuserservice.model.JobUserDTO;
import com.example.jobsuserservice.service.CvUserService;
import com.example.jobsuserservice.service.JobUserService;
import com.example.jobsuserservice.service.JobUserServiceFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class JobUserController {
    @Autowired
    private JobUserServiceFeign jobUserServiceFeign;

    @Autowired
    private CvUserService cvUserService;

//    @Autowired
//    private JobUserService jobUserService;

    private static final Logger LOGGER = LoggerFactory.getLogger(JobUserController.class);

    public BaseResponse defaultCall(){
        return new ResponseData("hello");
    }


    @GetMapping("/show")
    @HystrixCommand(fallbackMethod = "defaultCall")
    public BaseResponse showJob(){
        try {
            List<JobUserFeignDTO> jobUserFeignDTOS = jobUserServiceFeign.getAllJob();
            if(jobUserFeignDTOS == null){
                return new ResponseEmpty();
            }
            return new ResponseData(jobUserFeignDTOS);
        }catch (Exception e){
            return new ResponseError("Error "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/show/{id}")
    public  BaseResponse getJobById(@PathVariable("id") String id){
        try {
            JobUserFeignDTO jobUserFeignDTO = jobUserServiceFeign.getJobById(id);
            if(jobUserFeignDTO == null){
                return new ResponseEmpty();
            }
            return new ResponseData(jobUserFeignDTO);
        }catch (Exception e){
            return new ResponseError("error" + e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/cvs")
    public  BaseResponse getCVByIdUser(HttpServletRequest request){
        try {
            List<CvDTO> cvDTOs = cvUserService.getCvByIdUser(request);
            if(cvDTOs == null){
                return new ResponseEmpty();
            }
            return new ResponseData(cvDTOs);
        }catch (Exception e){
            return new ResponseError("error" + e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
//    @PostMapping("/applyjob")
//    public BaseResponse applyJob(@Valid @RequestBody ApplyJobForm applyJobForm){
//        try {
//            JobUserDTO jobUserDTO = jobUserService.AppLyJob(applyJobForm);
//            if(jobUserDTO == null){
//                return new ResponseEmpty();
//            }
//            return new ResponseData(jobUserDTO);
//        }catch (Exception e){
//            return new ResponseError("errpr"+e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
