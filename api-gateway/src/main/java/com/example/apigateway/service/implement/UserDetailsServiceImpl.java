package com.example.apigateway.service.implement;

import com.example.apigateway.dto.UserDtoZuul;

import com.example.apigateway.service.UserZuul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    UserRepository userRepository;
    @Autowired
    UserZuul userZuul;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserDtoZuul user = userZuul.getUserByUserName(username);


        return UserPrinciple.build(user);
    }

}
