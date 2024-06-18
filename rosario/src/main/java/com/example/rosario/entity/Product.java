package com.example.rosario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 엔티티의 기본키 값을 자동으로 생성
    private Long productId;         // 상품ID


    @NotBlank(message = "상품명은 필수 입력 항목입니다.")
    @Column(nullable = false)
    private String productNm;       // 상품명

    @NotNull(message = "상품가격은 필수 입력 항목입니다.")
    @Min(value = 0, message = "상품가격은 0 이상이어야 합니다.")
    @Column(nullable = false)
    private Long productPrice;      // 상품가격

    @NotNull(message = "상품재고수량은 필수 입력 항목입니다.")
    @Min(value = 0, message = "상품재고수량은 0 이상이어야 합니다.")
    @Column(nullable = false)
    private Long productStock;      // 상품재고수량

    @NotBlank(message = "상품사이즈는 필수 입력 항목입니다.")
    @Column(nullable = false)
    private String productSize;     // 상품사이즈 (M, L, XL)
}
