package com.example.MangaHaven.dto;

import com.example.MangaHaven.entity.Image;
import com.example.MangaHaven.entity.Manga;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ChapterDto {

    private Long id;
    private Integer chapterNumber;
    private String title;
    private Long idManga;
    private List<ImageDto> images;
}
