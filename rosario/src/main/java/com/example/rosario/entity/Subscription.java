package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscribe")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscribeId;       // 구독 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders orders;      // 주문 ID (FK)

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date subscribeStDt;     // 구독 시작일 (배송 요청 날짜)

    @Column(nullable = false)
    private Long totalNum;          // 주문 총 이용 횟수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;      // 고객 ID (FK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;          // 판매자 ID (FK)

    @Column(nullable = false)
    private Long deliveryCount;     // 배송 횟수

    @Column(nullable = false)
    private Long remainCount;       // 남은 구독 횟수

    // constructors, getters, setters 생략
}
