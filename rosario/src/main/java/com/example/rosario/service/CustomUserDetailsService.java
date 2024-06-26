package com.example.rosario.service;

import com.example.rosario.auth.CustomUserDetails;
import com.example.rosario.entity.Customer;
import com.example.rosario.entity.Seller;
import com.example.rosario.repository.CustomerRepository;
import com.example.rosario.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public CustomUserDetailsService(CustomerRepository customerRepository, SellerRepository sellerRepository) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 먼저 Customer에서 이메일로 사용자를 찾음
        Customer customer = customerRepository.findByCustomerEmlAdr(email).orElse(null);
        if (customer != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
            return new CustomUserDetails(customer.getCustomerId(), customer.getCustomerEmlAdr(), customer.getCustomerPassword(),
                    Collections.singletonList(authority), null, customer.getCustomerId());
        }

        // Customer에서 찾지 못했을 경우, Seller에서 이메일로 사용자를 찾음
        Seller seller = sellerRepository.findBySellerEmailAdr(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SELLER");
        return new CustomUserDetails(seller.getSellerId(), seller.getSellerEmailAdr(), seller.getSellerPassword(),
                Collections.singletonList(authority), seller.getSellerId(), null);
    }
//    이제, 사용자가 /api/customers/register 엔드포인트로 회원가입 요청을 보내면,
//    CustomerService에서 비밀번호를 암호화하여 저장하고, 이후 Spring Security가 사용자 인증을 처리할 때
//    CustomUserDetailsService를 통해 데이터베이스에서 사용자 정보를 조회합니다.
}
