package com.example.cvservice.service.implement;

import com.example.cvservice.dto.ModelDTO;
import com.example.cvservice.dto.reponse.FileByteResponse;
import com.example.cvservice.dto.reponse.FileDBResponse;
import com.example.cvservice.model.FileDB;
import com.example.cvservice.repository.FileDBRepository;
import com.example.cvservice.service.FileDBService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileDBServiceImpl implements FileDBService {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private FileDBRepository fileDBRepository;

    @Override
    public FileDB store(MultipartFile file, String model) throws IOException, InterruptedException {
        ModelDTO modelDTO = mapper.readValue(model, ModelDTO.class);
        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = modelDTO.getName();
        FileDB image = new FileDB(fileName, file.getContentType(), file.getBytes(),modelDTO.getUsername(),modelDTO.getJobId());
        fileDBRepository.save(image);
//        System.out.println(image.getId());
//        postCV(file);
//        TimeUnit.SECONDS.sleep(45);
        return null;
    }
    @Override
    public String postCV(MultipartFile file) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        ByteArrayResource uploadFile = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getName();
            }
        };

        body.add("file", uploadFile);

        System.out.println(file.getContentType());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = "http://192.168.42.17:5100/uploader";
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
        }catch (Exception e){
            System.out.println("Response code: " + e.toString());
        }


        return null;
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
    public FileByteResponse getFile(String id) {
        FileDB file = getFilesById(id);
        FileByteResponse fileByteResponse = new FileByteResponse();
        fileByteResponse.setData(file.getData());
        fileByteResponse.setName(file.getName());
        return fileByteResponse;
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