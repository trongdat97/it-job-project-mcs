package com.example.jobsuserservice.service.implement;

import com.example.common.Response.BaseResponse;
import com.example.jobsuserservice.dto.CvDTO;
import com.example.jobsuserservice.feignclient.CvClient;
import com.example.jobsuserservice.service.CvUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public class CvUserServiceImpl  implements CvUserService {
    @Autowired
    CvClient cvClient;
    @Value("${jwtSecret}")
    private String jwtSecret;
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public BaseResponse getCvByIdUser(HttpServletRequest request) {
        BaseResponse res = cvClient.getAllCvByIdUser(request);
        System.out.println(res);
        return null;
    }

//    public BaseResponse<List<CvDTO>> getAllCvByIdUser (HttpServletRequest request){
//        BaseResponse res = cvClient.getAllCvByIdUser(request);
//
//    }
}
