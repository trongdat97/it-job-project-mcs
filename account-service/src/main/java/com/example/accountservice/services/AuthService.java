package com.example.accountservice.services;

import com.example.accountservice.message.request.*;
import com.example.accountservice.message.response.JwtResponse;
import com.example.common.Response.BaseResponse;

public interface AuthService {
    JwtResponse signin(LoginForm loginForm);
    BaseResponse signup(SignUpForm signUpForm);
    BaseResponse changePass(ChangePassForm changePassForm);
    BaseResponse forgotPass(FogotPassForm fogotPassForm);
    BaseResponse resetPass(ResetPassForm resetPassForm);
}
