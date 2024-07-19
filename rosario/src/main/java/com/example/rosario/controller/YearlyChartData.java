package com.example.rosario.controller;

import com.example.rosario.dto.OrdersDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class YearlyChartData {
    private double subscribedPercentageYear;
    private double unsubscribedPercentageYear;
    private List<OrdersDto> subscribedListByYear;
    private List<OrdersDto> unsubscribedListByYear;


}
