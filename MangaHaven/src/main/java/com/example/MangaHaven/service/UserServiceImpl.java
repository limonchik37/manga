package com.example.MangaHaven.service;


import com.example.MangaHaven.dto.ImageDto;
import com.example.MangaHaven.dto.UserDto;
import com.example.MangaHaven.entity.Image;
import com.example.MangaHaven.entity.User;
import com.example.MangaHaven.exception.EntityNotFoundException;
import com.example.MangaHaven.exception.ValidationException;
import com.example.MangaHaven.mapper.UserMapper;
import com.example.MangaHaven.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageServiceImpl imageServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;
    private final UserDetailsManager userDetailsManager;

    public User create(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new ValidationException();
        }
        User user = userMapper.toEntity(userDto);
        return userRepository.save(user);
    }

    public User update(Long id, UserDto userDto) {
        if (userRepository.existsByUsernameAndIdNot(userDto.getUsername(), id)) {
            throw new ValidationException();
        }
        User user = userServiceImpl.getById(id);
        updateAuthentication(user, userDto);
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        enrichWithImage(user, userDto.getProfilePhoto());
        return userRepository.save(user);
    }

    private void enrichWithImage(User user, ImageDto imageDto) {
        if (imageDto.getId() == null) {
            Image image = imageServiceImpl.createImage(imageDto);
            user.setProfilePhoto(image);
        }
    }

    private void updateAuthentication(User user, UserDto userDto) {
        if (!Objects.equals(user.getUsername(), userDto.getUsername()) ||
                !Objects.equals(user.getPassword(), userDto.getPassword())) {
            userDetailsManager.deleteUser(user.getUsername());
            UserDetails userDetails = buildUserDetails(userDto.getUsername(), userDto.getPassword(), "USER");
            userDetailsManager.createUser(userDetails);
        }
    }

    public void delete(Long id) {
        User user = userServiceImpl.getById(id);
        userRepository.delete(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User register(UserDto userDto) {
        User user = create(userDto);
        UserDetails userDetails = buildUserDetails(user.getUsername(), user.getPassword(), "USER");
        userDetailsManager.createUser(userDetails);
        return user;
    }

    private UserDetails buildUserDetails(String username, String password, String... roles) {
        return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(password)
                .roles(roles)
                .build();
    }

//    public boolean userExistation(String username , String password){
//        return userRepository.existsByUsernameAndPassword(username, password);
//    }
//
//    public UserDto findByUsername(String username){
//        return userMapper.toDto(userRepository.findByUsername(username));
//    }

    public User getUserfromDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userRepository.findByUsername(userDetails.getUsername())
                    .orElse(null);
        }
        return null;
    }
}
