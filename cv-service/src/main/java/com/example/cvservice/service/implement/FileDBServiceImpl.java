package com.example.cvservice.service.implement;

import com.example.cvservice.dto.ModelDTO;
import com.example.cvservice.model.FileDB;
import com.example.cvservice.repository.FileDBRepository;
import com.example.cvservice.service.FileDBService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileDBServiceImpl implements FileDBService {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private FileDBRepository fileDBRepository;

    @Override
    public FileDB store(MultipartFile file, String model) throws IOException {
        ModelDTO modelDTO = mapper.readValue(model, ModelDTO.class);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB image = new FileDB(fileName, file.getContentType(), file.getBytes(),"","");
        System.out.println(modelDTO.getName());
        return fileDBRepository.save(image);
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}