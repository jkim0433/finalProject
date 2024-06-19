package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_img")
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImgId;   // 상품이미지 ID

    @Column(nullable = false, name = "product_id")
    private Product product;      // 상품 ID (FK)

    @Column(nullable = false)
    private String img;          // 이미지 URL 또는 파일 경로
}