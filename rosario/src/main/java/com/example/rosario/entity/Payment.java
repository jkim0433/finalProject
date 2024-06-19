package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Long paymentPrice;
    private Date paymentDate;
    private String paymentType;

    @OneToMany(mappedBy = "payment")
    private Set<PaymentSeller> paymentSellers;

    @OneToMany(mappedBy = "payment")
    private Set<OrderCusPay> orderCusPays;
}
