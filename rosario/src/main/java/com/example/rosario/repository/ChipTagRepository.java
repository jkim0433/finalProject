package com.example.rosario.repository;

import com.example.rosario.entity.ChipTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChipTagRepository extends JpaRepository<ChipTag, Long> {
}