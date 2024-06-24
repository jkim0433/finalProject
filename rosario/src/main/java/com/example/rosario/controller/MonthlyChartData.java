package com.example.rosario.controller;

import com.example.rosario.dto.OrdersDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 구독자, 비구독자 % ,list 관련 controller
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyChartData {
    private double subscribedPercentage;
    private double unsubscribedPercentage;
    private List<OrdersDto> subscribedList;
    private List<OrdersDto> unsubscribedList;

    // Getter와 Setter 생략
}
