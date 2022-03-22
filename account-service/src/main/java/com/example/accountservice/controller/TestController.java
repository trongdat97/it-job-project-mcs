package com.example.accountservice.controller;

import com.example.accountservice.dto.UserDTO;
import com.example.accountservice.model.ResponseData;
import com.example.accountservice.services.UserService;
import com.example.accountservice.services.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String userAccess() {
        return ">>> User Contents!";
    }

    @GetMapping("/pm")
    public String projectManagementAccess() {
        return ">>> Board Management Project";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return ">>> Admin Contents";
    }

    @GetMapping("/findall")
    public ResponseData<List<UserDTO>>  findAll() {
        return new ResponseData<List<UserDTO>>(userService.getAllUser());
    }
}