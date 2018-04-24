package com.example.controllers;

import dto.UserAllotmentDto;
import dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import model.dao.UserRepositoryDAO;
import model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.AllotmentUserService;
import services.UserService;

import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AllotmentUserService allotmentUserService;

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public UserDto getUser(@PathVariable("id") Integer id){
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

    @GetMapping("/getAllActiveUserAllotment")
    public List<UserAllotmentDto> getAllActiveUserAllotment(){
        System.out.println(allotmentUserService.getAllActiveAllotmentUserDto().size());
        return allotmentUserService.getAllActiveAllotmentUserDto();
    }

    @PutMapping("/edit/{id")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String editUser(@PathVariable("id") Integer id, UserDto userDto){
        userService.editUser(userDto);
        return "User edited successfully";
    }
}
