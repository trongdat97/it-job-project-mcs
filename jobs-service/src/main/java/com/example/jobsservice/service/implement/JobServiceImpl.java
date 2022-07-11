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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public JobDTO createJob(JobCreateRequest jobCreateRequest) throws IOException {
        Job job ;
        JobDTO jobDTO ;
        job = modelMapper.map(jobCreateRequest,Job.class);
        jobRepository.save(job);
        postJD(job.getId(),job.getJobDetail());

        jobDTO = modelMapper.map(job,JobDTO.class);
        return jobDTO;
    }

    public String postJD(String idjob, String jd) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();



        body.add("idjob", idjob);
        body.add("jd",jd);

        System.out.println(idjob);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = "http://192.168.42.17:5100/uploadjd";
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
        }catch (Exception e){
            System.out.println("Response code: " + e.toString());
        }


        return null;
    }
//    @Override
//    public List<JobDTO> searchJob(String name){
//        Optional<Job> jobs = jobRepository.findByJobName2(name);
//        Type listType = new TypeToken<List<JobDTO>>() {}.getType();
//        List<JobDTO> jobDTOs = modelMapper.map(jobs, listType);
//        return jobDTOs;
//    }
    @Override
    public List<JobDTO> searchJob(String name){
        List<JobDTO> jobDTOs = getAllJob();
        List<JobDTO> jobDTOSSreach = new ArrayList<>();
        for (JobDTO job: jobDTOs
             ) {
            if (job.getJobName() == name){
                System.out.println(job.getJobName());
                jobDTOSSreach.add(job);
            }
        }
        return jobDTOSSreach;
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
        jobData.setDel(true);
        jobRepository.save(jobData);
    }

    @Override
    public List<JobDTO> getJobDeleted() {
        Boolean isdel = true;
        Optional<Job> job = jobRepository.getAllJobDeleted(isdel);
        Job jobData = job.get();
        return null;
    }

    @Override
    public List<JobDTO> findJobByUserName(String username){
        List<Job> jobs = jobRepository.findJobsByUsername(username);
        Type listType = new TypeToken<List<JobDTO>>() {}.getType();
        List<JobDTO> jobDTOs = modelMapper.map(jobs, listType);
        return jobDTOs;
    }


}
