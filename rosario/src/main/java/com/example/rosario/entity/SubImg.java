package com.example.rosario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subImg")
public class SubImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subImgId;   // 구독 주문시 첨부하는 이미지  ID

    @Column(nullable = false)
    private String subImg;   // 첨부하는 이미지 이미지 URL 또는 파일 경로

    @ManyToOne
    @JoinColumn(name = "subscribe_id", nullable = false)
    private Subscription subscription;   // 구독 ID (FK)
}
