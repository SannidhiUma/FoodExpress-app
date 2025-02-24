package com.example.foodexpress.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodexpress.model.PaymentStats;
import com.example.foodexpress.repository.PaymentStatsRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

    @Autowired
    private PaymentStatsRepository paymentStatsRepository;

    public int getTotalPayments() {
        return paymentStatsRepository.findById(1L)
                .map(PaymentStats::getTotalPayments)
                .orElse(0);
    }

    @Transactional
    public void incrementPaymentCount() {
        PaymentStats stats = paymentStatsRepository.findById(1L).orElse(new PaymentStats());
        stats.incrementPayments();
        paymentStatsRepository.save(stats);
        System.out.println("Updated Payment Count: " + stats.getTotalPayments()); // Debugging

    }
}
