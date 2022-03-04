package com.example.galleryservice.entiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {
    private Integer id;
    private List<Object> images;
}
