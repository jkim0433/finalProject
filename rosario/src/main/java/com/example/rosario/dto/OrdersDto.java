package com.example.rosario.dto;

import com.example.rosario.entity.Customer;
import com.example.rosario.entity.Product;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
@Table(name = "orders")
public class OrdersDto {
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
