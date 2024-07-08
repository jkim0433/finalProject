package com.example.rosario.service;

import com.example.rosario.dto.CustomerDto;
import com.example.rosario.entity.Customer;
import com.example.rosario.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public boolean isEmailAlreadyRegistered(String email) {
        return customerRepository.existsByCustomerEmlAdr(email);
    }

    // 마이페이지 필요 - 도혜 추가(07.30)
    public CustomerDto getCustomerProfile(String email) {
        Optional<Customer> customerPf = customerRepository.findByCustomerEmlAdr(email);
        if (customerPf.isPresent()) {
            Customer customer = customerPf.get();
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustomerId(customer.getCustomerId());
            customerDto.setCustomerNm(customer.getCustomerNm());
            customerDto.setCustomerBirthDt(customer.getCustomerBirthDt());
            customerDto.setCustomerCno(customer.getCustomerCno());
            customerDto.setCustomerAdr(customer.getCustomerAdr());
            customerDto.setCustomerEmlAdr(customer.getCustomerEmlAdr());
            // 비밀번호는 보안상 포함하지 않습니다.
            return customerDto;
        }
        return null;
    }

}
