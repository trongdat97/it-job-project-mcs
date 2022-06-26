package com.example.jobsservice.dto;


import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class JobDTO {
//    @Field("id")
    private String id;
    private String JobName;
    private String JobType;
    private Integer lowestSalary;
    private Integer highestSalary;
    private Integer experience;
    private String CompanyName;
    private String CompanyAddress;
    private String CompanyLogo;
    private Date CreateAt;
    private Date TimeExpired;
    private String JobDetail;
    private Boolean isDel = false;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

    public JobDTO(){

    }

    public JobDTO(String id, String jobName, String companyName, String companyAddress, String companyLogo,
               Date createAt, Date timeExpired, String jobDetail, String jobType,
                  Integer lowestSalary, Integer highestSalary, Integer experience, Boolean isDel, String username) {
        this.JobType = jobType;
        this.lowestSalary = lowestSalary;
        this.highestSalary = highestSalary;
        this.experience =  experience;
        this.id = id;
        this.JobName = jobName;
        this.CompanyName = companyName;
        this.CompanyAddress = companyAddress;
        this.CompanyLogo = companyLogo;
        this.CreateAt = createAt;
        this.TimeExpired = timeExpired;
        this.JobDetail = jobDetail;
        this.isDel = isDel;
        this.username = username;
    }

    public String getJobType() {
        return JobType;
    }

    public void setJobType(String jobType) {
        JobType = jobType;
    }

    public Integer getLowestSalary() {
        return lowestSalary;
    }

    public void setLowestSalary(Integer lowestSalary) {
        this.lowestSalary = lowestSalary;
    }

    public Integer getHighestSalary() {
        return highestSalary;
    }

    public void setHighestSalary(Integer highestSalary) {
        this.highestSalary = highestSalary;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }

    public String getCompanyLogo() {
        return CompanyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        CompanyLogo = companyLogo;
    }

    public Date getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(Date createAt) {
        CreateAt = createAt;
    }

    public Date getTimeExpired() {
        return TimeExpired;
    }

    public void setTimeExpired(Date timeExpired) {
        TimeExpired = timeExpired;
    }

    public String getJobDetail() {
        return JobDetail;
    }

    public void setJobDetail(String jobDetail) {
        JobDetail = jobDetail;
    }
}
