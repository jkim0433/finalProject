package com.example.rosario.dto;

public class ProductImgDto {

    private Long productimgId;  // 상품이미지 ID
    private Long productId;     // 상품 ID (FK)
    private String img;         // 이미지 URL 또는 파일 경로
}
