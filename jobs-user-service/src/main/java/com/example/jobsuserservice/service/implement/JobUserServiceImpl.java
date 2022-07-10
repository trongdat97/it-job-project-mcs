package com.example.jobsuserservice.service.implement;

import com.example.common.Response.BaseResponse;
import com.example.jobsservice.dto.JobDTO;
import com.example.jobsservice.model.Job;
import com.example.jobsuserservice.dto.UserDTO;
import com.example.jobsuserservice.dto.request.ApplyJobForm;
import com.example.jobsuserservice.dto.response.FileDBResponse;
import com.example.jobsuserservice.feignclient.NewCvClient;
import com.example.jobsuserservice.model.JobUser;
import com.example.jobsuserservice.model.JobUserDTO;
import com.example.jobsuserservice.repository.JobUserRepository;
import com.example.jobsuserservice.service.CvUserService;
import com.example.jobsuserservice.service.JobUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.util.Length;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobUserServiceImpl implements JobUserService {

    @Autowired
    CvUserService cvUserService;

    @Autowired
    NewCvClient newCvClient;

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
        Type listType = new TypeToken<List<JobUserDTO>>() {}.getType(); // Sai type
        List<JobUserDTO> jobUserDTOs = modelMapper.map(jobUser, listType);

        return  jobUserDTOs;
    }
    public FileDBResponse getCvUserById2(String id) {
        BaseResponse<FileDBResponse> res = newCvClient.getFiles(id);
        FileDBResponse fileDBResponse = res.getData();
        return fileDBResponse;
    }

    @Override
    public List<FileDBResponse> getInfoCvApplyJob(String id) {
        List<FileDBResponse> listInfoCV = new ArrayList<>();

        List<JobUserDTO> jobUserDTOS = getCvApplyJob(id);
//        for (int i = 0; i <= jobUserDTOS.size(); i++) {
//
//        }
        int i = 0;
//        while (i < jobUserDTOS.size()){
//            System.out.println("aaaaaa");
//            JobUserDTO jobUserDTO = jobUserDTOS.get(i);
//            JobUser we = modelMapper.map(jobUserDTO,JobUser.class);
//            System.out.println(we.getUsername());
//        }

//        Type listType = new TypeToken<List<JobUserDTO>>() {}.getType();
//        List<JobUser> newjobUserDTOs = modelMapper.map(jobUserDTOS, listType);

        for ( JobUserDTO s : jobUserDTOS) {
//            Type listType = new TypeToken<List<JobUserDTO>>() {}.getType();
//            List<JobUser> newjobUserDTOs = modelMapper.map(jobUserDTOS, listType);

            String idcv = s.getCvId();
            FileDBResponse fileDBResponse = getCvUserById2(idcv);
            listInfoCV.add(fileDBResponse);
        }
        return listInfoCV;
    }


//    @Override
//    public List<FileDBResponse> getCvById(String id){
//       return  null;
//    }


    @Autowired
    public void setJobUserRepository (JobUserRepository jobUserRepository){
        this.jobUserRepository = jobUserRepository;
    }
}
