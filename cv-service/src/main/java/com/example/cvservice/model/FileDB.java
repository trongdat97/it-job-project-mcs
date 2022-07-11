package com.example.cvservice.model;

import lombok.Data;
import org.bson.types.Binary;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;

@Data
@Entity
public class FileDB {

    @javax.persistence.Id
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    private String username;

    private String jobId;

    private String cvname;

    @Lob
    private byte[] data;

    public FileDB(String name, String type, byte[] data, String username, String jobId, String cvname) {

        this.name = name;
        this.type = type;
        this.data = data;
        this.username = username;
        this.jobId = jobId;
        this.cvname = cvname;
    }

    public FileDB() {

    }
}