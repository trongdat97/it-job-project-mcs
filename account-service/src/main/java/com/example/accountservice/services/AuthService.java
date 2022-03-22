package com.example.accountservice.services;

import com.example.accountservice.message.request.*;
import com.example.accountservice.message.response.JwtResponse;
import com.example.accountservice.model.User;
import com.example.common.Response.BaseResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    JwtResponse signin(LoginForm loginForm);
    User signup(SignUpForm signUpForm);
    User changePass(ChangePassForm changePassForm, User userJWT);
    BaseResponse forgotPass(FogotPassForm fogotPassForm);
    BaseResponse resetPass(ResetPassForm resetPassForm);
    User getUserFromJWT(HttpServletRequest request);
}
