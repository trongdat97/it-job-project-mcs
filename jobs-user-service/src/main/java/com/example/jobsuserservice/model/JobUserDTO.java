package com.example.jobsuserservice.model;

public class JobUserDTO {
    Long id;
    Long idUser;
    String idJob;
    String idCv;
    Boolean isAccept;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getIdCv() {
        return idCv;
    }

    public void setIdCv(String idCv) {
        this.idCv = idCv;
    }

    public Boolean getAccept() {
        return isAccept;
    }

    public void setAccept(Boolean accept) {
        isAccept = accept;
    }
}
