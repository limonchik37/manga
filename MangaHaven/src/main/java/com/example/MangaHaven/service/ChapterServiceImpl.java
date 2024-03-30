package com.example.MangaHaven.service;

import com.example.MangaHaven.dto.ChapterDto;
import com.example.MangaHaven.dto.ImageDto;
import com.example.MangaHaven.entity.Chapter;
import com.example.MangaHaven.entity.Image;
import com.example.MangaHaven.entity.Manga;
import com.example.MangaHaven.mapper.ChapterMapper;
import com.example.MangaHaven.repository.ChapterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ChapterServiceImpl implements ChapterService
{

    private final ChapterRepository chapterRepository;
    private final MangaService mangaService;
    private  final ChapterMapper chapterMapper;

    public Chapter create(ChapterDto chapterDto) {
        Chapter chapter = chapterMapper.toEntity(chapterDto);
        Manga manga = mangaService.getById(chapterDto.getIdManga());
        manga.getChapters().add(chapter);
        manga.getChapters().stream().sorted(Comparator.comparing(Chapter::getChapterNumber));
        return chapterRepository.save(chapter);
    }

    public List<String> imageToData(List<ImageDto> images){
        return images.stream().map(ImageDto::getContent).toList();
    }

    public Chapter update(Long id, ChapterDto chapterDto) {
        Chapter chapter = getById(id);
        buildChapter(chapter, chapterDto);
        return chapterRepository.save(chapter);
    }

    public void buildChapter(Chapter newChapter, ChapterDto chapterDto) {
        enrichByModels(newChapter, chapterDto.getIdManga(), imageToData(chapterDto.getImages()), chapterDto.getChapterNumber(), chapterDto.getTitle());

        newChapter.getManga().getChapters().stream()
                .sorted(Comparator.comparing(Chapter::getChapterNumber))
                .filter(chapter -> chapter.getChapterNumber() > chapterDto.getChapterNumber())
                .forEach(chapter -> chapter.setChapterNumber(chapter.getChapterNumber() ));
        newChapter.getManga().getChapters().add(newChapter);
    }

    private void enrichByModels(Chapter chapter,
                                Long mangaId,
                                List<String> imageBase64List,
                                int chapterNumber,
                                String chapterTitle) {
        Manga manga = mangaService.getById(mangaId);
        List<Image> images = imageBase64List.stream()
                .map(imageBase64 -> buildImage(imageBase64, manga.getTitle()))
                .toList();
        chapter.getImages().clear();
        chapter.getImages().addAll(images);
        chapter.setChapterNumber(chapterNumber);
        chapter.setTitle(chapterTitle);
        chapter.setManga(manga);
    }

    private Image buildImage(String imageBase64, String mangaTitle) {
        Image newImage = new Image();
        newImage.setName(mangaTitle + (new Date().getTime()));
        newImage.setContent(imageBase64);
        return newImage;
    }

    public void delete(Long id) {
        Chapter chapter = getById(id);
        chapterRepository.delete(chapter);
    }

    public Chapter getById(Long id) {
        return chapterRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    }
}
