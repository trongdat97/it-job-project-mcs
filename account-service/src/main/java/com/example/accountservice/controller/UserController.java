package com.example.accountservice.controller;

import com.example.accountservice.dto.UserDTO;
import com.example.accountservice.dto.request.FogotPassForm;
import com.example.accountservice.dto.request.ResetPassForm;
import com.example.accountservice.model.User;
import com.example.accountservice.services.EmailService;
import com.example.accountservice.services.UserService;
import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @GetMapping("/user")
    public BaseResponse getAllUser(){
        try{
            List<UserDTO> userDTOs = userService.getAllUser();
            if(userDTOs==null){
                return new ResponseEmpty();
            }
            return new ResponseData(userDTOs);
        }catch (Exception e){
            return new ResponseError("Error" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/user/{id}")
    public BaseResponse getUserById(@PathVariable("id") Long id) {
        try {
            UserDTO userDTO = userService.getUserById(id);
            if (userDTO == null) {
                return new ResponseEmpty();
            }
            return new ResponseData(userDTO);
        } catch (Exception e) {
            return new ResponseError("Error"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/user/fogot")
    public BaseResponse fogotPass(@Valid @RequestBody FogotPassForm fogotPassForm){
        try {
            String data = emailService.resetPassByMail(fogotPassForm);
            if(data==null){
                return new ResponseEmpty();
            }
            return new ResponseData(data);
        } catch (UnirestException e) {
            e.printStackTrace();
            return new ResponseError("Error" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/user/reset")
    public BaseResponse resetPasswordByEmailToken(@Valid @RequestBody ResetPassForm resetPassForm) {
        try{
            User user =  emailService.resetPassByMailToken(resetPassForm);
            if(user == null){
                return new ResponseEmpty();
            }
            return new ResponseData(user);
        }catch (Exception e){
            return new ResponseError("Error " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
