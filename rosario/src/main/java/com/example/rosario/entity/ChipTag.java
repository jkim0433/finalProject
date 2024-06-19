package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Table(name = "chipTag")
@Getter
@Setter
public class ChipTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chipTagId;

    @Column(nullable = false)
    private String label;

    @OneToMany(mappedBy = "chipTag")
    private Set<CustomerTag> customerTags;

    @OneToMany(mappedBy = "chipTag")
    private Set<ProductTag> productTags;
}
