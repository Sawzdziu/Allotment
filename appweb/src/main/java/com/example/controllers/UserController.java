package com.example.controllers;

import dto.UserAllotmentDto;
import dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import model.dao.UserRepositoryDAO;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import services.AllotmentUserService;
import services.UserService;

import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Autowired
    private AllotmentUserService allotmentUserService;

    @Autowired
    private UserService userService;

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

    @GetMapping("/roleuser")
    @PreAuthorize("hasRole('USER')")
    public String user(){
        System.out.println("User permission");
        return "User permission!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){
        System.out.println("Admin permission");
        return "Admin permission!";
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public UserDto getUser(@PathParam("id") Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/allActiveUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getAllActiveUsers(){
        return userService.getAllActiveUsers();
    }

    @GetMapping("/allUsers")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getAllUserAllotment")
    public List<UserAllotmentDto> getAllUserAllotment(){
        System.out.println(allotmentUserService.getAllAllotmentUserDto().size());
        return allotmentUserService.getAllAllotmentUserDto();
    }
}