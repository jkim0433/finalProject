package com.example.rosario.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
@Entity
@Table(name = "customerTag")
public class CustomerTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customTagId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "chipTagId")
    private ChipTag chipTag;
}
