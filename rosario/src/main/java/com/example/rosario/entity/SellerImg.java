package com.example.rosario.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Table(name = "sellerImg")
public class SellerImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerImgId;   // 판매자 이미지 ID

    @Column(nullable = false)
    private String sellerFilename;   // 판매자 이미지  파일 이름

    @Column(nullable = false)
    private String sellerFilePath;   // 판매자 이미지  파일 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;               // 판매자 ID (FK)

    public SellerImg() {};

    public SellerImg(Seller seller, String sellerFilename, String sellerFilePath){
        this.seller = seller;
        this.sellerFilename =sellerFilename;
        this.sellerFilePath = sellerFilePath;
    }
}
