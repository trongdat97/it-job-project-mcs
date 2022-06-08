package com.example.cvservice.controller;
import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import com.example.cvservice.dto.CvDTO;
import com.example.cvservice.dto.UserDTO;
import com.example.cvservice.dto.request.CvCreateRequest;
import com.example.cvservice.dto.request.CvUpdateRequest;
import com.example.cvservice.model.CV;
import com.example.cvservice.service.CvService;
import com.example.cvservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class CvController {
    @Autowired
    CvService cvService;

    @Autowired
    UserService userService;

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
    @GetMapping("/cv/{id}")
    public BaseResponse getCvById(@PathVariable("id") String id){
        try{
            CvDTO cvDTO = cvService.getCvById(id);
            if(cvDTO==null){
                return new ResponseEmpty();
            }
            return new ResponseData(cvDTO);
        }catch (Exception e){
            return new ResponseError("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/cv")
    public BaseResponse createCv(@Valid @RequestBody CvCreateRequest cvCreateRequest, HttpServletRequest request){
        try{
            CvDTO cvDTO = cvService.createCV(cvCreateRequest, request);
            if(cvDTO == null){
                return new ResponseEmpty();
            }
            return new ResponseData(cvDTO);
        }catch (Exception e){
            return new ResponseError("Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/cv/{id}")
    public BaseResponse updateCv(@Valid @PathVariable("id") String id, @RequestBody CvUpdateRequest cvUpdateRequest){
        try {
            CvDTO cvDTO = cvService.updateCV(id,cvUpdateRequest);
            if(cvDTO == null){
                return new ResponseEmpty();
            }return new ResponseData(cvDTO);

        }catch (Exception e){
            return new ResponseError("Error " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/cv/{id}")
    public BaseResponse deleteCv(@PathVariable("id") String id){
        try {
            cvService.deleteCV(id);
            return new ResponseData("Delete Successfully");
        }catch (Exception e){
            return new ResponseError("Error " + e , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cv/user")
    public BaseResponse getInfoUser(HttpServletRequest request){
        try {
            UserDTO userDTO = userService.getAllInfoUser(request);
            if(userDTO == null){
                return new ResponseEmpty();
            }
            return new ResponseData(userDTO);
        }catch (Exception e){
            return new ResponseError("Error "+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
