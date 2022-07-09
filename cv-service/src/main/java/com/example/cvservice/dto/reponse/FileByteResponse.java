package com.example.cvservice.dto.reponse;

import lombok.Data;

@Data
public class FileByteResponse {
    private String name;
    private byte[] data;
}