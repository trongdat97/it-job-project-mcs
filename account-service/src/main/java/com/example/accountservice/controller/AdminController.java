package com.example.accountservice.controller;

import com.example.accountservice.dto.request.LoginForm;
import com.example.accountservice.dto.request.SetRoleForm;
import com.example.accountservice.dto.request.SignUpForm;
import com.example.accountservice.model.JobDTO;
import com.example.accountservice.model.Role;
import com.example.accountservice.services.AdminService;
import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/setrole")
    public BaseResponse setRole(@Valid @RequestBody SetRoleForm setRoleForm){
        try {
            adminService.setRole(setRoleForm);
            return new ResponseData("Set role successfully");
        }catch (Exception e){
            return new ResponseError("Error " +e , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/activate/{id}")
    public BaseResponse activate(@PathVariable("id") Long id){
        try {
            adminService.deleteUser(id);
            return new ResponseData("Delete User Successfully");
        }catch (Exception e){
            return new ResponseError("Error" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/unactivate/{id}")
    public BaseResponse unactivate(@PathVariable("id") Long id){
        try {
            adminService.unDeleteUser(id);
            return new ResponseData("Restore User Successfully");
        }catch (Exception e){
            return new ResponseError("Error" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/jobsadmin")
    public BaseResponse getJob(){
        try{
            BaseResponse<List<JobDTO>> jobs= adminService.getUser();
            return new ResponseData(jobs);
        }catch (Exception e){
            return new ResponseError("error" +e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getrole")
    public BaseResponse getRole(@Valid @RequestBody LoginForm loginForm){
        try {
            Set<Role> roles = adminService.getRole(loginForm);
            if (roles == null){
                return new ResponseEmpty();
            }else {
                return new ResponseData(roles);
            }
        }catch (Exception e){
            return new ResponseError("error" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
