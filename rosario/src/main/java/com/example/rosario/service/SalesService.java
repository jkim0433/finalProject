package com.example.rosario.service;

import com.example.rosario.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Service
public class SalesService {
    @Autowired
    private OrdersRepository ordersRepository;

    // 월별 매출 조회
    public Long getMonthlySales(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month - 1,1);
        Date startDate = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();

        return ordersRepository.getMonthlySales(startDate,endDate);
//        return ordersRepository.getMonthlySales(year, month);
    }
    // 분기별 매출 조회 메서드
    public Long getQuarterlySales(int year, int quarter){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,(quarter - 1) * 3);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Date startDate = calendar.getTime();

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) +2);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();

        return ordersRepository.getQuarterlySales(startDate,endDate);
    }

    // 연도별 매출 조회
    public Long getYearlySales(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Date endDate = calendar.getTime();

        return ordersRepository.getYearlySales(startDate, endDate);
    }
}


