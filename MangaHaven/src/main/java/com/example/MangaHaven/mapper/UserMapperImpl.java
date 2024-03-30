package com.example.MangaHaven.mapper;

import com.example.MangaHaven.dto.ImageDto;
import com.example.MangaHaven.dto.UserDto;
import com.example.MangaHaven.entity.Image;
import com.example.MangaHaven.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final ImageMapper imageMapper;

    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
        User entity = new User();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        Image image = imageMapper.toEntity(dto.getProfilePhoto());
        entity.setProfilePhoto(image);
        return entity;
    }

    public UserDto toDto(User entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        ImageDto imageDto = imageMapper.toDto(entity.getProfilePhoto());
        dto.setProfilePhoto(imageDto);
        return dto;
    }
}
