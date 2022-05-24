package com.example.cvservice.service.implement;

import com.example.common.Response.BaseResponse;
import com.example.cvservice.dto.UserDTO;
import com.example.cvservice.feignclient.UserClient;
import com.example.cvservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserClient userClient;


    @Override
    public UserDTO getAllInfoUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String authToken = authHeader.replace("Bearer ","");
        BaseResponse<UserDTO> res = userClient.getInfoUser(authToken);
        UserDTO userDTO = res.getData();
        return userDTO;
    }
}
