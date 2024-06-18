package com.example.rosario.entity;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long paymentPrice;
    private Date paymentDate;
    private String paymentType;
}
