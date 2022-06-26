package com.example.cvservice.service;

import com.example.cvservice.model.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileDBService {
    FileDB store(MultipartFile file) throws IOException;

    Stream<FileDB> getAllFiles();
}
