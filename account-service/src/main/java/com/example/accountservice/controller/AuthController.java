package com.example.accountservice.controller;

import com.example.accountservice.dto.request.*;
import com.example.accountservice.dto.response.JwtResponse;
import com.example.accountservice.model.User;
import com.example.accountservice.repository.RoleRepository;
import com.example.accountservice.repository.UserRepository;
import com.example.accountservice.jwt.JwtProvider;
import com.example.accountservice.services.AuthService;
import com.example.accountservice.services.EmailService;
import com.example.accountservice.services.implement.UserServiceImpl;

import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserServiceImpl userDetailsService;

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public BaseResponse authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        try {
            JwtResponse jwt =  authService.signin(loginRequest);
            if(jwt == null){
                return new ResponseEmpty();
            }
            return new ResponseData(jwt);
        }catch (Exception e){
            return new ResponseError("Error"+e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/signup")
    public BaseResponse registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        try {
            User user = authService.signup(signUpRequest);
            if(user==null){
                return new ResponseEmpty();
            }
            return new ResponseData("Register successfully");
        }catch (Exception e){
            return new ResponseError("Error " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/changepass")
    public BaseResponse changePass(@Valid @RequestBody ChangePassForm changePassForm, HttpServletRequest request){

        try {
            User userJWT = authService.getUserFromJWT(request);
            User user = authService.changePass(changePassForm,userJWT);
            if(user == null){
                return new ResponseEmpty();
            }
            return new ResponseData("Change password successfully");


        }catch (Exception e){
            return new ResponseError("Error" + e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/setrole")
    public BaseResponse setRole(@Valid @RequestBody SetRoleForm setRoleForm){
        try {
            authService.setRole(setRoleForm);
            return new ResponseData("Set role successfully");
        }catch (Exception e){
            return new ResponseError("Error " +e , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/fogot")
    public BaseResponse resetPassByEmail(@Valid @RequestBody FogotPassForm fogotPassForm){
        try {
            authService.resetPassByMail(fogotPassForm);
            return new ResponseData("");
        }catch (Exception e){
            return new ResponseError("Error " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/reset")
    public BaseResponse resetPasswordByEmailToken(@Valid @RequestBody ResetPassForm resetPassForm) {
        try{
            User user =  authService.resetPassByMailToken(resetPassForm);
            if(user == null){
                return new ResponseEmpty();
            }
            return new ResponseData(user);
        }catch (Exception e){
            return new ResponseError("Error " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}