package com.example.MangaHaven.controller;

import com.example.MangaHaven.dto.UserDto;
import com.example.MangaHaven.entity.User;
import com.example.MangaHaven.mapper.UserMapper;
import com.example.MangaHaven.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        User user = userService.create(userDto);
        return userMapper.toDto(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userService.update(id, userDto);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registration")
    public UserDto register(@RequestBody UserDto userDto) {
        User user = userService.register(userDto);
        return userMapper.toDto(user);
    }

    @GetMapping("/user")
    public UserDto getUserDetails() {
        return userMapper.toDto(userService.getUserfromDetails());
    }

}
