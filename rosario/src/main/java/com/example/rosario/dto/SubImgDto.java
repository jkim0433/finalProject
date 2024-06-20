package com.example.rosario.dto;

import lombok.Data;

@Data
public class SubImgDto {
    private Long sellerImgId;
    private String sellerImg;
    private Long subscribeId;       // 구독 ID(FK)
}
