package com.example.rosario.controller;

import com.example.rosario.dto.SellerDto;
import com.example.rosario.entity.Seller;
import com.example.rosario.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;

    }

    @PostMapping("/register")
    public ResponseEntity<Seller> registerSeller(@RequestBody SellerDto sellerDTO) {
        Seller registeredSeller = sellerService.registerSeller(sellerDTO);
        return ResponseEntity.ok(registeredSeller);
    }
}

