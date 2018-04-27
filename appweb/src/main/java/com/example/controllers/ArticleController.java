package com.example.controllers;

import dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getLast")
    public List<ArticleDto> getLastFiveMails(){
        return articleService.getLastFiveArticles();
    }

    @GetMapping("/getAll")
    public List<ArticleDto> getAllMails(){
        return articleService.getAllArticles();
    }
}
