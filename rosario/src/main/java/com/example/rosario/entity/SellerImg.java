package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sellerImg")
public class SellerImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerImgId;   // 판매자 이미지 ID

    @Column(nullable = false)
    private String sellerImg;   // 판매자 이미지 URL 또는 파일 경로

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;               // 판매자 ID (FK)
}
