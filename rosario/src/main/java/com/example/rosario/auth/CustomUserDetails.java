package com.example.rosario.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Long sellerId;
    private Long customerId;

    // Constructor
    public CustomUserDetails(Long id, String username, String password,
                             Collection<? extends GrantedAuthority> authorities,
                             Long sellerId, Long customerId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.sellerId = sellerId;
        this.customerId = customerId;
    }

    // Other methods ...

    public Long getSellerId() {
        return sellerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    // Override necessary methods from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}