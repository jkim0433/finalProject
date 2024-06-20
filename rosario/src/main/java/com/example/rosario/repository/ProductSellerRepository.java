package com.example.rosario.repository;

import com.example.rosario.entity.ProductSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSellerRepository extends JpaRepository<ProductSeller, Long> {
    List<ProductSeller> findBySeller_SellerId(Long sellerId);
}
