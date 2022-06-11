package com.example.jobsservice.controller;


import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import com.example.jobsservice.dto.JobDTO;
import com.example.jobsservice.dto.request.JobCreateRequest;
import com.example.jobsservice.dto.request.JobUpdateRequest;

import com.example.jobsservice.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping("/jobs")
    public BaseResponse<List<JobDTO>> getAllJob(@RequestParam(required = false) String title){
//        try{
//            List<Job> jobs = new ArrayList<>();
//            if(title == null){
//                jobServiceImpl.getAllJob().forEach(jobs::add);
//            }
////            else{
////                jobServiceImpl.searchByTitle(title).forEach(jobs::add);
////            }
//            if(jobs.isEmpty()){
//                return new ResponseData(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseData(jobs);
//        }catch (Exception e){
//            return new ResponseData(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return null;
        try {
            List<JobDTO> jobDTOs = jobService.getAllJob();
            if(jobDTOs.isEmpty()){
                return new ResponseEmpty();
            }
            return new ResponseData<>(jobDTOs);
        }catch (Exception e){
            return new ResponseError("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/jobs/{id}")
    public BaseResponse<JobDTO> getJobById(@PathVariable("id") String id){
        try{
            JobDTO jobData = jobService.getJobById(id);
            if(jobData == null){
                return new ResponseEmpty();
            }
            return new ResponseData(jobData);
        }catch (Exception e){
            return new ResponseError("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/jobs")
    public BaseResponse createJob(@RequestBody JobCreateRequest jobCreateRequest){
        try {
            JobDTO newJob = jobService.createJob(jobCreateRequest);
            if(newJob == null){
                return new ResponseEmpty();
            }
            return new ResponseData(newJob);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseError("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/jobs/{id}")
    public BaseResponse updateJob(@PathVariable("id") String id, @RequestBody JobUpdateRequest jobUpdateRequest){
        try {
            JobDTO data = jobService.updateJob(id,jobUpdateRequest);
            if(data == null){
                return new ResponseEmpty();
            }
            return new ResponseData(data);
        }catch (Exception e){
            return new ResponseError("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/jobsdel/{id}")
//    @RequestMapping(value = "/jobs/{id}", method = {RequestMethod.DELETE,RequestMethod.GET})
    public BaseResponse deleteJob(@PathVariable("id") String id){
        try{
            jobService.deleteJob(id);
            return new ResponseData(id);
        }catch (Exception e){
            return new ResponseError("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/jobssearch/{name}")
    public BaseResponse search(@PathVariable("name") String name){
        try{
            List<JobDTO> jobs = jobService.searchJob(name);
            if(jobs.isEmpty()){
                return new ResponseEmpty();
            }
            return new ResponseData<>(jobs);
        }catch (Exception e){
            return new ResponseError("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
