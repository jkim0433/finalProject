package com.example.rosario.repository;

import com.example.rosario.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findBySeller_SellerId(Long sellerId);

}
