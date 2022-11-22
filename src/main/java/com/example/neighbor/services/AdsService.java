package com.example.neighbor.services;

import com.example.neighbor.models.Ad;
import com.example.neighbor.models.Role;
import com.example.neighbor.repositories.AdsRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdsService {
    private final AdsRepository repository;

    public AdsService(AdsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Ad create(Ad ad) {
        return repository.save(ad);
    }

    @Transactional
    public Ad getById(long id) {
        return repository.findById(id);
    }
}
