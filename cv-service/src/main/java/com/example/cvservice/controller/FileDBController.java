package com.example.cvservice.controller;

import com.example.common.Response.BaseResponse;
import com.example.common.Response.ResponseData;
import com.example.common.Response.ResponseEmpty;
import com.example.common.Response.ResponseError;
import com.example.cvservice.dto.reponse.FileByteResponse;
import com.example.cvservice.dto.reponse.FileDBResponse;
import com.example.cvservice.dto.reponse.MessageResponse;
import com.example.cvservice.model.FileDB;
import com.example.cvservice.repository.FileDBRepository;
import com.example.cvservice.service.FileDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cv/2")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileDBController {

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private FileDBService fileDBService;

    @PostMapping("/upload")
    public BaseResponse uploadFile(@RequestParam("model") String model,@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileDBService.store(file, model);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return new ResponseData(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return new ResponseError(message, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PostMapping("/up")
    public BaseResponse up(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileDBService.postCV(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return new ResponseData(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return new ResponseError(message, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/files")
    public BaseResponse getListFiles() {
        try {
            List<FileDBResponse> files = fileDBService.getAllFiles2();
            if(files==null){
                return new ResponseEmpty();
            }return new ResponseData(files);
        }catch (Exception e){
            return new ResponseError("Error"+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/files/user/{username}")
    public BaseResponse getListFilesByUsername(@PathVariable String username) {
        try {
            List<FileDBResponse> files =  fileDBService.getFilesByUsername2(username);
            if(files==null){
                return new ResponseEmpty();

            }return new ResponseData(files);
        }catch (Exception e){
            return new ResponseError("Error"+e ,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @GetMapping("/file/{id}")
    public BaseResponse<FileDBResponse> getFiles(@PathVariable String id) {
        try {
            FileDBResponse dbResponse = fileDBService.getFilesById2(id);
            if ( dbResponse == null){
                return new ResponseEmpty();
            }
            return new ResponseData(dbResponse);
        }catch (Exception e){
            return new ResponseError("Error"+ e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileByteResponse file = fileDBService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }
}