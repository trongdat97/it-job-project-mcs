package com.example.cvservice.service.implement;

import com.example.common.Response.BaseResponse;
import com.example.cvservice.dto.UserDTO;
import com.example.cvservice.feignclient.UserClient;
import com.example.cvservice.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserClient userClient;

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Override
    public UserDTO getAllInfoUser(HttpServletRequest request) {
        String username = getUserNameFromJWT(request);
        BaseResponse<UserDTO> res = userClient.getUserByName(username);
        UserDTO userDTO = res.getData();
        return userDTO;
    }
    public String getUserNameFromJWT(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String authToken = authHeader.replace("Bearer ","");

        String username = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken)
                .getBody().getSubject();
        return username;
    }
    @Override
    public Long getIdUser(UserDTO userDTO){
        return  userDTO.getId();
    }

}
