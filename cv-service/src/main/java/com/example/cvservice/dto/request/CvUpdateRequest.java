package com.example.cvservice.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CvUpdateRequest {
    @NotBlank
    @Min(5)
    @Max(30)
    private String id;
    @NotBlank
    @Min(5)
    @Max(30)
    private String name;
    @NotBlank
    @Min(5)
    @Max(100)
    private String url;
    @NotBlank
    private Boolean activate;
    @NotBlank
    @Min(5)
    @Max(50)
    private String idJob;

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

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }
}
