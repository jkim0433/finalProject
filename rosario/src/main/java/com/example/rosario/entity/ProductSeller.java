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

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;         // 상품 ID (FK)

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;          // 판매자 ID (FK)
}
