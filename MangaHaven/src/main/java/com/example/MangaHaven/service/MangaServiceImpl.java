package com.example.MangaHaven.service;

import com.example.MangaHaven.dto.ImageDto;
import com.example.MangaHaven.dto.MangaDto;
import com.example.MangaHaven.entity.Image;
import com.example.MangaHaven.entity.Manga;
import com.example.MangaHaven.entity.User;
import com.example.MangaHaven.exception.ValidationException;
import com.example.MangaHaven.mapper.MangaMapper;
import com.example.MangaHaven.repository.MangaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MangaServiceImpl implements MangaService {

    //delete create read find
    private final MangaRepository mangaRepository;
    private final ImageService imageService;
    private final UserService userService;
    private final MangaService mangaService;
    private final MangaMapper mangaMapper;


    //poisk

//    public Manga create(Manga manga) {
//        if (manga.getTitle() == null ) {
//            throw new ValidationException();
//        }
//        return mangaRepository.save(manga);
//    }

    public Manga create(MangaDto mangaDto) {
        if (mangaRepository.existsByTitle(mangaDto.getTitle())) {
            throw new ValidationException();
        }
        Manga manga = mangaMapper.toEntity(mangaDto);

        return mangaRepository.save(manga);
    }

    public Manga update(Long id, MangaDto mangaDto) {
        if (mangaRepository.existsByTitleAndIdNot(mangaDto.getTitle(), mangaDto.getId())) {
            throw new ValidationException();
        }
        Manga manga = getById(id);
        manga.setTitle(mangaDto.getTitle());
        manga.setAuthor(mangaDto.getAuthor());
        manga.setDescription(mangaDto.getDescription());
        enrichWithImage(manga, mangaDto.getCoverImage());
        return mangaRepository.save(manga);
    }


    private void enrichWithImage(Manga manga, ImageDto imageDto) {
        Image image = imageService.createImage(imageDto);
        manga.setCoverImage(image);
    }

    public void delete(Long id) {
        Manga manga = getById(id);
        mangaRepository.delete(manga);
    }

    public Manga getById(Long id) {
        return mangaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Manga likeManga(Long idManga) {
        User user = userService.getUserfromDetails();
        Manga manga = mangaService.getById(idManga);
        if (manga.getLikedUser().add(user)) {
            //user.getLikedManga().add(manga);
        } else {
            manga.getLikedUser().remove(user);
            //user.getLikedManga().remove(manga);
        }
        return mangaRepository.save(manga);
    }

    public List<Manga> findGreaterThan(int likes) {

        return mangaRepository.findByLikedGreaterThan(likes);
    }

    public List<Manga> getAllMangas() {
        return mangaRepository.findAll();
    }

}
