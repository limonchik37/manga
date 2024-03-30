package com.example.MangaHaven.service;

import com.example.MangaHaven.dto.ImageDto;
import com.example.MangaHaven.entity.Image;

public interface ImageService {
    Image createImage(ImageDto imageDto);
}
