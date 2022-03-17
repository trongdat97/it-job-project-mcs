package com.example.jobsuserservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private String JobId;
    private String JobName;
    private String CompanyName;
    private String CompanyAddress;
    private String CompanyLogo;
    private Date CreateAt;
    private Date TimeExpired;
    private String JobDetail;
}
