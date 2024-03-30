package com.example.MangaHaven.service;

import com.example.MangaHaven.dto.ImageDto;
import com.example.MangaHaven.entity.Image;
import com.example.MangaHaven.mapper.ImageMapper;
import com.example.MangaHaven.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService
{
    private final ImageRepository imageRepository ;
    private final ImageMapper imageMapper;


    public Image createImage(ImageDto imageDto) {
        Image image = imageMapper.toEntity(imageDto);
        return imageRepository.save(image);
    }
}
