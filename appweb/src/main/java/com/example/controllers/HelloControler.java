package com.example.controllers;

import lombok.extern.slf4j.Slf4j;
import model.dao.UserRepositoryDAO;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloControler {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @GetMapping("/world")
    public String hello() throws Exception {
        System.out.println("TEST");
        User user = userRepositoryDAO.findById(1);
        System.out.println(userRepositoryDAO.findById(1).toString());
        log.info("Hello controller info");
        return "Hello World!";
    }

    @GetMapping("/working")
    public String working(){
        System.out.println("Working");
        return "Working!";
    }
}
