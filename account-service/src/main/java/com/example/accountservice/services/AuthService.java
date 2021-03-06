package com.example.accountservice.services;

import com.example.accountservice.dto.UserDTO;
import com.example.accountservice.dto.request.*;
import com.example.accountservice.dto.response.JwtResponse;
import com.example.accountservice.model.User;
import com.example.common.Response.BaseResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    JwtResponse signin(LoginForm loginForm);
    User signup(SignUpForm signUpForm);
    User changePass(ChangePassForm changePassForm, User userJWT);
    UserDTO getUserFromJWT2(HttpServletRequest request);
    BaseResponse forgotPass(FogotPassForm fogotPassForm);
    BaseResponse resetPass(ResetPassForm resetPassForm);
    User getUserFromJWT(HttpServletRequest request);
    User changePass2(ChangePassForm changePassForm ,String username);

//    void resetPassByMail(FogotPassForm fogotPassForm);
//    User resetPassByMailToken(ResetPassForm resetPassForm);
}
