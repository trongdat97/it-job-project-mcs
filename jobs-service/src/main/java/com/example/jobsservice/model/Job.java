package com.example.jobsservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Document(collection = "job")
public class Job {
    @Id
    //@Field("id")
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
    private Boolean isActive = false;
    private Boolean isDel = false;

    public Job(){

    }


    public Job(String id, String jobName, String companyName, String companyAddress, String companyLogo,
               Date createAt, Date timeExpired, String jobDetail, Boolean isActive, Boolean isDel, String jobType, Integer lowestSalary,
               Integer highestSalary, Integer experience) {
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
        this.isActive = isActive;
        this.isDel = isDel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

    public String getJobId() {
        return id;
    }

    public void setJobId(String jobId) {
        id = id;
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

    public Boolean getIsDel(){return isDel;}

    public void setIsDel(Boolean isDel){ this.isDel = isDel;}

    public Boolean getIsActive(){return isActive;}

    public void setIsActive(Boolean isActive){this.isActive = isActive;}


    @Override
    public String toString() {
        return "Job{" +
                "JobId=" + id +
                ", JobName='" + JobName + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", CompanyAddress='" + CompanyAddress + '\'' +
                ", CompanyLogo='" + CompanyLogo + '\'' +
                ", CreateAt=" + CreateAt +
                ", TimeExpired=" + TimeExpired +
                ", JobDetail='" + JobDetail + '\'' +
                '}';
    }
}
