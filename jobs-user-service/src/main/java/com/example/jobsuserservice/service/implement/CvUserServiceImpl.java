package com.example.jobsuserservice.service.implement;

import com.example.common.Response.BaseResponse;
import com.example.jobsuserservice.dto.JobUserFeignDTO;
import com.example.jobsuserservice.dto.response.CvDTO;
import com.example.jobsuserservice.dto.UserDTO;
import com.example.jobsuserservice.dto.response.FileDBResponse;
//import com.example.jobsuserservice.feignclient.CvClient;
import com.example.jobsuserservice.dto.response.FileDBResponse2;
import com.example.jobsuserservice.feignclient.NewCvClient;
import com.example.jobsuserservice.feignclient.UserClient;
import com.example.jobsuserservice.service.CvUserService;
import io.jsonwebtoken.Jwts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public class CvUserServiceImpl  implements CvUserService {
    @Autowired
    NewCvClient newCvClient;

//    @Autowired
//    CvClient cvClient;
    @Autowired
    UserClient userClient;
    @Value("${jwtSecret}")
    private String jwtSecret;
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<CvDTO> getCvByIdUser(HttpServletRequest request) {
        String username = getUserNameFromJWT(request);
        BaseResponse<UserDTO> res = userClient.getUserByName(username);
        UserDTO userDTO = res.getData();
        Long idUser = userDTO.getId();
//        BaseResponse<List<CvDTO>> resCv = cvClient.getAllCvByIdUser2(idUser);
//        List<CvDTO> cvDTOs = resCv.getData();
        return null;
    }

//    public BaseResponse<List<CvDTO>> getAllCvByIdUser (HttpServletRequest request){
//        BaseResponse res = cvClient.getAllCvByIdUser(request);
//
//    }
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
    public List<FileDBResponse> getCVByUsername(String username) {
        BaseResponse<List<FileDBResponse>> res = newCvClient.getListFilesByUsername(username);
        List<FileDBResponse> fileDBResponseList = res.getData();
        return fileDBResponseList;
    }


    @Override
    public FileDBResponse getCvUserById(String id) {
        BaseResponse<FileDBResponse> res = newCvClient.getFiles(id);
        FileDBResponse fileDBResponse = res.getData();

        return fileDBResponse;
    }

    @Override
    public Long getIdUser(UserDTO userDTO){
        return  userDTO.getId();
    }


}
