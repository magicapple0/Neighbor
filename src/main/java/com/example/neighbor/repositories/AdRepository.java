package com.example.neighbor.repositories;

import com.example.neighbor.models.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findById(long Id);

    Page<Ad> findAllByOwnerId(long id, Pageable pageable);

    Page<Ad> findAllByCategory(String category, Pageable pageable);

     @Query(value = "SELECT * from ads a where to_tsvector(a.description) @@ plainto_tsquery(:query) = TRUE or to_tsvector(a.title) @@ plainto_tsquery(:query) = TRUE"
            ,nativeQuery = true)
    Page<Ad> findByQuery(String query, Pageable pageable);

}
