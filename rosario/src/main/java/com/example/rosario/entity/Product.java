package com.example.rosario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;         // 상품ID

    @NotBlank(message = "상품명은 필수 입력 항목입니다.")
    @Column(name = "product_nm", nullable = false)
    private String productNm;       // 상품명

    @NotNull(message = "상품가격은 필수 입력 항목입니다.")
    @Min(value = 0, message = "상품가격은 0 이상이어야 합니다.")
    @Column(name = "product_price", nullable = false)
    private Long productPrice;      // 상품가격

    @NotNull(message = "상품재고수량은 필수 입력 항목입니다.")
    @Min(value = 0, message = "상품재고수량은 0 이상이어야 합니다.")
    @Column(name = "product_stock", nullable = false)
    private Long productStock;      // 상품재고수량

    @Column(name = "product_size", nullable = false)
    private String productSize;     // 상품사이즈 (M, L, XL)

    @OneToMany(mappedBy = "product")
    private Set<ProductSeller> productSellers;

    @OneToMany(mappedBy = "product")
    private Set<ProductTag> productTags;
}
