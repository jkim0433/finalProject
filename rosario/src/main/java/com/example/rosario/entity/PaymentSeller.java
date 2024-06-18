package com.example.rosario.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productSeller")
public class PaymentSeller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSellerId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sellerId")
    private Seller seller;

}
