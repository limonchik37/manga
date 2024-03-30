package com.example.MangaHaven.service;

import com.example.MangaHaven.dto.MangaDto;
import com.example.MangaHaven.entity.Manga;

import java.util.List;

public interface MangaService {
    Manga create(MangaDto mangaDto);

    Manga update(Long id, MangaDto mangaDto);

    void delete(Long id);

    Manga getById(Long id);

    Manga likeManga(Long idManga);

    List<Manga> findGreaterThan(int likes);

    List<Manga> getAllMangas();


}
