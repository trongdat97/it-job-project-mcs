package com.example.accountservice.services;

import com.example.accountservice.dto.request.SetRoleForm;
import com.example.accountservice.dto.request.SignUpForm;
import com.example.accountservice.model.JobDTO;
import com.example.accountservice.model.User;
import com.example.common.Response.BaseResponse;

import java.util.List;

public interface AdminService {
    void deleteUser(Long id);
    void unDeleteUser(Long id);
    void setRole(SetRoleForm setRoleForm);
    BaseResponse addNewUser(SignUpForm signUpForm);
    BaseResponse<List<JobDTO>> getUser();
}
