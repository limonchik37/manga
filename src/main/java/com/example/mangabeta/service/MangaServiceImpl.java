package com.example.mangabeta.service;

import com.example.mangabeta.model.Manga;
import com.example.mangabeta.repositories.MangaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MangaServiceImpl{
    private final MangaRepository mangaRepository;
//    private List<Manga> mangas = new ArrayList<>();

    public List<Manga> listMangas(String title){
        List<Manga> mangas = mangaRepository.findAll();
        if(title != null )mangaRepository.findByTitle(title);
        return mangaRepository.findByTitle(title);
    }
    public void saveManga(Manga manga){
        log.info("Saving new manga {}", manga);
        mangaRepository.save(manga);
    }
    public void deleteManga(Long id){
        mangaRepository.deleteById(id);

    }

    public Manga getMangaById(Long id) {
        return mangaRepository.findById(id).orElse(null);
    }
}
