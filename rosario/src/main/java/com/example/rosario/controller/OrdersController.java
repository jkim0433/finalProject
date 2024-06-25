package com.example.rosario.controller;

import com.example.rosario.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 매출조회 controller
@RestController
@RequestMapping("/rosario/admin/sales")
public class OrdersController {
    @Autowired
    private OrdersService salesService;

    // 일별 매출 조회 API
    @GetMapping("/term/daily/{year}/{month}/{day}")
    public Long getDailySales(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day){
        return salesService.getDailySales(year,month,day);
    }



    // 월별 매출 조회 API
    @GetMapping("/term/monthly/{year}/{month}")
    public Long getMonthlySales(@PathVariable ("year") int year, @PathVariable ("month") int month) {
        return salesService.getMonthlySales(year, month);
    }


    // 분기별 매출 조회 API
    @GetMapping("/term/quarterly/{year}/{quarter}")
    public Long getQuarterlySales(@PathVariable("year") int year, @PathVariable("quarter") int quarter) {
//        System.out.println("Year: " + year);
//        System.out.println("Quarter: " + quarter);

        return salesService.getQuarterlySales(year, quarter);
//        System.out.println("Quarterly Sales: " + sales);
//        return sales;
    }

    // 연도별 매출 조회 API
    @GetMapping("/term/yearly/{year}")
    public Long getYearlySales(@PathVariable("year") int year) {
        System.out.println("Year: " + year);

        return salesService.getYearlySales(year);
//        System.out.println("Yearly Sales: " + sales);
//        return sales;
    }
}


//json-> requestbody