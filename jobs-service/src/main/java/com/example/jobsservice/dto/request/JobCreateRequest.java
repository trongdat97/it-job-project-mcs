package com.example.jobsservice.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class JobCreateRequest {
    private String id;
    private String JobType;
    private Integer lowestSalary;
    private Integer highestSalary;
    private Integer experience;

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

    @NotBlank
    @Min(3)
    @Max(50)
    private String JobName;
    @NotBlank
    @Min(3)
    @Max(50)
    private String CompanyName;
    @NotBlank
    @Min(3)
    @Max(50)
    private String CompanyAddress;
    @NotBlank
    @Max(100)
    private String CompanyLogo;
    @NotBlank
    @Max(30)
    private Date CreateAt;
    @NotBlank
    @Max(30)
    private Date TimeExpired;
    @NotBlank
    @Max(500)
    private String JobDetail;
}
