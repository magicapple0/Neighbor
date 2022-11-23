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

    @Transactional
    public Page<Ad> getAds(int page, int pageSize) {
        var pagination = PageRequest.of(page, pageSize, Sort.unsorted());
        var pageObj = repository.findAll(pagination);
        return pageObj;
    }

    @Transactional
    public Page<Ad> getAds(long ownerId, int page, int pageSize) {
        var pagination = PageRequest.of(page, pageSize, Sort.unsorted());
        var pageObj = repository.findAllByOwnerId(ownerId, pagination);
        return pageObj;
    }

    @Transactional
    public Ad create(Ad ad) {
        return repository.save(ad);
    }

    @Transactional
    public void remove(Ad ad) {
        repository.delete(ad);
    }

    @Transactional
    public Ad getById(long id) {
        return repository.findById(id);
    }
}
