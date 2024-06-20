package com.example.rosario.repository;

import com.example.rosario.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerEmlAdr(String email);

    boolean existsByCustomerEmlAdr(String customerEmlAdr);
}
