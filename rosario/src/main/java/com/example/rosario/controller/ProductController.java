package com.example.rosario.controller;

import com.example.rosario.dto.ProductDetailDto;
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

    @PostMapping("/register")
    public ResponseEntity<Product> registerProduct(@RequestBody Product product, @RequestParam("sellerId") String sellerId) {
        try {
            Product savedProduct = productService.saveProduct(product, Long.parseLong(sellerId));
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Product>> getProductsBySellerId(@PathVariable("sellerId") String sellerId) {
        List<Product> products = productService.getProductsBySellerId(Long.parseLong(sellerId));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDto> getProductDetail(@PathVariable("productId") String productId) {
        ProductDetailDto productDetailDto = productService.getProductDetail(Long.parseLong(productId));
        return ResponseEntity.ok(productDetailDto);
    }


}
