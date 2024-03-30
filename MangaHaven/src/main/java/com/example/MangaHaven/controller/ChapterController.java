package com.example.MangaHaven.controller;

import com.example.MangaHaven.dto.ChapterDto;
import com.example.MangaHaven.entity.Chapter;
import com.example.MangaHaven.entity.Manga;
import com.example.MangaHaven.mapper.ChapterMapper;
import com.example.MangaHaven.service.ChapterService;
import com.example.MangaHaven.service.ChapterServiceImpl;
import com.example.MangaHaven.service.MangaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;
    private final MangaService mangaService;

    @GetMapping("/{id}")
    public ChapterDto get(@PathVariable Long id) {
        Chapter chapter = chapterService.getById(id);
        return chapterMapper.toDto(chapter);
    }


    @PostMapping
    public ChapterDto create(@RequestBody ChapterDto chapterDto) {
        Chapter chapter = chapterService.create(chapterDto);
        return chapterMapper.toDto(chapter);
    }

    @PutMapping("/{idChapter}")
    public ChapterDto update(@PathVariable Long idChapter, @RequestBody ChapterDto chapterDto) {
        Chapter chapter = chapterService.update(idChapter, chapterDto);
        return chapterMapper.toDto(chapter);
    }

    @DeleteMapping("/{idChapter}")
    public ResponseEntity<?> delete(@PathVariable Long idChapter) {
        chapterService.delete(idChapter);
        return ResponseEntity.ok().build();
    }
}
