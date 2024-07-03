package com.example.rosario.controller;

import com.example.rosario.dto.CustomerDto;
import com.example.rosario.entity.Customer;
import com.example.rosario.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.registerCustomer(customerDto);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/check-email")
    //이메일 중복체크
    public ResponseEntity<Boolean> checkEmail(@RequestParam("email") String email) {
        boolean emailExists = customerService.isEmailAlreadyRegistered(email);
        return ResponseEntity.ok(emailExists);
    }

    // 마이페이지 필요 - 도혜 추가(07.30)
    @GetMapping("/profile")
    public ResponseEntity<CustomerDto> getCustomerProfile(@RequestParam("email") String email) {
        CustomerDto customerProfile = customerService.getCustomerProfile(email);
        if (customerProfile != null) {
            return ResponseEntity.ok(customerProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
