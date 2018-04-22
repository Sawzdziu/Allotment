package com.example.controllers;

import dto.AllotmentDto;
import dto.AllotmentHistoryDto;
import dto.UserAllotmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.AllotmentHistoryService;
import services.AllotmentService;

import java.util.List;


@RestController
@RequestMapping("/allotment")
public class AllotmentController {

    @Autowired
    private AllotmentService allotmentService;

    @Autowired
    private AllotmentHistoryService allotmentHistoryService;

    @GetMapping("/getAllAllotments")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserAllotmentDto> getAllAllotments(){
        return allotmentService.getAllAllotments();
    }

    @GetMapping("/getAllotmentHistory/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public AllotmentHistoryDto getAllotmentHistory(@PathVariable("id") Integer id){
        return allotmentHistoryService.getAllotmentHistory(id);
    }


}
