package com.example.controllers;

import dto.payment.AddPaymentDto;
import dto.payment.EditPaymentDto;
import dto.payment.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.PaymentService;
import services.pdf.PDFService;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PDFService pdfService;

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
    public String createPayment(@RequestBody AddPaymentDto addPaymentDto){
        paymentService.createPayment(addPaymentDto);
        return "Payment added successfully!";
    }

    @PutMapping("/confirmPayment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String updatePayment(@PathVariable Integer id){
        paymentService.confirmPayment(id);
        return "Payment confirmed!";
    }

    @PutMapping("/declinePayment/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String declinePayment(@PathVariable Integer id){
        paymentService.declinePayment(id);
        return "Payment declined!";
    }

    @GetMapping("/generatePDF/{id}")
    public byte[] generatePDF(@PathVariable Integer id){
        return pdfService.createDocument(id).toByteArray();
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String updatePayment(@RequestBody EditPaymentDto editPaymentDto){
        paymentService.updatePayment(editPaymentDto);
        return "Payment updated successfully!";
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String deletePayment(@PathVariable Integer id){
        paymentService.deletePayment(id);
        return "Payment deleted successfully!";
    }
}
