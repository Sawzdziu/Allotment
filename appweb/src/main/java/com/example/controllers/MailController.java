package com.example.controllers;

import dto.mail.MailBodyDto;
import dto.mail.MailDto;
import dto.mail.NewMailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<MailDto> getLastFiveMails(){
        return mailService.getLastFiveMails();
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<MailBodyDto> getAllMails(){
        return mailService.getAllMails();
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void newMail(@RequestBody NewMailDto newMailDto){
        mailService.createNewMail(newMailDto);
    }
}