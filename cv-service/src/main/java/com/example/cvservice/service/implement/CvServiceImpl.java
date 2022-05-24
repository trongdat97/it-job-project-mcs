package com.example.cvservice.service.implement;

import com.example.common.Response.BaseResponse;
import com.example.cvservice.dto.CvDTO;
import com.example.cvservice.dto.UserDTO;
import com.example.cvservice.dto.request.CvCreateRequest;
import com.example.cvservice.dto.request.CvUpdateRequest;
import com.example.cvservice.feignclient.UserClient;
import com.example.cvservice.model.CV;
import com.example.cvservice.repository.CvRepository;
import com.example.cvservice.service.CvService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class CvServiceImpl implements CvService {

    @Autowired
    CvRepository cvRepository;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public CvDTO createCV(CvCreateRequest cvCreateRequest) {
        CV cv = new CV();
        CvDTO cvDTO;
        cv = modelMapper.map(cvCreateRequest,CV.class);
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
    public CvDTO getCvById(String id) {
        CvDTO cvDTO = new CvDTO();
        Optional<CV> cvOptional = cvRepository.findById(id);
        CV cv = cvOptional.get();
        cvDTO = modelMapper.map(cv,CvDTO.class);
        return cvDTO;
    }

    @Override
    public List<CvDTO> getCVbyIdCompany(String id) {
        List<CV> cvOptional = cvRepository.findByIdJob(id);
        Type listType = new TypeToken<List<CV>>() {}.getType();
        List<CvDTO> cvDTOs = modelMapper.map(cvOptional,listType);
        return cvDTOs;
    }

    @Override
    public CvDTO updateCV(String id, CvUpdateRequest cvUpdateRequest) {
        CvDTO cvDTO = new CvDTO();
        Optional<CV> cvOptional = cvRepository.findById(id);
        if(cvOptional.isPresent()){
            CV newCv = cvOptional.get();
            newCv.setName(cvUpdateRequest.getName());
            newCv.setUrl(cvUpdateRequest.getUrl());
            newCv.setIdJob(cvUpdateRequest.getIdJob());
            newCv.setActivate(cvUpdateRequest.getActivate());
            cvRepository.save(newCv);
            cvDTO = modelMapper.map(newCv,CvDTO.class);
            return cvDTO;
        }
        return null;
    }

    @Override
    public void deleteCV(String id) {
        Optional<CV> cvOptional = cvRepository.findById(id);
        CV cv = cvOptional.get();
        cvRepository.delete(cv);
    }

    @Override
    public void setDefaultCV(String id) {
        Optional<CV> cvOptional = cvRepository.findById(id);
        CV cv = cvOptional.get();

    }

}
