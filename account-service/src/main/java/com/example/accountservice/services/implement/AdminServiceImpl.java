package com.example.accountservice.services.implement;


import com.example.accountservice.dto.request.SetRoleForm;
import com.example.accountservice.dto.request.SignUpForm;
import com.example.accountservice.model.Role;
import com.example.accountservice.model.RoleName;
import com.example.accountservice.model.User;
import com.example.accountservice.repository.RoleRepository;
import com.example.accountservice.repository.UserRepository;
import com.example.accountservice.services.AdminService;
import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.loadById(id);
        user.setActivate(false);
        userRepository.save(user);
    }

    @Override
    public void unDeleteUser(Long id) {
        User user = userRepository.loadById(id);
        user.setActivate(true);
        userRepository.save(user);
    }
    @Override
    public void setRole(SetRoleForm setRoleForm) {
        User user = userRepository.loadByUsername(setRoleForm.getUsername());
        Set<String> strRoles = setRoleForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch(role) {
                case "pm":
                    Role modRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);
                    break;
                case "user":
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userRepository.save(user);


    }

    @Override
    public BaseResponse addNewUser(SignUpForm signUpForm) {
        if(userRepository.existsByUsername(signUpForm.getUsername())){
            return new ResponseError("User name already exit");
        }
        if(userRepository.existsByEmail(signUpForm.getEmail())){
            return new ResponseError("Gmail already exit ");
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
        userRepository.save(user);
        return new ResponseData(user);
    }
}
