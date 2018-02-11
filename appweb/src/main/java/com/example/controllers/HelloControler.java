package com.example.controllers;

import model.dao.UserRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @GetMapping("/world")
    public String hello(){
        System.out.println("TEST");
//        System.out.println(userRepositoryDAO.findAll());
        return "Hello World!";
    }
}
