package com.example.jobsservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobUpdateRequest {
    @NotBlank
    private String id;
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
