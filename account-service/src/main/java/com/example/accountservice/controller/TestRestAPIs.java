package com.example.accountservice.controller;

import com.example.accountservice.model.ResponseData;
import com.example.accountservice.model.User;
import com.example.accountservice.services.implement.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestRestAPIs {

    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return ">>> User Contents!";
    }

    @GetMapping("/pm")
//    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public String projectManagementAccess() {
        return ">>> Board Management Project";
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return ">>> Admin Contents";
    }
    @Autowired
    UserDetailsServiceImpl udsi;
    @GetMapping("/findall")
//    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public ResponseData<List<User>>  findAll() {
        return new ResponseData<List<User>>(udsi.findAll());
    }
}