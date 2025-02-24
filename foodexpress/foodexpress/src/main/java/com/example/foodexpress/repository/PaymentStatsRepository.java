package com.example.foodexpress.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.foodexpress.model.PaymentStats;

@Repository
public interface PaymentStatsRepository extends JpaRepository<PaymentStats, Long> {
}
