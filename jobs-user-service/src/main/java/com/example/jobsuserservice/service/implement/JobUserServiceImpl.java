package com.example.jobsuserservice.service.implement;

import com.example.common.Response.BaseResponse;
import com.example.jobsservice.dto.JobDTO;
import com.example.jobsservice.model.Job;
import com.example.jobsuserservice.dto.UserDTO;
import com.example.jobsuserservice.dto.request.ApplyJobForm;
import com.example.jobsuserservice.dto.response.CvMatch;
import com.example.jobsuserservice.dto.response.FileDBResponse;
import com.example.jobsuserservice.dto.response.FileDBResponse2;
import com.example.jobsuserservice.dto.response.ResponseFlask;
import com.example.jobsuserservice.feignclient.NewCvClient;
import com.example.jobsuserservice.model.JobUser;
import com.example.jobsuserservice.model.JobUserDTO;
import com.example.jobsuserservice.repository.JobUserRepository;
import com.example.jobsuserservice.service.CvUserService;
import com.example.jobsuserservice.service.JobUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
import sun.security.util.Length;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.DataInput;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobUserServiceImpl implements JobUserService {
    ObjectMapper objectMapper = new ObjectMapper();
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
    public List<FileDBResponse2> getInfoCvApplyJob(String id) throws IOException, JSONException {
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
        String data = dataCvApply(id,listInfoCV);

        String a =  postDT(data);
//        System.out.println(a);
        JSONObject jsonObj = new JSONObject(a);
        JSONObject jsonObj1 =  jsonObj.getJSONObject("cv1");
        JSONObject jsonObj2 =  jsonObj.getJSONObject("cv2");
        JSONObject jsonObj3 =  jsonObj.getJSONObject("cv3");
//        JSONObject jsonObj4 =  jsonObj.getJSONObject("cv4");
//        JSONObject jsonObj5 =  jsonObj.getJSONObject("cv5");
//        JSONObject jsonObj6 =  jsonObj.getJSONObject("cv6");
//        System.out.println(jsonObj.toString());

//        ResponseFlask responseFlask = objectMapper.readValue(jsonObj.toString(), ResponseFlask.class);
        CvMatch cv1 = objectMapper.readValue(jsonObj1.toString(),CvMatch.class);
        CvMatch cv2 = objectMapper.readValue(jsonObj2.toString(),CvMatch.class);
        CvMatch cv3 = objectMapper.readValue(jsonObj3.toString(),CvMatch.class);
//        CvMatch cv4 = objectMapper.readValue(jsonObj3.toString(),CvMatch.class);
//        CvMatch cv5 = objectMapper.readValue(jsonObj3.toString(),CvMatch.class);
//        CvMatch cv6 = objectMapper.readValue(jsonObj3.toString(),CvMatch.class);
        ArrayList<CvMatch> cvs = new ArrayList<CvMatch>();

        cvs.add(cv1);
        cvs.add(cv2);
        cvs.add(cv3);
//        cvs.add(cv4);
//        cvs.add(cv5);
//        cvs.add(cv6);
        Integer s = cvs.size();


        Type listType = new TypeToken<List<FileDBResponse2>>() {}.getType(); // Sai type
        List<FileDBResponse2> cvs1 = modelMapper.map(cvs, listType);
        ; // Sai type
        List<FileDBResponse2> fileDBResponse2 = modelMapper.map(listInfoCV, listType);

        ArrayList<FileDBResponse2> listf2 = new ArrayList<FileDBResponse2>();

        for (FileDBResponse2 fi: fileDBResponse2
             ) {        FileDBResponse2 fe = new FileDBResponse2();
            for (FileDBResponse2 fa: cvs1
                 ) {
                    if(fa.getId().equals(fi.getId())){

                        //fe.setCvname(fi.getCvname());
                        if(fa.getCvname() == null){
                            fa.setCvname(fi.getCvname());
                        }
                        //fe.setName(fi.getName());
                        if(fa.getName() == null){
                            fa.setName(fi.getName());
                        }
                        //fe.setUrl(fi.getUrl());
                        if(fa.getUrl() == null){
                            fa.setUrl(fi.getUrl());
                        }
                        //fe.setType(fi.getType());
                        if(fa.getType() == null){
                            fa.setType(fi.getType());
                        }
                        //fe.setJobId(fi.getJobId());
                        if(fa.getJobId() == null){
                            fa.setJobId(fi.getJobId());
                        }
                        //fe.setUsername(fi.getUsername());
                        if(fa.getUsername() == null){
                            fa.setUsername(fi.getUsername());
                        }
                        //fe.setSkillcv(fi.getSkillcv());
                        if(fa.getCvskill() == null){
                            fa.setCvskill(fi.getCvskill());
                        }
                        //fe.setSkilljd(fi.getSkilljd());
                        if(fa.getJdskill() == null){
                            fa.setJdskill(fi.getJdskill());
                        }
                        //fe.setMatch(fi.getMatch());
                        if(fa.getMatch() == null){
                            fa.setMatch(fi.getMatch());
                        }
                        fe = fa;
                        listf2.add(fe);


                    }
            }

        }
//        for (int j = 0; j < fileDBResponse2.size(); j++) {
//            for (int k = 0; k < cvs; k++) {
//
//            }
//
//        }
//        List<FileDBResponse2> combinedList = Stream.of(cvs1,fileDBResponse2)
//                .flatMap(x -> x.stream())
//                .collect(Collectors.toList());

        return listf2;
    }

    public String dataCvApply(String idjob, List<FileDBResponse> fileDBResponses){
        String data = "";
        data = data + idjob + ",";
        for (FileDBResponse s: fileDBResponses){
            String idcv = s.getId() + ",";
            data += idcv;
        }
        return data;
    }
    public String postDT(String data) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        ObjectMapper mapper = new ObjectMapper();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();


        body.add("data", data);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = "http://192.168.42.17:5100/updata";
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
            String responseBody = response.getBody();
//            ResponseFlask responseFlask = modelMapper.map(objects,ResponseFlask.class);
//            System.out.println(responseFlask.getCV1().getIdcv());
            return responseBody;
        }catch (Exception e){
            return ("Response code: " + e.toString());
        }

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
