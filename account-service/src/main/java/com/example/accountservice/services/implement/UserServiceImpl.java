package com.example.accountservice.services.implement;

import com.example.accountservice.dto.UserDTO;
import com.example.accountservice.model.User;

import com.example.accountservice.repository.UserRepository;
import com.example.accountservice.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<UserDTO> getAllUser(){
        List<User> users = userRepository.findAll();
        Type listType = new TypeToken<List<UserDTO>>() {}.getType();
        List<UserDTO> userDTOs = modelMapper.map(users,listType);
        return userDTOs;
    }
}
