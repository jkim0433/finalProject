package com.example.rosario.service;

import com.example.rosario.entity.Product;
import com.example.rosario.entity.ProductImg;
import com.example.rosario.repository.ProductImgRepository;
import com.example.rosario.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductImgService {
    @Autowired
    private ProductImgRepository productImgRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductImg saveFile(Long productId, String filename, String filePath) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            ProductImg productImg = new ProductImg(product, filename, filePath);
            return productImgRepository.save(productImg);
        } else {
            throw new RuntimeException("Product not found with id: " + productId);
        }
    }

    public List<ProductImg> getImagesByProductId(Long productId) {
        return productImgRepository.findByProductProductId(productId);
    }
}
