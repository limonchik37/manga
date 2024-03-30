package com.example.MangaHaven.mapper;

import com.example.MangaHaven.dto.ChapterDto;
import com.example.MangaHaven.dto.ImageDto;
import com.example.MangaHaven.entity.Chapter;
import com.example.MangaHaven.entity.Image;
import com.example.MangaHaven.service.MangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterMapperImpl implements ChapterMapper {

    private final ImageMapper imageMapper;
    private final MangaService mangaService;

    public Chapter toEntity(ChapterDto dto) {
        Chapter entity = new Chapter();
        entity.setId(dto.getId());
        List<Image> images = dto.getImages().stream()
                .map(imageMapper::toEntity)
                .toList();
        entity.setChapterNumber(dto.getChapterNumber());
        entity.setTitle(dto.getTitle());
        entity.setImages(images);
        entity.setManga(mangaService.getById(dto.getIdManga()));

        return entity;
    }

    public ChapterDto toDto(Chapter entity) {

        ChapterDto dto = new ChapterDto();
        dto.setId(entity.getId());
        List<ImageDto> images = entity.getImages().stream()
                .map(imageMapper::toDto)
                .toList();
        dto.setChapterNumber(entity.getChapterNumber());
        dto.setTitle(entity.getTitle());
        dto.setImages(images);
        dto.setIdManga(entity.getManga().getId());

        return dto;
    }

}
