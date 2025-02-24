package com.example.foodexpress.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodexpress.service.PaymentService;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/count")
    public int getTotalPayments() {
        return paymentService.getTotalPayments();
    }

    @PostMapping("/increment")
    public void incrementPaymentCount() {
        paymentService.incrementPaymentCount();
    }
}
