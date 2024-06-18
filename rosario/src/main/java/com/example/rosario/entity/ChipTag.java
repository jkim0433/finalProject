package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "chipTag")
@Getter
@Setter
public class ChipTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chipTagId;

    private String label;

}
