package com.example.cvservice.service.implement;

import com.example.common.Response.BaseResponse;
import com.example.cvservice.dto.CvDTO;
import com.example.cvservice.dto.ModelDTO;
import com.example.cvservice.dto.UserDTO;
import com.example.cvservice.dto.request.CvCreateRequest;
import com.example.cvservice.dto.request.CvUpdateRequest;
import com.example.cvservice.feignclient.UserClient;
import com.example.cvservice.model.CV;
import com.example.cvservice.model.FileDB;
import com.example.cvservice.repository.CvRepository;
import com.example.cvservice.repository.FileDBRepository;
import com.example.cvservice.service.CvService;
import com.example.cvservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class CvServiceImpl implements CvService {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    CvRepository cvRepository;


    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    UserService userService;


    @Override
    public CvDTO createCV(CvCreateRequest cvCreateRequest, HttpServletRequest request) {
        CV cv = new CV();
        CvDTO cvDTO;
        cv = modelMapper.map(cvCreateRequest,CV.class);
        UserDTO userDTO = userService.getAllInfoUser(request);
        cv.setUserId(userService.getIdUser(userDTO));
        cvRepository.save(cv);
        cvDTO = modelMapper.map(cv,CvDTO.class);
        return cvDTO;
    }

    @Override
    public List<CvDTO> getAllCV() {
        List<CV> cvs = cvRepository.findAll();

        Type listType = new TypeToken<List<CvDTO>>() {}.getType();
        List<CvDTO> cvDTOs = modelMapper.map(cvs,listType);
        return cvDTOs;
    }

    @Override
    public CvDTO getCvById(Long id) {
        CvDTO cvDTO = new CvDTO();
        Optional<CV> cvOptional = cvRepository.findById(id);
        CV cv = cvOptional.get();
        cvDTO = modelMapper.map(cv,CvDTO.class);
        return cvDTO;
    }

    @Override
    public List<CvDTO> getCVbyIdCompany(Long id) {
        List<CV> cvOptional = cvRepository.findByJobId(id);
        Type listType = new TypeToken<List<CV>>() {}.getType();
        List<CvDTO> cvDTOs = modelMapper.map(cvOptional,listType);
        return cvDTOs;
    }

    @Override
    public CvDTO updateCV(Long id, CvUpdateRequest cvUpdateRequest) {
        CvDTO cvDTO = new CvDTO();
        Optional<CV> cvOptional = cvRepository.findById(id);
        if(cvOptional.isPresent()){
            CV newCv = cvOptional.get();
            newCv.setName(cvUpdateRequest.getName());
            newCv.setUrl(cvUpdateRequest.getUrl());
            newCv.setJobId(cvUpdateRequest.getIdJob());
            newCv.setActivate(cvUpdateRequest.getActivate());
            cvRepository.save(newCv);
            cvDTO = modelMapper.map(newCv,CvDTO.class);
            return cvDTO;
        }
        return null;
    }

    @Override
    public void deleteCV(Long id) {
        Optional<CV> cvOptional = cvRepository.findById(id);
        CV cv = cvOptional.get();
        cvRepository.delete(cv);
    }

    @Override
    public void setDefaultCV(Long id) {
        Optional<CV> cvOptional = cvRepository.findById(id);
        CV cv = cvOptional.get();

    }

    @Override
    public List<CvDTO> getCvByIdUser(HttpServletRequest request) {
        UserDTO userDTO = userService.getAllInfoUser(request);
        Long id = userDTO.getId();
        List<CV> cvs = cvRepository.loadCVById(id);
        Type listType = new TypeToken<List<CvDTO>>() {}.getType();
        List<CvDTO> cvDTOs = modelMapper.map(cvs,listType);
        return cvDTOs;
    }
    @Override
    public List<CvDTO> getCvByIdUser2(Long id){
        List<CV> cvs = cvRepository.loadCVById(id);
        Type listType = new TypeToken<List<CvDTO>>() {}.getType();
        List<CvDTO> cvDTOs = modelMapper.map(cvs,listType);
        return cvDTOs;
    }
    @Override
    public CvDTO newCreateCv(String model, MultipartFile file) throws IOException{
        ModelDTO modelDTO = mapper.readValue(model, ModelDTO.class);
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = modelDTO.getName();
        FileDB image = new FileDB(fileName, file.getContentType(), file.getBytes(),modelDTO.getUsername(),modelDTO.getJobId());
        System.out.println(modelDTO.getName());
        fileDBRepository.save(image);
        CV cv = new CV();
        CvDTO cvDTO;
//        cv = modelMapper.map(modelDTO,CV.class);
        cv.setType(file.getContentType());
        cv.setData(file.getBytes());
        cv.setUserId(modelDTO.getUserId());
        cv.setJobId(modelDTO.getJobId());
        cv.setName(modelDTO.getName());
        cv.setUsername(modelDTO.getUsername());
        cvRepository.save(cv);
        cvDTO = modelMapper.map(cv,CvDTO.class);
//        ModelDTO modelDTO = mapper.readValue(model, ModelDTO.class);
//        CvDTO cvDTO;
//        CV cv = new CV();
//        cv.setCvfile(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
//        cv.setUserId(modelDTO.getUserId());
//        cv.setJobId(modelDTO.getJobId());
//        cv.setName(modelDTO.getName());
//        cv.setUsername(modelDTO.getUsername());
//        cvRepository.save(cv);
//        cvDTO = modelMapper.map(cv,CvDTO.class);
        return cvDTO;
    }

    @Override
    public List<CvDTO> getCvOfUser(String username){
        List<CV> cvs = cvRepository.loadCVOfUser(username);
        Type listType = new TypeToken<List<CvDTO>>() {}.getType();
        List<CvDTO> cvDTOs = modelMapper.map(cvs,listType);
        return  cvDTOs;
    }
}
