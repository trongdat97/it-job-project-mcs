package com.example.jobsuserservice.service.implement;

import com.example.jobsuserservice.feignclient.JobClient;
import com.example.jobsuserservice.model.Job;
import com.example.jobsuserservice.service.JobUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobUserServiceImpl implements JobUserService {
    @Autowired
    JobClient jobClient;

    @Override
    public List<Job> getAllJob() {
        return jobClient.getAllJob();
    }

    @Override
    public Job getJobById(String id) {
        return jobClient.getById(id);
    }

}
