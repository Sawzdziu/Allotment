package com.example.controllers;

import dto.allotmentUser.AllotmentDto;
import dto.AllotmentHistoryDto;
import dto.allotmentUser.UserAllotmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    public List<UserAllotmentDto> getAllAllotments() {
        return allotmentService.getAllAllotments();
    }

    @GetMapping("/getAllActiveAllotments")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserAllotmentDto> getAllActiveAllotments() {
        return allotmentService.getAllActiveAllotments();
    }

    @GetMapping("/getAllFreeAllotments")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserAllotmentDto> getAllFreeAllotments() {
        return allotmentService.getAllNotOccupiedAllotments();
    }

    @GetMapping("/getAllotmentHistory/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public AllotmentHistoryDto getAllotmentHistory(@PathVariable("id") Integer id) {
        return allotmentHistoryService.getAllotmentHistory(id);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String editAllotment(@PathVariable("id") Integer id, @RequestBody AllotmentDto allotmentDto) {
        allotmentService.editAllotment(allotmentDto);
        return "Edited successfully";
    }
}
