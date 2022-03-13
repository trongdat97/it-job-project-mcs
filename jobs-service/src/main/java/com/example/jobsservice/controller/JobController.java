package com.example.jobsservice.controller;


import com.example.jobsservice.model.Job;
import com.example.jobsservice.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class JobController {
    @Autowired
    JobRepository jobRepository;

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJob(@RequestParam(required = false) String title){
        try{
            List<Job> jobs = new ArrayList<>();
            if(title == null){
                jobRepository.findAll().forEach(jobs::add);
            }
            if(jobs.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(jobs,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") String id){
        Optional<Job> jobData = jobRepository.findById(id);

        if(jobData.isPresent()){
            return new ResponseEntity<>(jobData.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        try {
            Job _job = jobRepository.save(job);
            return new ResponseEntity<>(_job,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id") String id, @RequestBody Job job){
        Optional<Job> jobData = jobRepository.findById(id);
        if(jobData.isPresent()){
            Job _job = jobData.get();
            if(job.getCompanyAddress() !=null) {
                _job.setCompanyAddress(job.getCompanyAddress());
            }
            if(job.getCompanyLogo() !=null) {
                _job.setCompanyLogo(job.getCompanyLogo());
            }
            if(job.getCompanyName() !=null) {
                _job.setCompanyName(job.getCompanyName());
            }
            if(job.getJobDetail() !=null) {
                _job.setJobDetail(job.getJobDetail());
            }
            if(job.getJobName() !=null){
                _job.setJobName(job.getJobName());
            }
            if(job.getTimeExpired() !=null){
                _job.setTimeExpired(job.getTimeExpired());
            }
            return new ResponseEntity<>(jobRepository.save(_job),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable("id") String id){
        try{
            jobRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/Job/{jname}")
//    public ResponseEntity<List<Job>> search(@PathVariable("jname") String jname){
//        List<Job> jobData = jobRepository.findByCompanyJob(jname);
//        if(jobData.isEmpty()){
//            return new ResponseEntity<>(jobData,HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//    }
}
