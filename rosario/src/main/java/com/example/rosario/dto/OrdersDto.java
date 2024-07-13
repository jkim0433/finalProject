package com.example.rosario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
//@AllArgsConstructor //모든 필드를 포함하는 생성자를 생성, 오류나서 생략함(도경)
@NoArgsConstructor //기본 생성자를 생성
public class OrdersDto {
    private Long ordersId;
    private String ordersDescription;
    private Long totalNum;
    private Long ordersEA;
    private String ordersAdr;
    private Long customerId;
    private Long productId;

    // 추가된 getTotalNum() 메서드
    public Long getTotalNum() {
        return totalNum != null ? totalNum : 1L; // totalNum이 null일 경우 기본값으로 0을 반환
    }

    // 아래(도혜 추가 -구독자와 비구독자를 알기 위한 dto)
    private String customerNm;
    private Date ordersDate;
    private Long totalAmount;
    private String customerEmlAdr;
    private String subscriptionType; // 구독/일반을 문자열로 저장할 필드
    private Long sellerId;  // 추가된 필드
    //구독자와 비구독자를 알기 위한 dto
    public OrdersDto(Long ordersId,String customerNm, Date ordersDate, Long totalAmount, String subscriptionType ) {
        this.ordersId = ordersId;
        this.customerNm = customerNm;
        this.ordersDate = ordersDate;
        this.totalAmount = totalAmount.longValue();
        this.subscriptionType = subscriptionType;

    }

    //도경추가 모든 orders 내용 불러오기("/api/orders")
    // 생성자: 모든 필드를 포함해야 하지만 table기준으로 항목만듬
    public OrdersDto(Long ordersId, String ordersDescription, Long totalNum, Long ordersEA, String ordersAdr,
                     Long customerId, Long productId, Date ordersDate,
                     Long sellerId) {
        this.ordersId = ordersId;
        this.ordersDescription = ordersDescription;
        this.totalNum = totalNum;
        this.ordersEA = ordersEA;
        this.ordersAdr = ordersAdr;
        this.customerId = customerId;
        this.productId = productId;
        this.ordersDate = ordersDate;
        this.sellerId = sellerId;
    }



}
