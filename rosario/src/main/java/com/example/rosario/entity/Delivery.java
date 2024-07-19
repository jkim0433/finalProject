package com.example.rosario.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery")
public class Delivery {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // 엔티티의 기본키 값을 자동으로 생성
        private Long deliveryId;       // 배송 ID

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "seller_id", nullable = false)
        private Seller seller;         // 사업주 ID (FK)

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "orders_id", nullable = false)
        private Orders orders;         // 주문 ID (FK)

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;     // 고객 ID (FK)

        @Column(nullable = false)
        private Long deliveryCount;    // 배송 횟수

        @Column(nullable = false)
        private Long deliveryPrice;    // 배송비

        @Column(nullable = false)
        private String deliveryType;   // 배송 방법 (픽업, 배송)

        @Column(nullable = false)
//        @Temporal(TemporalType.DATE)   // 날짜와 시간 타입을 매핑(여기서는 날짜 정보만 포함)
        @Temporal(TemporalType.TIMESTAMP)  // 날짜와 시간 타입을 매핑 (여기서는 날짜와 시간 정보 모두 포함)- 도혜추가
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")   // 도혜추가
        private Date deliveryRqDt;     // 배송 요청 날짜

        @Column
//        @Temporal(TemporalType.DATE)
        @Temporal(TemporalType.TIMESTAMP)  // 날짜와 시간 타입을 매핑 (여기서는 날짜와 시간 정보 모두 포함)- 도혜추가
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")   // 도혜추가
        private Date deliveryCpDt;     // 배송 완료 날짜

        @Column(nullable = false)
        private String deliveryState;  // 배달 상태
}
