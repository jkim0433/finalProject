package com.example.rosario.controller;

import com.example.rosario.auth.JwtProvider;
import com.example.rosario.dto.OrdersDto;
import com.example.rosario.entity.Customer;
import com.example.rosario.entity.Orders;
import com.example.rosario.entity.Seller;
import com.example.rosario.repository.CustomerRepository;
import com.example.rosario.service.OrdersService;
import com.example.rosario.service.SubscriptionService;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// 매출조회 controller
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    //모든orders내용 조회(도경추가0712)-------------------
    @GetMapping
    public List<OrdersDto> getAllOrders(){return ordersService.getAllOrders();}
   //-------------------------------------------------


    @Autowired
    private JwtProvider jwtProvider; // jwtProvider 필드에 @Autowired 추가
    @Autowired
    private CustomerRepository customerRepository;
    // 일별 매출 조회 API, sellerId추가(도경추가 0712)
    @GetMapping("/term/daily/seller/{sellerId}/{year}/{month}/{day}")
    public ResponseEntity<Long> getDailySales(@PathVariable("sellerId") Long sellerId, @PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day){
        Long dailySales = ordersService.getDailySales(sellerId, year, month, day);
        return ResponseEntity.ok(dailySales);
    }
//    @GetMapping("/term/daily/{year}/{month}/{day}")
//    public Long getDailySales(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day){
//        return ordersService.getDailySales(year,month,day);
//    }



    // 월별 매출 조회 API, sellerId추가(도경 0712)
    @GetMapping("/term/monthly/seller/{sellerId}/{year}/{month}")
    public ResponseEntity<Long> getMonthlySales(@PathVariable("sellerId") Long sellerId, @PathVariable ("year") int year, @PathVariable ("month") int month) {
        Long monthlySales = ordersService.getMonthlySales(sellerId, year, month);
        return ResponseEntity.ok(monthlySales);
    }
//    @GetMapping("/term/monthly/{year}/{month}")
//    public Long getMonthlySales(@PathVariable ("year") int year, @PathVariable ("month") int month) {
//        return ordersService.getMonthlySales(year, month);
//    }


    // 분기별 매출 조회 API
    @GetMapping("/term/quarterly/{year}/{quarter}")
    public Long getQuarterlySales(@PathVariable("year") int year, @PathVariable("quarter") int quarter) {
//        System.out.println("Year: " + year);
//        System.out.println("Quarter: " + quarter);
        return ordersService.getQuarterlySales(year, quarter);
//        System.out.println("Quarterly Sales: " + sales);
//        return sales;
    }

    // 연도별 매출 조회 API
    @GetMapping("/term/yearly/{year}")
    public Long getYearlySales(@PathVariable("year") int year) {
        System.out.println("Year: " + year);
        return ordersService.getYearlySales(year);
//        System.out.println("Yearly Sales: " + sales);
//        return sales;
    }

    // 금일 매출표
    @GetMapping("/term/daily/details/{year}/{month}/{day}")
    public List<OrdersDto> getDailySalesDetails(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day){
        return ordersService.getTodaySalesList(year,month,day);
    }

    // 일반 주문서
//    @PostMapping("/create")
//    public ResponseEntity<Orders> createOrdersFromDTO(@RequestBody OrdersDto ordersDto) {
//        Orders createdOrders = ordersService.createOrdersFromDto(ordersDto);
//        return ResponseEntity.ok(createdOrders);
//    }
    @PostMapping("/create")
    public ResponseEntity<Object> createOrdersFromDTO(@RequestBody OrdersDto ordersDto, HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        if (token != null && jwtProvider.validateToken(token)) {
            String customerEmail = jwtProvider.getUsernameFromToken(token);
            Optional<Customer> optionalCustomer = customerRepository.findByCustomerEmlAdr(customerEmail);
            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                try {
                    Orders order = ordersService.createOrdersFromDto(customer.getCustomerId(), ordersDto);
                    return ResponseEntity.ok(order);
                } catch (RuntimeException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order: " + e.getMessage());
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found with email: " + customerEmail);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}



//json-> requestbody