package com.example.jobsuserservice.service;

import com.example.common.Response.BaseResponse;
import com.example.jobsuserservice.dto.CvDTO;

import javax.servlet.http.HttpServletRequest;

public interface CvUserService {
    public BaseResponse getCvByIdUser(HttpServletRequest request);
}
