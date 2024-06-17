package com.example.rosario.dto;

import java.util.Date;

public class CustomerDto {

    private Long customerId;
    private String customerNm;
    private Date customerBirthDt;
    private Long customerCno;
    private String customerAdr;
    private String customerEmlAdr;
    private String customerPassword;

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }

    public Date getCustomerBirthDt() {
        return customerBirthDt;
    }

    public void setCustomerBirthDt(Date customerBirthDt) {
        this.customerBirthDt = customerBirthDt;
    }

    public Long getCustomerCno() {
        return customerCno;
    }

    public void setCustomerCno(Long customerCno) {
        this.customerCno = customerCno;
    }

    public String getCustomerAdr() {
        return customerAdr;
    }

    public void setCustomerAdr(String customerAdr) {
        this.customerAdr = customerAdr;
    }

    public String getCustomerEmlAdr() {
        return customerEmlAdr;
    }

    public void setCustomerEmlAdr(String customerEmlAdr) {
        this.customerEmlAdr = customerEmlAdr;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}


