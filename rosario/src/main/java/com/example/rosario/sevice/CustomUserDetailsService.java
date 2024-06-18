package com.example.rosario.sevice;

import com.example.rosario.entity.Customer;
import com.example.rosario.repository.CustomerRepository;
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

    @Autowired
    public CustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCustomerEmlAdr(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        return new User(customer.getCustomerEmlAdr(), customer.getCustomerPassword(), Collections.singletonList(authority));
    }
//    이제, 사용자가 /api/customers/register 엔드포인트로 회원가입 요청을 보내면,
//    CustomerService에서 비밀번호를 암호화하여 저장하고, 이후 Spring Security가 사용자 인증을 처리할 때
//    CustomUserDetailsService를 통해 데이터베이스에서 사용자 정보를 조회합니다.
}
