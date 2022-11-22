package com.example.neighbor.services;

import com.example.neighbor.models.Ad;
import com.example.neighbor.repositories.AdsRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AdsService {
    private final AdsRepository repository;

    public AdsService(AdsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Ad> getAds(int page, int pageSize) {
        var pagination = PageRequest.of(page, pageSize, Sort.unsorted());
        return repository.findAll(pagination).getContent();
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
