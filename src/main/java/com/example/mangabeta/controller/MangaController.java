package com.example.mangabeta.controller;

import com.example.mangabeta.model.Manga;
import com.example.mangabeta.service.MangaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class MangaController {

    private final MangaServiceImpl mangaService;


    @GetMapping("/")
    public String mangas(@RequestParam (name ="title" , required = false) String title, Model model) {
        model.addAttribute("mangas", mangaService.listMangas(title) );
        return "manga";
    }
    @GetMapping("/manga/{id}")
    public String MangaInfo(@PathVariable Long id,Model model ){
        model.addAttribute("manga", mangaService.getMangaById(id));
        return "manga_info";
    }
    @PostMapping("/manga/create")
    public String createManga(Manga manga){
        mangaService.saveManga(manga);
        return "redirect:/";
    }

    @PostMapping("/manga/delete/{id}")
    public String deleteManga(@PathVariable Long id){
        mangaService.deleteManga(id);
        return "redirect:/";
    }
}
