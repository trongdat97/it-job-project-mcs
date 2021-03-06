package com.example.jobsuserservice.service;

import com.example.jobsuserservice.dto.request.ApplyJobForm;
import com.example.jobsuserservice.dto.response.FileDBResponse;
import com.example.jobsuserservice.dto.response.FileDBResponse2;
import com.example.jobsuserservice.model.JobUserDTO;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public interface JobUserService{
    JobUserDTO AppLyJob(ApplyJobForm applyJobForm);
    JobUserDTO acceptCv(Long id);
    JobUserDTO rejectCv(Long id);

    JobUserDTO getJobUserById(Long id);
    List<JobUserDTO> getCvApplyJob(String jobid);
//    List<FileDBResponse> getCvById(String id);
    List<FileDBResponse2> getInfoCvApplyJob(String id) throws IOException, JSONException;
}
