package com.example.MangaHaven.service;

import com.example.MangaHaven.dto.UserDto;
import com.example.MangaHaven.entity.User;

import java.util.List;

public interface UserService {
    User create(UserDto userDto);

    User update(Long id, UserDto userDto);

    void delete(Long id);

    User getById(Long id);

    List<User> findAll();

    User register(UserDto userDto);

    User getUserfromDetails();
}
