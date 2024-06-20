package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seller")
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;             // 판매자ID

    @Column(nullable = false)
    private String sellerNm;           // 판매자이름

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date sellerBirthDt;        // 판매자생년월일

    @Column(nullable = false)
    private Long sellerCno;            // 판매자연락처

    @Column(nullable = false)
    private String sellerAdr;          // 판매자주소

    @Column(nullable = false)
    private Long sellerRgtDt;          // 사업자등록번호

    @Column(nullable = false)
    private String sellerEmailAdr;     // 판매자이메일주소


    private String sellerPassword;  // Add a password field

    @OneToMany(mappedBy = "seller")
    private Set<ProductSeller> productSellers;

    @OneToMany(mappedBy = "seller")
    private Set<PaymentSeller> paymentSellers;
}