package com.example.cvservice.dto;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

public class CvDTO {
    @Id
    private String id;
    private String name;
    private String url;
    private Boolean activate;
    private String jobId;
    private Long userId;
    private String username;
//    private Binary cvfile;
//
//    public Binary getCvfile() {
//        return cvfile;
//    }
//
//    public void setCvfile(Binary cvfile) {
//        this.cvfile = cvfile;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
