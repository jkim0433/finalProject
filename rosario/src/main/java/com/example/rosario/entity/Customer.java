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
    //private Long customerCno;  기존 설정

    @Column(name = "customer_cno", nullable = false, length = 20) // length는 필요에 따라 조정
    private String customerCno;   // 도혜 추가

    private String customerAdr;
    private String customerEmlAdr;
    private String customerPassword;

    @OneToMany(mappedBy = "customer")
    private Set<OrderCusPay> orderCusPays;

    @OneToMany(mappedBy = "customer")
    private Set<CustomerTag> customerTags;
    public Customer() {}
    public Customer(Long customerId) {
        this.customerId = customerId;
    }
}
