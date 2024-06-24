package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment_seller")
@Data
public class PaymentSeller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSellerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentId")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId")
    private Seller seller;

}
