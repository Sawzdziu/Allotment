package com.example.controllers;

import dto.AddEditUserDto;
import dto.allotmentUser.UserAllotmentDto;
import dto.allotmentUser.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.AllotmentUserService;
import services.UserService;

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
    public UserDto getUser(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/allActiveUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getAllActiveUsers() {
        return userService.getAllActiveUsers();
    }

    @GetMapping("/allUsers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getAllUserAllotment")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserAllotmentDto> getAllUserAllotment() {
        System.out.println(allotmentUserService.getAllAllotmentUserDto().size());
        return allotmentUserService.getAllAllotmentUserDto();
    }

    @GetMapping("/getActiveUser")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public UserAllotmentDto getActiveUser(){
        return allotmentUserService.getActiveUser();
    }

    @GetMapping("/getAllActiveUserAllotment")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<UserAllotmentDto> getAllActiveUserAllotment() {
        System.out.println(allotmentUserService.getAllActiveAllotmentUserDto().size());
        return allotmentUserService.getAllActiveAllotmentUserDto();
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String addUser(@RequestBody AddEditUserDto addEditUserDto) {
        userService.addUser(addEditUserDto);
        return "User added successfully";
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String editUser(@PathVariable("id") Integer id, @RequestBody AddEditUserDto addEditUserDto) {
        userService.editUser(addEditUserDto);
        return "User edited successfully";
    }
}
