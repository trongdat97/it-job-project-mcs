package com.example.cvservice.controller;
import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import com.example.cvservice.dto.CvDTO;
import com.example.cvservice.dto.UserDTO;
import com.example.cvservice.dto.reponse.MessageResponse;
import com.example.cvservice.dto.request.CvCreateRequest;
import com.example.cvservice.dto.request.CvUpdateRequest;
import com.example.cvservice.model.FileDB;
import com.example.cvservice.repository.FileDBRepository;
import com.example.cvservice.service.CvService;
import com.example.cvservice.service.FileDBService;
import com.example.cvservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class CvController {
    @Autowired
    CvService cvService;

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private FileDBService fileDBService;


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
    public BaseResponse getCvById(@PathVariable("id") Long id){
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
    public BaseResponse updateCv(@Valid @PathVariable("id") Long id, @RequestBody CvUpdateRequest cvUpdateRequest){
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
    public BaseResponse deleteCv(@PathVariable("id") Long id){
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
    @GetMapping("/cvs")
    public BaseResponse getAllCvByIdUser(HttpServletRequest request){
        try{
            List<CvDTO> cvs = cvService.getCvByIdUser(request);
            if(cvs==null){
                return new ResponseEmpty();
            }
            return new ResponseData(cvs);
        }catch (Exception e){
            return new ResponseError("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cvsid/{id}")
    public BaseResponse getAllCvByIdUser2(@PathVariable("id") Long id){
        try{
            List<CvDTO> cvs = cvService.getCvByIdUser2(id);
            if(cvs==null){
                return new ResponseEmpty();
            }
            return new ResponseData(cvs);
        }catch (Exception e){
            return new ResponseError("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/cv/uploadcv")
    public BaseResponse<MessageResponse> uploadFile(@RequestParam("model") String model, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            cvService.newCreateCv(model,file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return new ResponseData(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return new ResponseError(message, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/cv/ofuser/{username}")
    public BaseResponse getCvOfUser(@PathVariable String username){
        try {
            List<CvDTO> cvDTOS =  cvService.getCvOfUser(username);
            if(cvDTOS == null){
                return new ResponseEmpty();
            }
            return new ResponseData(cvDTOS);
        }catch (Exception e){
            return new ResponseError("Error" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
