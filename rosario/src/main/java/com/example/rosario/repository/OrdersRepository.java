package com.example.rosario.repository;

import com.example.rosario.dto.OrdersDto;
import com.example.rosario.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 매출량 조회와 구독자 및 비구독자 %, 리스트 쿼리문 repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    // 하루의 매출량 조회쿼리
    @Query("SELECT SUM(o.ordersEA * p.productPrice) " +
            "FROM Orders o " +
            "JOIN o.product p " +
            "WHERE FUNCTION('YEAR', o.ordersDate) = :year AND FUNCTION('MONTH', o.ordersDate) = :month AND FUNCTION('DAY', o.ordersDate) = :day")
    Long getDailySales(@Param("year") int year, @Param("month") int month, @Param("day") int day);

    // 한달의 매출량 조회쿼리
    @Query("SELECT SUM(o.ordersEA *  p.productPrice) " +
            "FROM Orders o " +
            "JOIN o.product p " +
            "WHERE FUNCTION('YEAR', o.ordersDate) = :year AND FUNCTION('MONTH', o.ordersDate) = :month")
    Long getMonthlySales(@Param("year") int year, @Param("month") int month);

    // 분기의 매출량 조회쿼리
    @Query("SELECT SUM(o.ordersEA *  p.productPrice) " +
            "FROM Orders o " +
            "JOIN o.product p " +
            "WHERE FUNCTION('YEAR', o.ordersDate) = :year AND FUNCTION('QUARTER', o.ordersDate) = :quarter")
    Long getQuarterlySales(@Param("year") int year, @Param("quarter") int quarter);

    //년도의 매출량 조회쿼리
    @Query("SELECT SUM(o.ordersEA *  p.productPrice) " +
            "FROM Orders o " +
            "JOIN o.product p " +
            "WHERE FUNCTION('YEAR', o.ordersDate) = :year")
    Long getYearlySales(@Param("year") int year);


    // 금일 매출표
    @Query("SELECT NEW com.example.rosario.dto.OrdersDto(o.ordersId,c.customerNm, o.ordersDate, o.ordersEA * p.productPrice, CASE WHEN o.totalNum >= 2  THEN '구독' ELSE '일반' END) " +
            "FROM Orders o " +
            "JOIN o.product p " +
            "JOIN o.customer c " +
            "WHERE FUNCTION('YEAR', o.ordersDate) = :year AND FUNCTION('MONTH', o.ordersDate) = :month AND FUNCTION('DAY', o.ordersDate) = :day")
    List<OrdersDto> findDailySalesList(@Param("year") int year, @Param("month") int month,  @Param("day") int day);


    // ----------------- 구독과 관련된 쿼리 --------------------
    // 구독자의 퍼센트 계산
    @Query(value = "SELECT " +
            "ROUND((SUM(CASE WHEN o.total_num >= 2 THEN 1 ELSE 0 END) * 100.0 / COUNT(*)), 2) AS subscribed_percentage, " +
            "ROUND((SUM(CASE WHEN o.total_num = 1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*)), 2) AS unsubscribed_percentage " +
            "FROM orders o " +
            "WHERE YEAR(o.orders_date) = :year AND MONTH(o.orders_date) = :month", nativeQuery = true)
    Double calculateSubscriptionPercentage(@Param("year") int year, @Param("month") int month);

    // 구독자 리스트
    @Query("SELECT NEW com.example.rosario.dto.OrdersDto(o.ordersId,c.customerNm, o.ordersDate, o.ordersEA * p.productPrice, CASE WHEN o.totalNum >= 2 THEN '구독' ELSE '일반' END ) " +
            "FROM Orders o " +
            "JOIN o.product p " +
            "JOIN o.customer c " +
            "WHERE FUNCTION('YEAR', o.ordersDate) = :year AND FUNCTION('MONTH', o.ordersDate) = :month AND o.totalNum >= 2")
    List<OrdersDto> findSubscribedList(@Param("year") int year, @Param("month") int month);

    // 비구독자 리스트
    @Query("SELECT NEW com.example.rosario.dto.OrdersDto(o.ordersId,c.customerNm, o.ordersDate, o.ordersEA * p.productPrice ,CASE WHEN o.totalNum >= 2  THEN '구독' ELSE '일반' END) " +
            "FROM Orders o " +
            "JOIN o.product p " +
            "JOIN o.customer c " +
            "WHERE FUNCTION('YEAR', o.ordersDate) = :year AND FUNCTION('MONTH', o.ordersDate) = :month AND o.totalNum = 1")
    List<OrdersDto> findUnsubscribedList(@Param("year") int year, @Param("month") int month);

// 아래 두 메서드는 각각 구독된 고객 수와 구독하지 않는 고객 수를 세는 것이 목적이였음
//    @Query("SELECT COUNT(s) FROM Subscription s WHERE YEAR(s.subscribeStDt) = :year AND MONTH(s.subscribeStDt) = :month AND s.customer.id IS NOT NULL")
//    Long countSubscribedCustomers(@Param("year") int year, @Param("month") int month);
//
//    @Query("SELECT COUNT(s) FROM Subscription s WHERE YEAR(s.subscribeStDt) = :year AND MONTH(s.subscribeStDt) = :month AND s.customer.id IS NULL")
//    Long countUnsubscribedCustomers(@Param("year") int year, @Param("month") int month);

}
