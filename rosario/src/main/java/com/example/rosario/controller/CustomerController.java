package com.example.rosario.controller;

import com.example.rosario.auth.JwtProvider;
import com.example.rosario.dto.CustomerDto;
import com.example.rosario.entity.Customer;
import com.example.rosario.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private JwtProvider jwtProvider; // 도혜추가(07.06)

    @Autowired
    public CustomerController(CustomerService customerService, JwtProvider jwtProvider) {
        this.customerService = customerService;
        this.jwtProvider = jwtProvider; // 도혜추가(07.06)
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
    public ResponseEntity<CustomerDto> getCustomerProfile(@RequestParam("customerEmlAdr") String customerEmlAdr) {
        CustomerDto customerProfile = customerService.getCustomerProfile(customerEmlAdr);
        if (customerProfile != null) {
            return ResponseEntity.ok(customerProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/info")
    public ResponseEntity<CustomerDto> getCustomerInfo(HttpServletRequest request) {
        try {
            String token = jwtProvider.resolveToken(request);
            if (token == null || !jwtProvider.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            String email = jwtProvider.getUsernameFromToken(token);
            CustomerDto customerInfo = customerService.getCustomerProfile(email);
            if (customerInfo != null) {
                return ResponseEntity.ok(customerInfo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // 예기치 못한 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
