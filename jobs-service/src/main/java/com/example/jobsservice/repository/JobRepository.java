package com.example.jobsservice.repository;

import com.example.jobsservice.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobRepository extends MongoRepository<Job,String>{
//    List<Job> findByTitleContaining(String title);
//    List<Job> findByCompanyJob(String jobNameOrCompanyName);

//    @Query(value="{'id' : $0}", delete = true)
//    public void deleteById (String id);
}
