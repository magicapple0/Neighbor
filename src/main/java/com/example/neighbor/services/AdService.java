package com.example.neighbor.services;

import com.example.neighbor.models.Ad;
import com.example.neighbor.repositories.AdRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class AdService {
    private final AdRepository repository;

    public AdService(AdRepository repository) {
        this.repository = repository;
    }

    public Page<Ad> getAds(int page, int pageSize) {
        var pagination = PageRequest.of(page, pageSize, Sort.unsorted());
        return repository.findAll(pagination);
    }

    public Page<Ad> getAds(long ownerId, int page, int pageSize) {
        var pagination = PageRequest.of(page, pageSize, Sort.unsorted());
        return repository.findAllByOwnerId(ownerId, pagination);
    }

    public Page<Ad> getAds(String category, int page, int pageSize) {
        var pagination = PageRequest.of(page, pageSize, Sort.unsorted());
        return repository.findAllByCategory(category, pagination);
    }

    @Transactional
    public Ad create(Ad ad) {
        return repository.save(ad);
    }

    @Transactional
    public void remove(Ad ad) {
        repository.delete(ad);
    }

    public Ad getById(long id) {
        return repository.findById(id);
    }
}
