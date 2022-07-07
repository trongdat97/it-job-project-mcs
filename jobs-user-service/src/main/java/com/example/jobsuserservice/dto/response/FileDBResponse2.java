package com.example.jobsuserservice.dto.response;


public class FileDBResponse2 {
    private String id;
    private String name;
    private String url;
    private String type;
    private long size;
    private String username;

    private String jobId;

    public FileDBResponse2(String id, String name, String url, String type, long size, String username, String jobId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.type = type;
        this.username = username;
        this.jobId = jobId;
        this.size = size;

    }
}
