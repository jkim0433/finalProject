package com.example.rosario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {
    private Long ordersId;
    private String ordersDescription;
    private Long totalNum;
    private Long ordersEA;
    private String ordersAdr;
    private Long customerId;
    private Long productId;

    // 아래(도혜 추가 -구독자와 비구독자를 알기 위한 dto)
    private String customerNm;
    private Date ordersDate;
    private Long totalAmount;

    private String subscriptionType; // 구독/일반을 문자열로 저장할 필드
    private Long sellerId;  // 추가된 필드
    //구독자와 비구독자를 알기 위한 dto
    public OrdersDto(Long ordersId,String customerNm, Date ordersDate, Long totalAmount, String subscriptionType ) {
        this.ordersId = ordersId;
        this.customerNm = customerNm;
        this.ordersDate = ordersDate;
        this.totalAmount = totalAmount.longValue(); ;
        this.subscriptionType = subscriptionType;

    }


}
