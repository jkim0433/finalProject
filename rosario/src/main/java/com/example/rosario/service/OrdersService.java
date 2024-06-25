package com.example.rosario.service;

import com.example.rosario.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 매출 조회 관련 service
@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    // 일별 매출 조회
    public Long getDailySales(int year, int month, int day) {
        return ordersRepository.getDailySales(year, month, day);
    }

    // 월별 매출 조회
    public Long getMonthlySales(int year, int month) {
        return ordersRepository.getMonthlySales(year, month);
    }

    // 분기별 매출 조회 메서드
    public Long getQuarterlySales(int year, int quarter) {
        return ordersRepository.getQuarterlySales(year, quarter);
    }
    // 연도별 매출 조회
    public Long getYearlySales(int year) {
        return ordersRepository.getYearlySales(year);
    }

}

//    // 월별 매출 조회
//    public Long getMonthlySales(int year, int month){
////        Calendar calendar = Calendar.getInstance();
////        calendar.set(year, month - 1, 1);
////        Date startDate = calendar.getTime();
////        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
////        Date endDate = calendar.getTime();
////
////        System.out.println("Start Date: " + startDate);
////        System.out.println("End Date: " + endDate);
////
////        Long sales = ordersRepository.getMonthlySales(startDate, endDate);
////        System.out.println("Monthly Sales: " + sales);
////        return sales;
//
//           return ordersRepository.getMonthlySales(year, month);
//
//    }
//
//    // 분기별 매출 조회 메서드
//    public Long getQuarterlySales(int year, int quarter){
////        Calendar calendar = Calendar.getInstance();
////        calendar.set(Calendar.YEAR, year);
////        calendar.set(Calendar.MONTH, (quarter - 1) * 3);
////        calendar.set(Calendar.DAY_OF_MONTH, 1);
////        Date startDate = calendar.getTime();
////        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 2);
////        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
////        Date endDate = calendar.getTime();
////
////        System.out.println("Start Date: " + startDate);
////        System.out.println("End Date: " + endDate);
//
//        return ordersRepository.getQuarterlySales(year, quarter);
////        System.out.println("Quarterly Sales: " + sales);
////        return sales;
//    }
//
//    // 연도별 매출 조회
//    public Long getYearlySales(int year) {
////        Calendar calendar = Calendar.getInstance();
////        calendar.set(Calendar.YEAR, year);
////        calendar.set(Calendar.MONTH, Calendar.JANUARY);
////        calendar.set(Calendar.DAY_OF_MONTH, 1);
////        Date startDate = calendar.getTime();
////        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
////        calendar.set(Calendar.DAY_OF_MONTH, 31);
////        Date endDate = calendar.getTime();
////
////        System.out.println("Start Date: " + startDate);
////        System.out.println("End Date: " + endDate);
////
//        return ordersRepository.getYearlySales(year);
////        System.out.println("Yearly Sales: " + sales);
////        return sales;
//    }
//}
