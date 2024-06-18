package com.example.rosario.controller;

import com.example.rosario.dto.LoginRequest;
import com.example.rosario.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/perform_login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 사용자 정보를 기반으로 Authentication 객체를 생성
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            // AuthenticationManager를 통해 인증을 시도하고, 인증된 Authentication 객체 반환
            Authentication authentication = authenticationManager.authenticate(token);

            // SecurityContext에 인증 객체 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 인증된 사용자의 UserDetails 정보를 가져옴
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

            // 추가적인 작업 수행 (예: 토큰 생성, 사용자 정보 반환 등)
            // 이 예제에서는 간단히 성공 메시지를 반환
            return ResponseEntity.ok("Login successful for user: " + userDetails.getUsername());
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("An error occurred during login");
        }
    }
}
