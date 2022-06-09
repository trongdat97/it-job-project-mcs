package com.example.jobsuserservice.feignclient;

import com.example.common.Response.BaseResponse;
import com.example.jobsuserservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@FeignClient("account-service")
public interface UserClient {
    @GetMapping("/auth/getInfoUser")
    public BaseResponse<UserDTO> getInfoUser(HttpServletRequest request);

    @GetMapping("/auth/user/{name}")
    public BaseResponse<UserDTO> getUserByName(@Valid @PathVariable("name") String name);
}
