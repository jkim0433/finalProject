package com.example.rosario.dto;

import lombok.Data;

@Data
public class SellerImgDto {
    private Long sellerImgId;
    private String sellerImg;
    private Long sellerId;     // 판매자 ID (FK)
}
