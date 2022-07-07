package com.example.cvservice.service.implement;

import com.example.cvservice.dto.ModelDTO;
import com.example.cvservice.dto.reponse.FileDBResponse;
import com.example.cvservice.model.FileDB;
import com.example.cvservice.repository.FileDBRepository;
import com.example.cvservice.service.FileDBService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
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
        FileDB image = new FileDB(fileName, file.getContentType(), file.getBytes(),modelDTO.getUsername(),modelDTO.getJobId());
        System.out.println(modelDTO.getName());
        return fileDBRepository.save(image);
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    @Override
    public FileDB getFilesById(String id){
        return fileDBRepository.loadById(id);
    }
    @Override
    public Stream<FileDB> getFilesByUsername(String username){
        return fileDBRepository.loadByUsernam(username).stream();
    }
    @Override
    public List<FileDBResponse> getAllFiles2(){
        List<FileDBResponse> files = getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/cv/2/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new FileDBResponse(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getUsername(),
                    dbFile.getJobId());
        }).collect(Collectors.toList());
        return files;
    }

    @Override
    public FileDBResponse getFilesById2(String id){
        FileDB files = getFilesById(id);
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/cv/2/files/")
                .path(files.getId().toString())
                .toUriString();

        FileDBResponse dbResponse = new FileDBResponse(
                files.getId(),
                files.getName(),
                fileDownloadUri,
                files.getType(),
                files.getData().length,
                files.getUsername(),
                files.getJobId());

        return dbResponse;
    }


    @Override
    public List<FileDBResponse> getFilesByUsername2(String username){
        List<FileDBResponse> files = getFilesByUsername(username).map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/cv/2/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new FileDBResponse(
                    dbFile.getId(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getUsername(),
                    dbFile.getJobId());
        }).collect(Collectors.toList());
        return files;
    }
}