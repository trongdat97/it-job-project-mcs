package com.example.cvservice.service;

import com.example.cvservice.dto.CvDTO;
import com.example.cvservice.dto.UserDTO;
import com.example.cvservice.dto.request.CvCreateRequest;
import com.example.cvservice.dto.request.CvUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface CvService {
    public CvDTO createCV(CvCreateRequest cvCreateRequest, HttpServletRequest request);
    public List<CvDTO> getAllCV();
    public CvDTO getCvById(Long id);
    public List<CvDTO> getCVbyIdCompany(Long id);
    public CvDTO updateCV(Long id,CvUpdateRequest cvUpdateRequest);
    public void deleteCV(Long id);
    public void setDefaultCV(Long id);
    public List<CvDTO> getCvByIdUser(HttpServletRequest request);
    public List<CvDTO> getCvByIdUser2(Long id);
    public CvDTO newCreateCv(String model, MultipartFile file) throws IOException;
    public List<CvDTO> getCvOfUser(String username);

}
