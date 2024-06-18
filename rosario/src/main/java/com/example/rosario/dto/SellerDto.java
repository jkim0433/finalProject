package com.example.rosario.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class SellerDto {

        private Long sellerId;          // 판매자ID
        private String sellerNm;        // 판매자이름
        private Date sellerBirthDt;     // 판매자생년월일
        private Long sellerCno;         // 판매자연락처
        private String sellerAdr;       // 판매자주소
        private Long sellerRgtDt;       // 사업자등록번호
        private String sellerEmailAdr;  // 판매자이메일주소
        private String sellerPassword;  // 판매자 비밀번호

    }
