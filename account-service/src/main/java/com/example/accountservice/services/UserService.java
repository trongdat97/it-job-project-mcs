package com.example.accountservice.services;

import com.example.accountservice.dto.UserDTO;
import com.example.accountservice.dto.request.UpdateUserForm;
import com.example.accountservice.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUser();
    UserDTO getUserById(Long id);
    UserDTO updateUser(Long id, UpdateUserForm updateUserForm);
    UserDTO getUserByUserName(String username);
}
