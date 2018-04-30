package com.example.controllers;

import dto.payment.AddPaymentDto;
import dto.payment.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/getPaymentsForUser")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<PaymentDto> getPaymentsForUser(){
        return paymentService.getPayments();
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void createPayment(@RequestBody AddPaymentDto addPaymentDto){
        paymentService.createPayment(addPaymentDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void updatePayment(@RequestBody PaymentDto paymentDto){
        paymentService.updatePayment(paymentDto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deletePayment(@PathVariable Integer id){
        paymentService.deletePayment(id);
    }
}
