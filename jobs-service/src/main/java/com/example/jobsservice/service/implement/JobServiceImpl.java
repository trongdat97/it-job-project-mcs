package com.example.jobsservice.service.implement;

import com.example.jobsservice.dto.JobDTO;
import com.example.jobsservice.dto.request.JobCreateRequest;
import com.example.jobsservice.dto.request.JobUpdateRequest;
import com.example.jobsservice.model.Job;
import com.example.jobsservice.repository.JobRepository;
import com.example.jobsservice.service.JobService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public JobDTO createJob(JobCreateRequest jobCreateRequest){
        Job job ;
        JobDTO jobDTO ;
        job = modelMapper.map(jobCreateRequest,Job.class);
        jobRepository.save(job);
        jobDTO = modelMapper.map(job,JobDTO.class);
        return jobDTO;
    }
    @Override
    public List<JobDTO> searchJob(String name){
        List<Job> jobs = jobRepository.findByJobName2(name);
        Type listType = new TypeToken<List<JobDTO>>() {}.getType();
        List<JobDTO> jobDTOs = modelMapper.map(jobs, listType);
        return jobDTOs;
    }
    @Override
    public List<JobDTO> getAllJob(){
        List<Job> jobs = jobRepository.findAll();
        Type listType = new TypeToken<List<JobDTO>>() {}.getType();
        List<JobDTO> jobDTOs = modelMapper.map(jobs, listType);
        return jobDTOs;
    }
    @Override
    public JobDTO getJobById(String id){
        JobDTO jobDTO = new JobDTO();
        Optional<Job> jobData = jobRepository.findById(id);
        Job newJob = jobData.get();
        jobDTO = modelMapper.map(newJob,JobDTO.class);
        return  jobDTO;
    }

    @Override
    public JobDTO updateJob(String id, JobUpdateRequest jobUpdateRequest) {
        JobDTO jobDTO = new JobDTO();
        Optional<Job> jobData = jobRepository.findById(id);
        if(jobData.isPresent()){
            Job newJob = jobData.get();
            newJob.setJobName(jobUpdateRequest.getJobName());
            newJob.setCompanyAddress(jobUpdateRequest.getCompanyAddress());
            newJob.setCompanyLogo(jobUpdateRequest.getCompanyLogo());
            newJob.setJobDetail(jobUpdateRequest.getJobDetail());
            newJob.setTimeExpired(jobUpdateRequest.getTimeExpired());
            newJob.setCompanyName(jobUpdateRequest.getCompanyName());
            jobRepository.save(newJob);
            jobDTO = modelMapper.map(newJob,JobDTO.class);
            return jobDTO;
        }
        return null;
    }

    @Override
    public void deleteJob(String id) {
        Optional<Job> job = jobRepository.findById(id);
        Job jobData = job.get();
        jobRepository.delete(jobData);
    }


}
