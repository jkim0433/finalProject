package com.example.rosario.repository;

import com.example.rosario.entity.CustomerTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTagRepository extends JpaRepository<CustomerTag, Long> {
    List<CustomerTag> findByCustomerCustomerId(Long customerId);

}
