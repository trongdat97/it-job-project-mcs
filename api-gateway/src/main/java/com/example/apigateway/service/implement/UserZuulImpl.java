package com.example.apigateway.service.implement;

import com.example.apigateway.dto.UserDtoZuul;
import com.example.apigateway.feignclient.UserClient;
import com.example.apigateway.service.UserZuul;
import com.example.common.Response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserZuulImpl implements UserZuul {
    @Autowired
    UserClient userClient;
    @Override
    public UserDtoZuul getUserByUserName(String name) {
        BaseResponse<UserDtoZuul> res = userClient.getUserByName(name);
        UserDtoZuul userDtoZuul = res.getData();
        return userDtoZuul;
    }
}
