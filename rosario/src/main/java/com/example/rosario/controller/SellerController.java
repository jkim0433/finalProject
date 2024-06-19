package com.example.rosario.controller;

import com.example.rosario.dto.SellerDto;
import com.example.rosario.entity.Seller;
import com.example.rosario.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;

    }

    @GetMapping
    public List<Seller> getAllSellers() {
        //전체 판매자(가게) 조회)
        return sellerService.getAllSellers();
    }

    @PostMapping("/register")
    //판매자(가게) 회원가입
    public ResponseEntity<Seller> registerSeller(@RequestBody SellerDto sellerDTO) {
        Seller registeredSeller = sellerService.registerSeller(sellerDTO);
        return ResponseEntity.ok(registeredSeller);
    }
}

