package com.example.accountservice.dto.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UpdateUserForm {
    @NotBlank
    @Min(6)
    @Max(20)
    private String name;
    @NotBlank
    @Min(6)
    @Max(50)
    private String avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
