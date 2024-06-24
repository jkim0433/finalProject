package com.example.rosario.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "product_img")
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImgId;   // 상품이미지 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;         // 상품 ID (FK)

    @Column(nullable = false)
    private String prodFilename;       //  파일 이름

    @Column(nullable = false)
    private String prodFilePath;         //  파일 경로

    public ProductImg() {}

    public ProductImg(Product product, String prodFilename, String prodFilePath) {
        this.product = product;
        this.prodFilename = prodFilename;
        this.prodFilePath = prodFilePath;
    }
}