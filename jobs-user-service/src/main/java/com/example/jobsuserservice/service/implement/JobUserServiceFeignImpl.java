package com.example.jobsuserservice.service.implement;

import com.example.common.Response.BaseResponse;
import com.example.jobsuserservice.dto.JobUserFeignDTO;
import com.example.jobsuserservice.feignclient.JobClient;
import com.example.jobsuserservice.dto.response.JobDTO;
import com.example.jobsuserservice.service.JobUserServiceFeign;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class JobUserServiceFeignImpl implements JobUserServiceFeign {
    @Autowired
    JobClient jobClient;

    ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<JobUserFeignDTO> getAllJob() {
        List<JobUserFeignDTO> jobUserFeignDTOS;
        BaseResponse<List<JobDTO>> res = jobClient.getAllJob();
        List<JobDTO> jobs = res.getData();
        Type listType = new TypeToken<List<JobUserFeignDTO>>() {}.getType();
        jobUserFeignDTOS = modelMapper.map(jobs,listType);
        return jobUserFeignDTOS;
    }

    @Override
    public JobUserFeignDTO getJobById(String id) {
        BaseResponse<JobDTO> res = jobClient.getById(id);
        JobDTO job = res.getData();
        JobUserFeignDTO jobUserFeignDTO;
        jobUserFeignDTO = modelMapper.map(job, JobUserFeignDTO.class);
        return jobUserFeignDTO;
    }

}
