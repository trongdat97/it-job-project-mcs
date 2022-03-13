package com.example.jobsservice.repository;

import com.example.jobsservice.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job,String> {
//    List<Job> findByTitleContaining(String title);
//    List<Job> findByCompanyJob(String jobNameOrCompanyName);


}
