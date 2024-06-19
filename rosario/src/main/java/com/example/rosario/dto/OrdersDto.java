package com.example.rosario.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class OrdersDto {
    private Long ordersId;
    private String orders;
    private Long totalNum;
    private Long ordersEA;
    private String ordersAdr;
    private Date ordersDate;
    private Long customerId;
    private Long productId;
}
