package com.example.MangaHaven.repository;

import com.example.MangaHaven.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String title);

    boolean existsByUsernameAndIdNot(String title, Long id);
    boolean existsByUsernameAndPassword(String usename , String password);
    Optional<User> findByUsername (String username);
}
