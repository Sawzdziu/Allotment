package com.example.controllers;

import dto.article.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getLast")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<ArticleDto> getLastFiveMails(){
        return articleService.getLastFiveArticles();
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<ArticleDto> getAllMails(){
        return articleService.getAllArticles();
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void createNewAritcle(@RequestBody ArticleDto articleDto){
        articleService.createNewArticle(articleDto);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void editArticle(@PathVariable Integer id, @RequestBody ArticleDto articleDto){
        articleService.editArticle(articleDto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void deleteArticle(@PathVariable Integer id){
        articleService.deleteArticle(id);
    }
}
