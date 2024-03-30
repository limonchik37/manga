package com.example.MangaHaven.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int chapterNumber;
    private String title;

    @ManyToOne
    @JoinColumn(name = "manga_id")
    private Manga manga;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "chapter_to_image",
            joinColumns = @JoinColumn(name = "chapter_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> images;
}

