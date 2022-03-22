package com.example.accountservice.services.implement;

import com.example.accountservice.jwt.JwtProvider;
import com.example.accountservice.message.request.*;
import com.example.accountservice.message.response.JwtResponse;
import com.example.accountservice.model.User;
import com.example.accountservice.model.UserPrinciple;
import com.example.accountservice.repository.UserRepository;
import com.example.accountservice.services.AuthService;
import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public JwtResponse signin(LoginForm loginForm) {
        User user = userRepository.loadByUsername(loginForm.getUsername());
        if(encoder.matches(loginForm.getPassword(),user.getPassword())){
            UserPrinciple userPrinciple = UserPrinciple.build(user);

            String jwt = jwtProvider.generateJwtToken(userPrinciple);
            return new JwtResponse(jwt);
        }else {
            return null;
        }

    }

    @Override
    public BaseResponse signup(SignUpForm signUpForm) {
        return null;
    }

    @Override
    public BaseResponse changePass(ChangePassForm changePassForm) {
        return null;
    }

    @Override
    public BaseResponse forgotPass(FogotPassForm fogotPassForm) {
        return null;
    }

    @Override
    public BaseResponse resetPass(ResetPassForm resetPassForm) {
        return null;
    }
}
