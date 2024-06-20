package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customerTag")
@Data
public class CustomerTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customTagId;

    @ManyToOne
    @JoinColumn(name = "customerId" ,nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "chipTagId" ,nullable = false)
    private ChipTag chipTag;
}
