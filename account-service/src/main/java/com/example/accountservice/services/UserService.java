package com.example.accountservice.services;

import com.example.accountservice.dto.UserDTO;
import com.example.accountservice.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUser();
}
