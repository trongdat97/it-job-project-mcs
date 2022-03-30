package com.example.accountservice.services;

import com.example.accountservice.dto.request.SetRoleForm;
import com.example.accountservice.model.User;

public interface AdminService {
    void deleteUser(Long id);
    void unDeleteUser(Long id);
    void setRole(SetRoleForm setRoleForm);
}
