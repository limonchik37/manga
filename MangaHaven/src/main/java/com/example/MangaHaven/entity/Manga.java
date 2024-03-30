package com.example.MangaHaven.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    @Column(length = Integer.MAX_VALUE)
    private String description;

    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image coverImage;
    @ManyToMany
    @JoinTable(
            name = "user_to_manga",
            joinColumns = @JoinColumn(name = "manga_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> likedUser;

}


