package com.example.rosario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor   //기본 생성자를 생성
@AllArgsConstructor   //모든 필드를 포함하는 생성자를 생성
public class ProductDto {

        private Long productId;         // 상품ID
        private String productNm;       // 상품명
        private Long productPrice;      // 상품가격
        private Long productStock;      // 상품재고수량
        private String productSize;     // 상품사이즈 (M, L, XL)



        }
