package com.example.cvservice.service;

import com.example.cvservice.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    UserDTO getAllInfoUser(HttpServletRequest request);
}
