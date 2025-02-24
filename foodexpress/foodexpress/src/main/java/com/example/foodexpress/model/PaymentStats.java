package com.example.foodexpress.model;



import jakarta.persistence.*;

@Entity
@Table(name = "payment_stats")
public class PaymentStats {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_payments", nullable = false)
    private int totalPayments = 0;

    // Constructors
    public PaymentStats() {}

    public PaymentStats(int totalPayments) {
        this.totalPayments = totalPayments;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public int getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(int totalPayments) {
        this.totalPayments = totalPayments;
    }

    public void incrementPayments() {
        this.totalPayments += 1;
    }
}
