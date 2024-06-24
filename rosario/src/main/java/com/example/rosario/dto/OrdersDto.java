package com.example.rosario.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class OrdersDto {
    private Long ordersId;
    private String ordersDescription;
    private Long totalNum;
    private Long ordersEA;
    private String ordersAdr;
    private Date ordersDate;
    private Long customerId;
    private Long productId;

    // 아래(도혜 추가 -구독자와 비구독자를 알기 위한 dto)
    private String customerNm;
    private Long totalAmount;

    //구독자와 비구독자를 알기 위한 dto
    public OrdersDto(String customerNm, Date ordersDate, Long totalAmount) {
        this.customerNm = customerNm;
        this.ordersDate = ordersDate;
        this.totalAmount = totalAmount;
    }
}
