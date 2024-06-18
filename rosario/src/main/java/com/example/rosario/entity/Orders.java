package com.example.rosario.entity;

import jakarta.persistence.*;


import java.util.Date;
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    private String orders;
    private Long totalNum;
    private Long ordersEA;
    private String ordersAdr;
    private Date ordersDate;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
