package com.example.MangaHaven.service;

import com.example.MangaHaven.dto.ChapterDto;
import com.example.MangaHaven.entity.Chapter;

import java.util.List;

public interface ChapterService {
    Chapter create(ChapterDto chapterDto);

    Chapter update(Long id, ChapterDto chapterDto);

    void delete(Long id);

    Chapter getById(Long id);
}
