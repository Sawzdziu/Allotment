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
    public String createNewCommentary(@RequestBody CommentaryDto commentaryDto){
        commentaryService.addCommentary(commentaryDto);
        return "Commentary added successfully!";
    }

    @PutMapping("/edit/{id}")
    public String editCommentary(@PathVariable Integer id,@RequestBody CommentaryDto commentaryDto){
        commentaryService.editCommentary(commentaryDto);
        return "Commentary edited successfully!";
    }

    @GetMapping("/getCommentariesFromArticle/{id}")
    public List<CommentaryDto> getCommentariesFromArticle(@PathVariable Integer id){
        return commentaryService.getCommentariesFromArticle(id);
    }

    @DeleteMapping("/deleteCommentary/{id}")
    public String deleteCommentary(@PathVariable Integer id){
        commentaryService.deleteCommentary(id);
        return "Commentary deleted successfully!";
    }
}
