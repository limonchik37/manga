package com.example.mangabeta.repositories;

import com.example.mangabeta.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MangaRepository extends JpaRepository<Manga, Long> {
    List<Manga> findByTitle (String title);
}
