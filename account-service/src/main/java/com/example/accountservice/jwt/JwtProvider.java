package com.example.accountservice.jwt;


import com.example.accountservice.model.UserInfoToken;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(UserInfoToken userInfoToken) {

        return Jwts.builder()
                .setSubject((userInfoToken.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
                .claim("user-details",new UserInfoToken(userInfoToken.getUsername(),userInfoToken.getRoles()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

}