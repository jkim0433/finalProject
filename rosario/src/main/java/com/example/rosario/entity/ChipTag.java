package com.example.rosario.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "chiptag")
public class ChipTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chipTagId;

    private String label;

}
