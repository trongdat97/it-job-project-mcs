package com.example.jobsuserservice.service;

import com.example.jobsuserservice.dto.response.CvDTO;
import com.example.jobsuserservice.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CvUserService {
    public List<CvDTO> getCvByIdUser(HttpServletRequest request);
    public Long getIdUser(UserDTO userDTO);
    public String getUserNameFromJWT(HttpServletRequest request);
}
