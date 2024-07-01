package com.example.rosario.repository;

import com.example.rosario.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    // sellerId 찾기
//    List<Subscription> findBySellerId(Long sellerId);
    List<Subscription> findBySeller_SellerId(Long sellerId);
}
