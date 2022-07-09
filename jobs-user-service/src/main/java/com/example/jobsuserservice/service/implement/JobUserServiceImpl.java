package com.example.jobsuserservice.service.implement;


import com.example.jobsservice.dto.JobDTO;
import com.example.jobsuserservice.dto.request.ApplyJobForm;
import com.example.jobsuserservice.model.JobUser;
import com.example.jobsuserservice.model.JobUserDTO;
import com.example.jobsuserservice.repository.JobUserRepository;
import com.example.jobsuserservice.service.JobUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class JobUserServiceImpl implements JobUserService {

    JobUserRepository jobUserRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public JobUserDTO AppLyJob(ApplyJobForm applyJobForm) {
        JobUser jobUser;
        JobUserDTO jobUserDTO;
        jobUser = modelMapper.map(applyJobForm,JobUser.class);
        jobUserRepository.save(jobUser);
        jobUserDTO = modelMapper.map(jobUser,JobUserDTO.class);
        return jobUserDTO;
    }
    // status 1 = pending, status 2 = accept, status 3 = reject
    @Override
    public JobUserDTO acceptCv(Long id) {
        JobUser jobUser = jobUserRepository.loadById(id);
        jobUser.setStatus(2);
        jobUserRepository.save(jobUser);
        JobUserDTO jobUserDTO = modelMapper.map(jobUser,JobUserDTO.class);
        return jobUserDTO;
    }
    // status 1 = pending, status 2 = accept, status 3 = reject
    @Override
    public JobUserDTO rejectCv(Long id) {
        JobUser jobUser = jobUserRepository.loadById(id);
        jobUser.setStatus(3);
        jobUserRepository.save(jobUser);
        JobUserDTO jobUserDTO = modelMapper.map(jobUser,JobUserDTO.class);
        return jobUserDTO;
    }

    @Override
    public JobUserDTO getJobUserById(Long id) {
        JobUser jobUser = jobUserRepository.loadById(id);
        JobUserDTO jobUserDTO = modelMapper.map(jobUser,JobUserDTO.class);
        return jobUserDTO;
    }
    @Override
    public List<JobUserDTO> getCvApplyJob(String jobid){
        List<JobUser> jobUser = jobUserRepository.getCvByJobId(jobid);
        Type listType = new TypeToken<List<JobUser>>() {}.getType();
        List<JobUserDTO> jobUserDTOs = modelMapper.map(jobUser, listType);

        return  jobUserDTOs;
    }

    @Autowired
    public void setJobUserRepository (JobUserRepository jobUserRepository){
        this.jobUserRepository = jobUserRepository;
    }


}
