package com.example.neighbor.repositories;

import com.example.neighbor.models.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findById(long Id);

    Page<Ad> findAllByOwnerId(long id, Pageable pageable);

    Page<Ad> findAllByCategory(String category, Pageable pageable);
}
