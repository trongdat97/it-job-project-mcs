package com.example.jobservice.service;

import com.example.jobservice.model.Job;
import com.example.jobservice.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Job saveJob(Job job){
        return jobRepository.save(job);
    }
    @Override
    public List<Job> ftechJobList(){
        return (List<Job>)jobRepository.findAll();
    }

    @Override
    public Job updateJob(Job job, Long jobId){
        Job jobDB = jobRepository.findById(jobId).get();
        if(Objects.nonNull(job.getJobName())&&!"".equalsIgnoreCase(job.getJobName())){
            jobDB.setJobName(job.getJobName());
        }
        if(Objects.nonNull(job.getTimeExpired())&&!"".equalsIgnoreCase(String.valueOf(job.getTimeExpired()))){
            jobDB.setTimeExpired(job.getTimeExpired());
        }
        if(Objects.nonNull(job.getJobDetail())&&!"".equalsIgnoreCase(job.getJobDetail())){
            jobDB.setJobDetail((job.getJobDetail()));
        }
        if(Objects.nonNull(job.getCompanyName())&&!"".equalsIgnoreCase(job.getCompanyName())){
            jobDB.setCompanyName(job.getCompanyName());
        }
        if(Objects.nonNull(job.getCompanyLogo())&&!"".equalsIgnoreCase(job.getCompanyLogo())){
            jobDB.setCompanyLogo(job.getCompanyLogo());
        }
        if(Objects.nonNull(job.getCompanyAddress())&&!"".equalsIgnoreCase(job.getCompanyAddress())){
            jobDB.setCompanyAddress(job.getCompanyAddress());
        }
        return jobRepository.save(jobDB);
    }

    @Override
    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }


    // @Override
    // public List<Job> searchJobList(String jobNameorCompanyName) {
    //     List<Job> result = new ArrayList<Job>();
    //     List<Job> lJob = (List<Job>)jobRepository.findAll();
    //     for (Job job: lJob
    //          ) { if (job.getJobName() == jobNameorCompanyName || job.getCompanyName() == jobNameorCompanyName ){
    //              result.add(job);
    //     }

    //     }
    //     return result;
    // }

}