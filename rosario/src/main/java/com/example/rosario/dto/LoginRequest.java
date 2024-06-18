package com.example.rosario.dto;

// 로그인 요청에서 전달될 DTO 클래스
public class LoginRequest {
    private String email;
    private String password;

    // 생성자, 게터, 세터
    // ...

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
