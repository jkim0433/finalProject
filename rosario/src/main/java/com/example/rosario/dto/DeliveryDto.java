package com.example.rosario.dto;

import lombok.*;

import java.util.Date;

@Data  //Getter와 Setter, toString(), equals(), hashCode() 메서드를 자동으로 생성
@NoArgsConstructor   //기본 생성자를 생성
@AllArgsConstructor   //모든 필드를 포함하는 생성자를 생성

public class DeliveryDto {

    private Long deliveryId;       // 배송 ID
    private Long ordersId;         // 주문 ID (FK)
    private Long sellerId;         // 사업주 ID (FK)
    private Long customerId;       // 고객 ID (FK)
    private Long deliveryCount;    // 배송 횟수
    private Long deliveryPrice;    // 배송비
    private String deliveryType;   // 배송 방법 (픽업, 배송)
    private Date deliveryRqDt;     // 배송 요청 날짜
    private Date deliveryCpDt;     // 배송 완료 날짜
    private String deliveryState;  // 배달 상태

    //0701추가
    private String customerNm; //고객이름
    private String ordersDescription; //주문서

}
