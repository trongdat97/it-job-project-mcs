package com.example.jobsuserservice.dto.response;


public class FileDBResponse {
    public String id;
    public String name;
    public String url;
    public String type;
    public long size;
    public String username;
    public String jobId;
    public String cvname;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCvname() {
        return cvname;
    }

    public void setCvname(String cvname) {
        this.cvname = cvname;
    }
}
