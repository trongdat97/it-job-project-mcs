package com.example.accountservice.services.implement;
import com.example.accountservice.dto.request.FogotPassForm;
import com.example.accountservice.dto.request.ResetPassForm;
import com.example.accountservice.model.User;
import com.example.accountservice.repository.UserRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.example.accountservice.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${API_KEY}")
    private String API_KEY;
    @Value("${API_BASE_URL}")
    private String API_BASE_URL;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;

//    @Override
//    public JsonNode sendEmail(String email, String resettoken) throws UnirestException {
//        JsonNode jsonNode = sendSimpleMessage(email,resettoken);
//        return jsonNode;
//    }

    public JsonNode sendSimpleMessage(String email, String resettoken) throws UnirestException {

        HttpResponse<com.mashape.unirest.http.JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + API_BASE_URL + "/messages")
                .basicAuth("api", API_KEY)
                .field("from", "Excited User LemubitStudents@lemubit.com")
                .field("to", email)//TODO put your emails here
                .field("subject", "Hello From Lemubit Students")
                .field("text", "resettoken" + resettoken)
                .asJson();

        return request.getBody();
    }
    @Override
    public String resetPassByMail(FogotPassForm fogotPassForm) throws UnirestException {
        String email = fogotPassForm.getEmail();
        User user = userRepository.loadByEmail(email);
        String token = UUID.randomUUID().toString();
        user.setResettonken(token);
        userRepository.save(user);
        JsonNode jsonNode = sendSimpleMessage(email,token);
        //send mail with reset token
        return jsonNode.toString();

    }
    @Override
    public User resetPassByMailToken(ResetPassForm resetPassForm) {
        User user = userRepository.loadByToKen(resetPassForm.getToken());
        if (user != null) {
            user.setPassword(encoder.encode(resetPassForm.getPassword()));
            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }

}
