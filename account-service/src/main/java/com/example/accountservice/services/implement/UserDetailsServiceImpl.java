package com.example.accountservice.services.implement;

import com.example.accountservice.model.User;

import com.example.accountservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl {
    @Autowired
    UserRepository userRepository;
    public List<User> findAll(){
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(user->users.add(user));
        return users;
    }
}
