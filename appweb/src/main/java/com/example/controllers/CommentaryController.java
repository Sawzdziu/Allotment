package com.example.controllers;

import dto.article.CommentaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.CommentaryService;

import java.util.List;

@RestController
@RequestMapping("/commentaries")
public class CommentaryController {

    @Autowired
    private CommentaryService commentaryService;

    @PostMapping("/new")
    public void createNewCommentary(@RequestBody CommentaryDto commentaryDto){
        commentaryService.addCommentary(commentaryDto);
    }

    @PutMapping("/edit")
    public void editCommentary(@RequestBody CommentaryDto commentaryDto){
        commentaryService.editCommentary(commentaryDto);
    }

    @GetMapping("/getCommentariesFromArticle/{id}")
    public List<CommentaryDto> getCommentariesFromArticle(@PathVariable Integer id){
        return commentaryService.getCommentariesFromArticle(id);
    }
}
