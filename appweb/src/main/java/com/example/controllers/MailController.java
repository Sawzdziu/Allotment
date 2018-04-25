package com.example.controllers;

import dto.mail.MailBodyDto;
import dto.mail.MailDto;
import dto.mail.NewMailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.MailService;

import java.util.List;

/**
 * Created by piotrsa on 25/04/18.
 */
@RestController
@RequestMapping("/mails")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/getLast")
    public List<MailDto> getLastFiveMails(){
        return mailService.getLastFiveMails();
    }

    @GetMapping("/getAll")
    public List<MailBodyDto> getAllMails(){
        return mailService.getAllMails();
    }

    @PostMapping("/new")
    public void newMail(NewMailDto newMailDto){
        mailService.createNewMail(newMailDto);
    }
}
