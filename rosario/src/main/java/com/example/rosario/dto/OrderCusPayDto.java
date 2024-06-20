package com.example.rosario.dto;

import lombok.Data;

@Data
public class OrderCusPayDto {
    private Long orderCusPayId;
    private Long ordersId;
    private Long customerId;
    private Long paymentId;
}
