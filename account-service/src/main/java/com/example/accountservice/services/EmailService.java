package com.example.accountservice.services;

import com.example.accountservice.dto.request.FogotPassForm;
import com.example.accountservice.dto.request.ResetPassForm;
import com.example.accountservice.model.User;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
//    public JsonNode sendEmail(String email, String resettoken) throws UnirestException;
//    public JsonNode sendSimpleMessage(String email, String resettoken) throws UnirestException;
    public String resetPassByMail(FogotPassForm fogotPassForm) throws UnirestException;
    public User resetPassByMailToken(ResetPassForm resetPassForm);
}
