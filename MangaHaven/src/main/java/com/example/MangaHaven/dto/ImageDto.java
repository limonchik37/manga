package com.example.MangaHaven.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ImageDto {
    private Long id;
    private String name;
    private String content;
}
