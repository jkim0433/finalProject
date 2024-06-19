package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
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

    @OneToMany(mappedBy = "orders")
    private Set<OrderCusPay> orderCusPays;
}
