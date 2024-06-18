package com.example.rosario.dto;

import com.example.rosario.entity.Customer;
import com.example.rosario.entity.Product;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.Date;


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
