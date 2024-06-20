package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_tag")
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productTagId;   // 상품취향 ID

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;         // 상품 ID (FK)

    @ManyToOne
    @JoinColumn(name = "chipTagId")
    private ChipTag chipTag;

}
