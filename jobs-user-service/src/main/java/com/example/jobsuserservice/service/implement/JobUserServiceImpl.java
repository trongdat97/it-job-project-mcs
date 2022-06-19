package com.example.jobsuserservice.service.implement;

import com.example.jobsservice.dto.JobDTO;
import com.example.jobsservice.model.Job;
import com.example.jobsuserservice.dto.request.ApplyJobForm;
import com.example.jobsuserservice.model.JobUser;
import com.example.jobsuserservice.model.JobUserDTO;
import com.example.jobsuserservice.repository.JobUserRepository;
import com.example.jobsuserservice.service.JobUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//
//@Service
//public class JobUserServiceImpl implements JobUserService {
//
//    @Autowired
//    JobUserRepository jobUserRepository;
//
//    ModelMapper modelMapper = new ModelMapper();
//
//    @Override
//    public JobUserDTO AppLyJob(ApplyJobForm applyJobForm) {
//        JobUser jobUser;
//        JobUserDTO jobUserDTO;
//        jobUser = modelMapper.map(applyJobForm,JobUser.class);
//        jobUserRepository.save(jobUser);
//        jobUserDTO = modelMapper.map(jobUser,JobUserDTO.class);
//        return jobUserDTO;
//    }
//}
