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
    private String CompanyName;
    private String CompanyAddress;
    private String CompanyLogo;
    private Date CreateAt;
    private Date TimeExpired;
    private String JobDetail;

    public Job(){

    }

    public Job(String id, String jobName, String companyName, String companyAddress, String companyLogo,
               Date createAt, Date timeExpired, String jobDetail) {
        this.id = id;
        this.JobName = jobName;
        this.CompanyName = companyName;
        this.CompanyAddress = companyAddress;
        this.CompanyLogo = companyLogo;
        this.CreateAt = createAt;
        this.TimeExpired = timeExpired;
        this.JobDetail = jobDetail;
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
