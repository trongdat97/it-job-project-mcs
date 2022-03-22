package com.example.accountservice.services.implement;

import com.example.accountservice.jwt.JwtProvider;
import com.example.accountservice.message.request.*;
import com.example.accountservice.message.response.JwtResponse;
import com.example.accountservice.model.Role;
import com.example.accountservice.model.RoleName;
import com.example.accountservice.model.User;
import com.example.accountservice.model.UserPrinciple;
import com.example.accountservice.repository.RoleRepository;
import com.example.accountservice.repository.UserRepository;
import com.example.accountservice.services.AuthService;
import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseError;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Autowired
    RoleRepository roleRepository;

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
    public User signup(SignUpForm signUpForm) {
        if(userRepository.existsByUsername(signUpForm.getUsername())){
            return null;
        }
        if(userRepository.existsByEmail(signUpForm.getEmail())){
            return null;
        }
        User user = new User(signUpForm.getName(), signUpForm.getUsername(),
                signUpForm.getEmail(), encoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch(role) {
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public User changePass(ChangePassForm changePassForm, User userJWT) {
        User user = userRepository.loadByUsername(userJWT.getUsername());
        if(encoder.matches(changePassForm.getPassword(),user.getPassword())){
            user.setPassword(encoder.encode(changePassForm.getNewpassword()));
            return userRepository.save(user);
        }else{
            return null;
        }
    }

    @Override
    public BaseResponse forgotPass(FogotPassForm fogotPassForm) {
        return null;
    }

    @Override
    public BaseResponse resetPass(ResetPassForm resetPassForm) {
        return null;
    }

    @Override
    public User getUserFromJWT(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String authToken = authHeader.replace("Bearer ","");

        String username = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken)
                .getBody().getSubject();
        User user = userRepository.loadByUsername(username);
        return user;
    }
}
