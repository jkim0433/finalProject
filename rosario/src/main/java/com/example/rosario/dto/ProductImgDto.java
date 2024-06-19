package com.example.rosario.dto;

import lombok.Data;

@Data
public class ProductImgDto {

    private Long productImgId;  // 상품이미지 ID
    private Long productId;     // 상품 ID (FK)
    private String img;         // 이미지 URL 또는 파일 경로
}
