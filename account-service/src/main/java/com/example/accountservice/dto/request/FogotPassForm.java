package com.example.accountservice.dto.request;

import javax.validation.constraints.Email;

public class FogotPassForm {
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
