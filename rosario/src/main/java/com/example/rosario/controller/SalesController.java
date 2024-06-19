package com.example.rosario.controller;

import  com.example.rosario.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rosario/admin/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;
//    // 월별 매출 조회 페이지
//    @GetMapping("/monthly")
//    public String showMonthlySalesPage(Model model) {
//        return "salesPage";
//    }

    // 월별 매출 조회 API
    @GetMapping("/term/monthly")
    public Long getMonthlySales(@RequestParam int year, @RequestParam int month){
        return salesService.getMonthlySales(year, month);
    }

    // 분기별 매출 조회 API
    @GetMapping("/term/quarterly")
    public Long getQuarterlySales(@RequestParam int year, @RequestParam int quarter) {
        return salesService.getQuarterlySales(year, quarter);
    }

    // 연도별 매출 조회 API
    @GetMapping("/term/yearly")
    public Long getYearlySales(@RequestParam int year){
        return salesService.getYearlySales(year);
    }
}
