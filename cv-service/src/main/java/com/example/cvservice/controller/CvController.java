package com.example.cvservice.controller;
import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import com.example.cvservice.dto.CvDTO;
import com.example.cvservice.dto.request.CvCreateRequest;
import com.example.cvservice.model.CV;
import com.example.cvservice.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class CvController {
    @Autowired
    CvService cvService;

    @GetMapping("/cv")
    public BaseResponse getAllCv(@RequestParam(required = false) String title){
        try{
            List<CvDTO> cvs = cvService.getAllCV();
            if(cvs==null){
                return new ResponseEmpty();
            }
            return new ResponseData(cvs);
        }catch (Exception e){
            return new ResponseError("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/cv")
    public BaseResponse createCv(@Valid @RequestBody CvCreateRequest cvCreateRequest){
        try{
            CvDTO cvDTO = cvService.createCV(cvCreateRequest);
            if(cvDTO == null){
                return new ResponseEmpty();
            }
            return new ResponseData(cvDTO);
        }catch (Exception e){
            return new ResponseError("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
