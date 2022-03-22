package com.example.accountservice.controller;

import com.example.accountservice.message.request.*;
import com.example.accountservice.message.response.JwtResponse;
import com.example.accountservice.model.Role;
import com.example.accountservice.model.RoleName;
import com.example.accountservice.model.User;
import com.example.accountservice.repository.RoleRepository;
import com.example.accountservice.repository.UserRepository;
import com.example.accountservice.jwt.JwtProvider;
import com.example.accountservice.services.AuthService;
import com.example.accountservice.services.EmailService;
import com.example.accountservice.services.implement.UserDetailsServiceImpl;
import com.example.accountservice.model.UserPrinciple;
import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestAPIs {

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Autowired
    EmailService emailService;

//    @Autowired
//    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public BaseResponse authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
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
    @PostMapping("/fogot")
    public ResponseEntity<?> resetPassByEmail(@Valid @RequestBody FogotPassForm fogotPassForm){
        String email = fogotPassForm.getEmail();
        User user = userRepository.loadByEmail(email);
        user.setResettonken(UUID.randomUUID().toString());
        userRepository.save(user);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("support@demo.com");
        simpleMailMessage.setFrom(user.getEmail());
        simpleMailMessage.setSubject("Password Reset Request");
        simpleMailMessage.setText("Reset your password in /reset, get this token to do that"+ user.getResettoken());
        emailService.sendEmail(simpleMailMessage);
        return ResponseEntity.ok().body("Send reset token password successfully, please check mail");
    }
    @PostMapping("/reset")
    public ResponseEntity<?> resetPasswordByEmailToken(@Valid @RequestBody ResetPassForm resetPassForm){
        User user = userRepository.loadByToKen(resetPassForm.getToken());
        if(user != null){
            user.setPassword(encoder.encode(resetPassForm.getPassword()));
            return ResponseEntity.ok().body("Reset pass successfully");
        }else{
            return new ResponseEntity<String>("Not valid",HttpStatus.NOT_FOUND);
        }

    }


}