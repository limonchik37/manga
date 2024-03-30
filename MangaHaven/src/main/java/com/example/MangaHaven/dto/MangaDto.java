package com.example.MangaHaven.dto;

import com.example.MangaHaven.entity.Image;
import com.example.MangaHaven.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MangaDto {
    private Long id;
    private String title;
    private String author;
    private String description;
    private List<ChapterDto> chapters;
    private ImageDto coverImage;
    private Integer likes;
    private Boolean liked;
}
