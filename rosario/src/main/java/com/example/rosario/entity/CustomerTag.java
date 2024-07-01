package com.example.rosario.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customerTag")
@Data
public class CustomerTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "customerId" ,nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "chipTagId" ,nullable = false)
    private ChipTag chipTag;
}
