package com.example.rosario.controller;

import com.example.rosario.auth.CustomUserDetails;
import com.example.rosario.auth.JwtProvider;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/do_login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            logger.info("Login request received for email: {}", loginRequest.getEmail());

            // 사용자 정보를 기반으로 Authentication 객체를 생성
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            logger.info("Authentication token created");

            // AuthenticationManager를 통해 인증을 시도하고, 인증된 Authentication 객체 반환
            Authentication authentication = authenticationManager.authenticate(token);
            logger.info("Authentication successful");

            // SecurityContext에 인증 객체 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("Security context updated");

            // 인증된 사용자의 UserDetails 정보를 가져옴
            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(loginRequest.getEmail());
            logger.info("User details loaded: {}", userDetails.getUsername());

            // JWT 토큰 생성
            String jwtToken = jwtProvider.createToken(userDetails.getUsername());
            logger.info("JWT token created");

            // 사용자 정보와 토큰을 포함한 맵 생성
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("user", userDetails);
            responseMap.put("token", jwtToken);
            responseMap.put("sellerId", userDetails.getSellerId());
            responseMap.put("customerId", userDetails.getCustomerId());

            // 맵을 JSON 문자열로 변환하여 반환
            String responseBody = objectMapper.writeValueAsString(responseMap);
            logger.info("Response body created");

            return ResponseEntity.ok(responseBody);
        } catch (UsernameNotFoundException ex) {
            logger.error("Username not found: {}", loginRequest.getEmail(), ex);
            return ResponseEntity.badRequest().body("Invalid credentials");
        } catch (Exception ex) {
            logger.error("An error occurred during login", ex);
            return ResponseEntity.status(500).body("An error occurred during login");
        }
    }
}
