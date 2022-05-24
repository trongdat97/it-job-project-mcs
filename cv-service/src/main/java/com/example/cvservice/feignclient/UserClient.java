package com.example.cvservice.feignclient;


import com.example.common.Response.BaseResponse;
import com.example.cvservice.dto.UserDTO;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@FeignClient("account-service")
public interface UserClient{

    @GetMapping("/auth/getInfoUser")
    public BaseResponse<UserDTO> getInfoUser(String request);
}
