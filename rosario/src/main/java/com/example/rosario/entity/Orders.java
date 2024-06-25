package com.example.rosario.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "orders_description")  // 데이터베이스 컬럼명과 일치하도록 수정
    private String ordersDescription;

    @Column(name = "total_num")
    private Long totalNum;

    @Column(name = "orders_ea")
    private Long ordersEA;

    @Column(name = "orders_adr")
    private String ordersAdr;

    @Temporal(TemporalType.TIMESTAMP)  // 날짜와 시간 타입을 매핑 (여기서는 날짜와 시간 정보 모두 포함)- 도혜추가
    @JsonFormat(shape  = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")   // 도혜추가
    @Column(name = "orders_date")
    private Date ordersDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "orders")
    private Set<OrderCusPay> orderCusPays;
}
