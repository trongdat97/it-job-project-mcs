package com.example.jobsservice.controller;


import com.example.jobsservice.dto.JobDTO;
import com.example.jobsservice.dto.request.JobCreateRequest;
import com.example.jobsservice.dto.request.JobUpdateRequest;
import com.example.jobsservice.dto.response.BaseResponse;
import com.example.jobsservice.dto.response.ResponseData;
import com.example.jobsservice.dto.response.ResponseEmpty;
import com.example.jobsservice.dto.response.ResponseError;
import com.example.jobsservice.model.Job;

import com.example.jobsservice.service.JobService;
import com.example.jobsservice.service.implement.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping("/jobs")
    public BaseResponse getAllJob(@RequestParam(required = false) String title){
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
    public ResponseEntity<Job> getJobById(@PathVariable("id") String id){
//        Optional<Job> jobData = jobServiceImpl.getJobById(id);
//
//        if(jobData.isPresent()){
//            return new ResponseEntity<>(jobData.get(), HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return null;
    }
    @PostMapping("/jobs")
    public ResponseEntity<JobDTO> createJob(@RequestBody JobCreateRequest jobCreateRequest){
        try {
            JobDTO newJob = jobService.createJob(jobCreateRequest);
            return new ResponseEntity<>(newJob,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
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
    @DeleteMapping("/jobs/{id}")
//    @RequestMapping(value = "/jobs/{id}", method = {RequestMethod.DELETE,RequestMethod.GET})
    public BaseResponse deleteJob(@PathVariable("id") String id){
        try{
            jobService.deleteJob(id);
            return new ResponseData(id);
        }catch (Exception e){
            return new ResponseError("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/jobs/{name}")
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
