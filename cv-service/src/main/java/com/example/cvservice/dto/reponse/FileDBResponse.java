package com.example.cvservice.dto.reponse;

import lombok.Data;

@Data
public class FileDBResponse {
    private String id;
    private String name;
    private String url;
    private String type;
    private long size;
    private String username;
    private String cvname;

    private String jobId;

    public FileDBResponse(String id,String name, String url, String type, long size, String username, String jobId, String cvname) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.username = username;
        this.jobId = jobId;
        this.size = size;
        this.cvname = cvname;

    }
}