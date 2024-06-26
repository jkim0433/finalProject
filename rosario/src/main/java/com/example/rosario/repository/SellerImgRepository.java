package com.example.rosario.repository;

import com.example.rosario.entity.Seller;
import com.example.rosario.entity.SellerImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerImgRepository extends JpaRepository<SellerImg, Long> {
    List<SellerImg> findBySellerSellerId(Long SellerId);
    Optional<SellerImg> findBySellerAndSellerFilename(Seller seller, String sellerFilename);

}
