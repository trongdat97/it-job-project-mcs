package com.example.cvservice.service;

import com.example.cvservice.dto.CvDTO;
import com.example.cvservice.dto.request.CvCreateRequest;
import com.example.cvservice.dto.request.CvUpdateRequest;

import java.util.List;

public interface CvService {
    public CvDTO createCV(CvCreateRequest cvCreateRequest);
    public List<CvDTO> getAllCV();
    public List<CvDTO> getCVbyIdCompany(String id);
    public CvDTO updateCV(String id,CvUpdateRequest cvUpdateRequest);
    public void deleteCV(String id);
}