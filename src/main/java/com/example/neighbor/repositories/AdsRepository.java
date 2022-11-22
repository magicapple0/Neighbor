package com.example.neighbor.repositories;

import com.example.neighbor.models.Ad;
import com.example.neighbor.models.User;
import org.springframework.data.repository.CrudRepository;

public interface AdsRepository extends CrudRepository<Ad, Long> {
    Ad findById(long Id);
}
