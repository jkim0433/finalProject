package com.example.rosario.service;


import com.example.rosario.dto.SellerDto;
import com.example.rosario.entity.Seller;
import com.example.rosario.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SellerService(SellerRepository sellerRepository, PasswordEncoder passwordEncoder) {
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Seller registerSeller(SellerDto sellerDTO) {
        Seller seller = new Seller();
        seller.setSellerNm(sellerDTO.getSellerNm());
        seller.setSellerBirthDt(sellerDTO.getSellerBirthDt());
        seller.setSellerCno(sellerDTO.getSellerCno());
        seller.setSellerAdr(sellerDTO.getSellerAdr());
        seller.setSellerRgtDt(sellerDTO.getSellerRgtDt());
        seller.setSellerEmailAdr(sellerDTO.getSellerEmailAdr());
        seller.setSellerPassword(passwordEncoder.encode(sellerDTO.getSellerPassword()));
        return sellerRepository.save(seller);
    }

    public List<Seller> getAllSellers() {
        //판매자(가게) 목록 전체 조회
        return sellerRepository.findAll();
    }
}

