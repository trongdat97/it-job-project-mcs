package com.example.accountservice.services.implement;

import com.example.accountservice.dto.UserDTO;
import com.example.accountservice.dto.request.UpdateUserForm;
import com.example.accountservice.model.User;

import com.example.accountservice.repository.UserRepository;
import com.example.accountservice.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<UserDTO> getAllUser(){
        List<User> users = userRepository.findAll();
        Type listType = new TypeToken<List<UserDTO>>() {}.getType();
        List<UserDTO> userDTOs = modelMapper.map(users,listType);
        return userDTOs;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.loadById(id);
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(Long id, UpdateUserForm updateUserForm) {
        User user = userRepository.loadById(id);
        UserDTO userDTO = new UserDTO();
        if(user != null){
            user.setName(updateUserForm.getName());
            user.setAvatar(updateUserForm.getAvatar());
            userRepository.save(user);
        }
        userDTO = modelMapper.map(user,UserDTO.class);
        return userDTO;
    }
    @Override
    public UserDTO getUserByUserName(String username){
        User user = userRepository.loadByUsername(username);
        UserDTO userDTO = new UserDTO();
        userDTO = modelMapper.map(user,UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO updateInfo(String username, UpdateUserForm updateUserForm) {
        UserDTO userDTO = getUserByUserName(username);
        userDTO.setAvatar(updateUserForm.getAvatar());
        userDTO.setCity(updateUserForm.getCity());
        userDTO.setName(updateUserForm.getName());
        userDTO.setPhone(updateUserForm.getPhone());
        userDTO.setWebsite(updateUserForm.getWebsite());
        User user = modelMapper.map(userDTO,User.class);
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public void activate(String username) {

    }
    @Override
    public List<UserDTO> getListDel(){
        List<User> users = userRepository.listDel();
        Type listType = new TypeToken<List<UserDTO>>() {}.getType();
        List<UserDTO> userDTOs = modelMapper.map(users,listType);
        return userDTOs;
    }
    @Override
    public List<UserDTO> getListUnDel(){
        List<User> users = userRepository.listUnDel();
        Type listType = new TypeToken<List<UserDTO>>() {}.getType();
        List<UserDTO> userDTOs = modelMapper.map(users,listType);
        return userDTOs;
    }
}
