package com.example.MangaHaven.repository;

import com.example.MangaHaven.entity.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface MangaRepository extends JpaRepository< Manga , Long> {

    boolean existsByTitle(String title);

    boolean existsByTitleAndIdNot(String title, Long id);

    @Query(value = "SELECT p FROM Manga p WHERE size(p.likedUser) > :likes")
    List<Manga> findByLikedGreaterThan(int likes);

//    List<Manga> findByTitle(String Title);


}
