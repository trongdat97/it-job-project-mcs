package com.example.jobservice.model;

import com.google.inject.internal.cglib.core.$Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long JobId;
    private String JobName;
    private String CompanyName;
    private String CompanyAddress;
    private String CompanyLogo;
    private Date CreateAt;
    private Date TimeExpired;
    private String JobDetail;
}
