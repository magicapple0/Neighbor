package com.example.neighbor.repositories;

import com.example.neighbor.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdsRepository extends JpaRepository<Ad, Long> {
    Ad findById(long Id);
}
