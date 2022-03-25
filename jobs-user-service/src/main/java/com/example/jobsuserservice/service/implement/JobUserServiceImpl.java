package com.example.jobsuserservice.service.implement;

import com.example.common.Response.BaseResponse;
import com.example.jobsuserservice.dto.JobUserDTO;
import com.example.jobsuserservice.feignclient.JobClient;
import com.example.jobsuserservice.model.Job;
import com.example.jobsuserservice.model.JobDTO;
import com.example.jobsuserservice.service.JobUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class JobUserServiceImpl implements JobUserService {
    @Autowired
    JobClient jobClient;

    ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<JobUserDTO> getAllJob() {
        List<JobUserDTO> jobUserDTOs;
        BaseResponse<List<JobDTO>> res = jobClient.getAllJob();
        List<JobDTO> jobs = res.getData();
        Type listType = new TypeToken<List<JobUserDTO>>() {}.getType();
        jobUserDTOs = modelMapper.map(jobs,listType);
        return jobUserDTOs;
    }

    @Override
    public JobUserDTO getJobById(String id) {
        BaseResponse<JobDTO> res = jobClient.getById(id);
        JobDTO job = res.getData();
        JobUserDTO jobUserDTO;
        jobUserDTO = modelMapper.map(job,JobUserDTO.class);
        return jobUserDTO;
    }

}
