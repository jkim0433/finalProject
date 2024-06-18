package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_seller")
public class ProductSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSellerId;   // 상품판매자 ID

    @Column(nullable = false)
    private Long productId;         // 상품 ID (FK)

    @Column(nullable = false)
    private Long sellerId;          // 판매자 ID (FK)

}