package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_tag")
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productTagId;   // 상품취향 ID

    @Column(nullable = false)
    private Long productId;      // 상품 ID (FK)

    @Column(nullable = false)
    private Long chipTagId;      // 취향 ID (FK)

}
