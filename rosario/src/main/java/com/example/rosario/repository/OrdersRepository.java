package com.example.rosario.repository;

import com.example.rosario.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
//    @Query("SELECT SUM(o.ordersEA) FROM Order o WHERE o.productId = :productId")
//        Long getTotalSalesByProductId(@Param("productId") Long productId);
//
//    List<Orders> findByCustomerId(Long customerId);
//    @Query("SELECT SUM(o.ordersEA) FROM Orders o WHERE MONTH(o.ordersDate) = :month AND YEAR(o.ordersDate) = :year")
//    Long getMonthlySales(@Param("year") int year, @Param("month") int month);

    @Query("SELECT SUM(o.ordersEA) FROM Orders o WHERE o.ordersDate BETWEEN :startDate AND :endDate")
    Long getMonthlySales(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(o.ordersEA) FROM Orders o WHERE o.ordersDate BETWEEN :startDate AND :endDate")
    Long  getQuarterlySales(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(o.ordersEA) FROM Orders o WHERE o.ordersDate BETWEEN :startDate AND :endDate")
    Long getYearlySales(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
