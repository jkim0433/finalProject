package com.example.rosario.repository;

import com.example.rosario.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
    List<ProductImg> findByProductProductId(Long productId);

}
