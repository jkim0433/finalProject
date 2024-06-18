package com.example.rosario.dto;

import java.util.Date;

public class SubscriptionDto {

    private Long subscribeId;       // 구독 ID
    private Long ordersId;          // 주문 ID (FK)
    private Date subscribeStDt;     // 구독 시작일 (배송 요청 날짜)
    private Long totalNum;          // 주문 총 이용 횟수
    private Long customerId;        // 고객 ID (FK)
    private Long sellerId;          // 판매자 ID (FK)
    private Long deliveryCount;     // 배송 횟수
    private Long remainCount;       // 남은 구독 횟수
}
