package com.example.jobsuserservice.dto.response;


public class FileDBResponse {
    public String id;
    public String name;
    public String url;
    public String type;
    public long size;
    public String username;

    public String jobId;

    public FileDBResponse(String id,String name, String url, String type, long size, String username, String jobId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.username = username;
        this.jobId = jobId;
        this.size = size;

    }
}
