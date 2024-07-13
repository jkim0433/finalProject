package com.example.rosario.service;

import com.example.rosario.dto.OrdersDto;
import com.example.rosario.entity.*;
import com.example.rosario.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// 매출 조회 관련 service
@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductSellerRepository productSellerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    //도경 등록(0712), 모든주문조회
    public List<OrdersDto> getAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();

        // Orders 객체를 OrdersDto로 변환하는 스트림 처리
        return ordersList.stream()
                .map(order -> new OrdersDto(
                        order.getOrdersId(),
                        order.getOrdersDescription(),
                        order.getTotalNum(),
                        order.getOrdersEA(),
                        order.getOrdersAdr(),
                        order.getCustomer().getCustomerId(),
                        order.getProduct().getProductId(),
                        order.getOrdersDate(),
                        order.getSeller().getSellerId()
                ))
                .collect(Collectors.toList());
    }


    // 일별 매출 조회, sellerId추가(도경0712)
    public Long getDailySales(Long sellerId, int year, int month, int day) {
        return ordersRepository.getDailySales(sellerId, year, month, day);
    }

    // 월별 매출 조회, sellerId추가(도경0712)
    public Long getMonthlySales(Long sellerId, int year, int month) {
        return ordersRepository.getMonthlySales(sellerId, year, month);
    }

    // 분기별 매출 조회 메서드
    public Long getQuarterlySales(int year, int quarter) {
        return ordersRepository.getQuarterlySales(year, quarter);
    }

    // 연도별 매출 조회
    public Long getYearlySales(int year) {
        return ordersRepository.getYearlySales(year);
    }

    // 금일 매출표
    public List<OrdersDto> getTodaySalesList(int year, int month, int day) {
        return ordersRepository.findDailySalesList(year, month, day);
    }

    // 일반 주문서를 위한 Dto
    // Product, Customer, Seller 객체를 데이터베이스에서 실제로 조회하여 사용하게 됩니다. 이는 데이터의 일관성을 유지하는 데 도움이 되며,
    // 존재하지 않는 ID를 사용하려고 할 때 오류를 발생시켜 잘못된 데이터 입력을 방지
    public Orders createOrdersFromDto(Long customerId,OrdersDto ordersDto) {
        Orders orders = new Orders();

        // product설정
        Product product = productRepository.findById(ordersDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        orders.setProduct(product);

        // customer 설정
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        orders.setCustomer(customer);


        // Seller 설정
        Seller seller = sellerRepository.findById(ordersDto.getSellerId())  //0710 도혜추가
                .orElseThrow(() -> new RuntimeException("Seller not found for this product"));
        orders.setSeller(seller);

        // 0 OrdersDescription 뽑아져나옴
        orders.setOrdersDescription(ordersDto.getOrdersDescription()); // ordersDescription 설정

        orders.setOrdersEA(ordersDto.getOrdersEA());
        orders.setOrdersAdr(ordersDto.getOrdersAdr());
        orders.setOrdersDate(new Date());

        // totalNum 설정 (구독일 경우 해당 값으로 설정)
        if (ordersDto.getTotalNum() >= 2) {
            orders.setTotalNum(ordersDto.getTotalNum()); // 구독 기간 설정
        } else {
            orders.setTotalNum(1L); // 단일 주문
        }

        // 구독 시작일은 주문일로 설정
        orders.setOrdersDate(new Date());

//        // 남은 구독 횟수 설정 (구독일 경우만)
//        if (ordersDto.getTotalNum() >= 2) {
//            orders.setRemainingSubscriptionCount(ordersDto.getTotalNum() - 1); // 처음 주문은 제외
//        }
        return ordersRepository.save(orders);
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
