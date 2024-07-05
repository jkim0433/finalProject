package com.example.rosario.dto;

import com.example.rosario.entity.ProductImg;


import java.util.Set;

public class ProductDetailDto {
    private Long productId;
    private String productNm;
    private Long productPrice;
    private Long productStock;
    private String productSize;
    private Set<ProductImg> productImages;

    //0704 도경추가
    private String sellerNm;        //판매자이름


    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductNm() {
        return productNm;
    }

    public void setProductNm(String productNm) {
        this.productNm = productNm;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductStock() {
        return productStock;
    }

    public void setProductStock(Long productStock) {
        this.productStock = productStock;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Set<ProductImg> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ProductImg> productImages) {
        this.productImages = productImages;
    }
}
