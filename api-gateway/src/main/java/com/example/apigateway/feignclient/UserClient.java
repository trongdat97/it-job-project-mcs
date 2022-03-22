package com.example.apigateway.feignclient;

import com.example.apigateway.dto.UserDtoZuul;
import com.example.common.Response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@FeignClient("account-service")
public interface UserClient {
    @GetMapping("/auth/user/{name}")
    public BaseResponse<UserDtoZuul> getUserByName(@Valid @PathVariable("name") String name);
}
