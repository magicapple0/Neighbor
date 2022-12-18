package com.example.neighbor.services;

import com.example.neighbor.models.Ad;
import com.example.neighbor.repositories.AdRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdService {
    private final AdRepository repository;
    private final RatingCalculator ratingCalculator;

    public AdService(AdRepository repository, RatingCalculator ratingCalculator) {
        this.repository = repository;
        this.ratingCalculator = ratingCalculator;
    }

    public Page<Ad> getAds(int page, int pageSize) {
        var pagination = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("rating")));
        return repository.findAll(pagination);
    }

    public Page<Ad> getAds(long ownerId, int page, int pageSize) {
        var pagination = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("rating")));
        return repository.findAllByOwnerId(ownerId, pagination);
    }

    public Page<Ad> getAds(String category, int page, int pageSize) {
        var pagination = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("rating")));
        return repository.findAllByCategory(category, pagination);
    }

    public Page<Ad> findAds(String query, int page, int pageSize) {
        var pagination = PageRequest.of(page, pageSize, Sort.unsorted());
        return repository.findByQuery(query, pagination);
    }

    @Transactional
    public Ad create(Ad ad) {
        ad.setRating(ratingCalculator.GetRating(ad));
        return repository.save(ad);
    }

    @Transactional
    public void remove(Ad ad) {
        repository.delete(ad);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Ad getById(long id) {
        var ad = repository.findById(id);
        ad.setRating(ad.getRating() + 1);
        repository.save(ad);
        return ad;
    }

    public Ad update(Ad ad) {
        return repository.save(ad);
    }
}
