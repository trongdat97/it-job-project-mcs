package com.example.accountservice.controller;


import com.example.accountservice.dto.UserDtoZuul;
import com.example.accountservice.services.ZuulService;
import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ZuulService zuulService;

    @GetMapping("/user/{name}")
    public BaseResponse<UserDtoZuul> getUserByName(@Valid @PathVariable("name") String name){
        try{
            UserDtoZuul userDtoZuul = zuulService.getUserByUsername(name);
            if(userDtoZuul == null){
                return new ResponseEmpty();
            }
            return new ResponseData<UserDtoZuul>(userDtoZuul);
        }catch (Exception e){
            return new ResponseError<UserDtoZuul>("error" + e );
        }
    }
}
