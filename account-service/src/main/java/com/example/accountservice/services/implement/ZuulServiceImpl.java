package com.example.accountservice.services.implement;

import com.example.accountservice.dto.UserDtoZuul;
import com.example.accountservice.model.User;
import com.example.accountservice.repository.UserRepository;
import com.example.accountservice.services.ZuulService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZuulServiceImpl implements ZuulService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDtoZuul getUserByUsername(String username) {
        User user = userRepository.loadByUsername(username);
        UserDtoZuul userDtoZuul;
        userDtoZuul = modelMapper.map(user,UserDtoZuul.class);
        return userDtoZuul;
    }
}
