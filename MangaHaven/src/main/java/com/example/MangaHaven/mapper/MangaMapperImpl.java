package com.example.MangaHaven.mapper;

import com.example.MangaHaven.dto.ChapterDto;
import com.example.MangaHaven.dto.ImageDto;
import com.example.MangaHaven.dto.MangaDto;
import com.example.MangaHaven.entity.Image;
import com.example.MangaHaven.entity.Manga;
import com.example.MangaHaven.entity.User;
import com.example.MangaHaven.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MangaMapperImpl implements MangaMapper {

    private final ImageMapper imageMapper;
    private final ChapterMapper chapterMapper;
    private final UserService userService;

    public Manga toEntity(MangaDto dto) {
        Manga entity = new Manga();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setAuthor(dto.getAuthor());
        Image image = imageMapper.toEntity(dto.getCoverImage());
        entity.setCoverImage(image);
        return entity;
    }

    public MangaDto toDto(Manga entity) {
        MangaDto dto = new MangaDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setAuthor(entity.getAuthor());
        ImageDto imageDto = imageMapper.toDto(entity.getCoverImage());
        dto.setCoverImage(imageDto);
        if(entity.getChapters() != null) {
            List<ChapterDto> chapters = entity.getChapters().stream()
                    .map(chapterMapper::toDto)
                    .toList();
            dto.setChapters(chapters);
        }
        dto.setLikes(entity.getLikedUser().size());
        User user = userService.getUserfromDetails();
        if (user != null) {
            dto.setLiked(entity.getLikedUser().contains(user));
        }
        return dto;
    }
}
