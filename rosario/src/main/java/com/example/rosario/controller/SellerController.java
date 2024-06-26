package com.example.rosario.controller;

import com.example.rosario.dto.ProductDetailDto;
import com.example.rosario.dto.SellerDto;
import com.example.rosario.entity.Seller;
import com.example.rosario.entity.SellerImg;
import com.example.rosario.service.SellerService;
import com.example.rosario.service.SellerImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;
    private final SellerImgService sellerImgService;

    @Autowired
    public SellerController(SellerService sellerService, SellerImgService sellerImgService) {
        this.sellerService = sellerService;
        this.sellerImgService = sellerImgService;
    }

    @GetMapping
    public List<Seller> getAllSellers() {
        // 전체 판매자(가게) 조회
        return sellerService.getAllSellers();
    }

    @PostMapping("/register")
    // 판매자(가게) 회원가입
    public ResponseEntity<Seller> registerSeller(@RequestBody SellerDto sellerDTO) {
        Seller registeredSeller = sellerService.registerSeller(sellerDTO);
        return ResponseEntity.ok(registeredSeller);
    }

    @GetMapping("/check-email")
    // 이메일 중복체크
    public ResponseEntity<Boolean> checkEmail(@RequestParam("email") String email) {
        boolean emailExists = sellerService.isEmailAlreadyRegistered(email);
        return ResponseEntity.ok(emailExists);
    }

    @GetMapping("/{sellerId}/images")
    // 특정 판매자 ID로 판매자 이미지 조회
    public ResponseEntity<List<SellerImg>> getSellerImages(@PathVariable("sellerId") Long sellerId) {
        List<SellerImg> sellerImages = sellerImgService.getImagesBySellerId(sellerId);
        return ResponseEntity.ok(sellerImages);
    }
}
