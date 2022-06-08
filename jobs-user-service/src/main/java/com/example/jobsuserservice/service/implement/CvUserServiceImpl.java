package com.example.jobsuserservice.service.implement;

import com.example.jobsuserservice.dto.CvDTO;
import com.example.jobsuserservice.feignclient.CvClient;
import com.example.jobsuserservice.service.CvUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CvUserServiceImpl  implements CvUserService {
    @Autowired
    CvClient cvClient;

    ModelMapper modelMapper = new ModelMapper();
    @Override
    public CvDTO getCvByIdUser(String id) {

        return null;
    }
}
