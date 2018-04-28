package com.example.controllers;

import dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/getAllPayments")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<PaymentDto> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @GetMapping("/getPayments")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<PaymentDto> getPayments(){
        return paymentService.getPayments();
    }
}
