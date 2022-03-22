package com.example.apigateway.service;

import com.example.apigateway.dto.UserDtoZuul;

public interface UserZuul {
    UserDtoZuul getUserByUserName(String name);
}
