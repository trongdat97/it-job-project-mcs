package com.example.accountservice.services;

import com.example.accountservice.dto.UserDtoZuul;
import com.example.accountservice.model.User;

public interface ZuulService {
    UserDtoZuul getUserByUsername(String username);
}
