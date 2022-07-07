package com.example.jobsuserservice.feignclient;


import com.example.common.Response.BaseResponse;
import com.example.jobsuserservice.dto.response.FileDBResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@FeignClient("cv-service")
public interface NewCvClient {
    @GetMapping("/cv/2/files")
    public BaseResponse getListFiles();
    @GetMapping("/cv/2/files/user/{username}")
    public BaseResponse getListFilesByUsername(@PathVariable String username) ;
    @GetMapping("/cv/2/file/{id}")
    public BaseResponse<FileDBResponse> getFiles(@PathVariable String id);
//    @GetMapping("/cvsid/{id}")
//    public BaseResponse getAllCvByIdUser2(@PathVariable("id") Long id);
}

