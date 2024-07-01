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
    
    @Column(name = "customer_nm")
    private String customerNm;  // 도혜 추가(6/30)
    
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

    @OneToMany(mappedBy = "customer")
    private Set<Subscription> subscriptions;
    // 구독 정보와의 관계 추가(도혜추가 6/30)

    public Customer() {}
    public Customer(Long customerId) {
        this.customerId = customerId;
    }
}
