package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerNm;
    private Date customerBirthDt;
    private Long customerCno;
    private String customerAdr;
    private String customerEmlAdr;
    private String customerPassword;

    @OneToMany(mappedBy = "customer")
    private Set<OrderCusPay> orderCusPays;

    @OneToMany(mappedBy = "customer")
    private Set<CustomerTag> customerTags;

}
