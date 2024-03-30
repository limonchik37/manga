package com.example.MangaHaven.dto;

import com.example.MangaHaven.entity.Image;
import com.example.MangaHaven.entity.Manga;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private List<MangaDto> likedManga;
    private ImageDto profilePhoto;
}
