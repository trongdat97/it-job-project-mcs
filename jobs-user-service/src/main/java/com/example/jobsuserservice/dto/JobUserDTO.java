package com.example.jobsuserservice.dto;

import java.util.Date;

public class JobUserDTO {
    private String JobId;
    private String JobName;
    private String JobType;
    private Integer lowestSalary;

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

    private Integer highestSalary;
    private Integer experience;
    private String CompanyName;
    private String CompanyAddress;
    private String CompanyLogo;
    private Date CreateAt;
    private Date TimeExpired;
    private String JobDetail;

    public String getJobId() {
        return JobId;
    }

    public void setJobId(String jobId) {
        JobId = jobId;
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
