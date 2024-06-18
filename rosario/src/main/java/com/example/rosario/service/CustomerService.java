package com.example.rosario.service;

import com.example.rosario.dto.CustomerDto;
import com.example.rosario.entity.Customer;
import com.example.rosario.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer registerCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerNm(customerDto.getCustomerNm());
        customer.setCustomerBirthDt(customerDto.getCustomerBirthDt());
        customer.setCustomerCno(customerDto.getCustomerCno());
        customer.setCustomerAdr(customerDto.getCustomerAdr());
        customer.setCustomerEmlAdr(customerDto.getCustomerEmlAdr());
        customer.setCustomerPassword(passwordEncoder.encode(customerDto.getCustomerPassword()));

        return customerRepository.save(customer);
    }
}
