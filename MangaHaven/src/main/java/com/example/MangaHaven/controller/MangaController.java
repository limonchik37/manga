package com.example.MangaHaven.controller;

import com.example.MangaHaven.dto.ChapterDto;
import com.example.MangaHaven.dto.MangaDto;
import com.example.MangaHaven.entity.Manga;
import com.example.MangaHaven.mapper.MangaMapper;
import com.example.MangaHaven.service.ChapterService;
import com.example.MangaHaven.service.MangaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/mangas")
@RequiredArgsConstructor
public class MangaController {

    private final MangaService mangaService;
    private final MangaMapper mangaMapper;
    private final ChapterService chapterService;

    @GetMapping("/{id}") // GET http://localhost:8080/api/mangas/5
    public MangaDto get(@PathVariable Long id) {
        Manga manga = mangaService.getById(id);
        return mangaMapper.toDto(manga);
    }

    @GetMapping("/{id}/chapters") // GET http://localhost:8080/api/mangas/5
    public List<ChapterDto> getAllChapters(@PathVariable Long id) {
        Manga manga = mangaService.getById(id);
        return mangaMapper.toDto(manga).getChapters();
    }


    @PostMapping // POST http://localhost:8080/api/mangas
    public MangaDto create(@RequestBody MangaDto mangaDto) {
        Manga manga = mangaService.create(mangaDto);
        return mangaMapper.toDto(manga);
    }


    @PutMapping("/{id}") // PUT http://localhost:8080/api/mangas/5
    public MangaDto update(@PathVariable Long id, @RequestBody MangaDto mangaDto) {
        Manga manga = mangaService.update(id, mangaDto);
        return mangaMapper.toDto(manga);
    }

    @DeleteMapping("/{id}") // DELETE http://localhost:8080/api/mangas/5
    public ResponseEntity<?> delete(@PathVariable Long id) {
        mangaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idManga}/likes")
    public MangaDto like(@PathVariable Long idManga) {
        Manga manga = mangaService.likeManga(idManga);
        return mangaMapper.toDto(manga);
    }

    @GetMapping("/filter")
    public List<MangaDto> findGreaterThan(@RequestParam int likes) {
        return mangaService.findGreaterThan(likes).stream().map(
                mangaMapper::toDto
        ).toList();
    }

    @GetMapping
    public List<MangaDto> getAll() {
        return mangaService.getAllMangas().stream().map(mangaMapper::toDto).toList();
    }


//    @GetMapping
//    public List<MangaDto> findBytitle(@RequestParam String title) {
//        return mangaService.findByTitle(title).stream().map(
//                mangaMapper::toDto
//        ).toList();
//    }
}
