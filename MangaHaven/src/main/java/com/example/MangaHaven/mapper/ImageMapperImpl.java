package com.example.MangaHaven.mapper;

import com.example.MangaHaven.dto.ImageDto;
import com.example.MangaHaven.entity.Image;
import org.springframework.stereotype.Service;

@Service
public class ImageMapperImpl implements ImageMapper {

    public Image toEntity(ImageDto dto) {
        if (dto == null) {
            return null;
        }
        Image entity = new Image();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setName(dto.getName());
        return entity;
    }

    public ImageDto toDto(Image entity) {
        if (entity == null) {
            return null;
        }
        ImageDto dto = new ImageDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setName(entity.getName());
        return dto;
    }
}
