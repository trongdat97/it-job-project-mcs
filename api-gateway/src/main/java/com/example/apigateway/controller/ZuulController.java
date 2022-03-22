//package com.example.apigateway.controller;
//
//
//import com.example.apigateway.dto.UserDtoZuul;
//import com.example.apigateway.service.UserZuul;
//import com.example.common.Response.BaseResponse;
//import com.example.common.Response.ResponseData;
//import com.example.common.Response.ResponseEmpty;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/zuul")
//public class ZuulController {
//    @Autowired
//    UserZuul userZuul;
//
//    @GetMapping("/get/{name}")
//    public BaseResponse<UserDtoZuul> getUserFromAcc(@PathVariable("name") String name){
//        try{
//            UserDtoZuul userDtoZuul = userZuul.getUserByUserName(name);
//            if(userDtoZuul==null){
//                return new ResponseEmpty();
//            }
//            return new ResponseData<>(userDtoZuul);
//        }catch (Exception e){
//            return new ResponseEmpty();
//        }
//    }
//}
