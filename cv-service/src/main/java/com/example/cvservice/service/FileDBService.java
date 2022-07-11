package com.example.cvservice.service;

import com.example.cvservice.dto.reponse.FileByteResponse;
import com.example.cvservice.dto.reponse.FileDBResponse;
import com.example.cvservice.model.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface FileDBService {
    FileDB store(MultipartFile file, String model) throws IOException, InterruptedException;

    Stream<FileDB> getAllFiles();

    FileDB getFilesById(String id);

    Stream<FileDB> getFilesByUsername(String username);
    List<FileDBResponse> getAllFiles2();

    FileDBResponse getFilesById2(String id);
    FileByteResponse getFile(String id);
    String postCV(MultipartFile file, String idcv) throws IOException;

    List<FileDBResponse> getFilesByUsername2(String username);
}
