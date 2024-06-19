package com.example.rosario.controller;

import com.example.rosario.entity.Product;
import com.example.rosario.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Product>> getProductsBySellerId(@PathVariable("sellerId") String sellerId) {
        List<Product> products = productService.getProductsBySellerId(Long.parseLong(sellerId));
        return ResponseEntity.ok(products);
    }

}
