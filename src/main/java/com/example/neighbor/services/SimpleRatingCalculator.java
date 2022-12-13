package com.example.neighbor.services;

import com.example.neighbor.models.Ad;
import org.springframework.stereotype.Service;

@Service
public class SimpleRatingCalculator implements RatingCalculator {

    @Override
    public int GetRating(Ad ad) {
        var rating = 0;
        rating += ad.getDescription().length() / 10;
        rating += ad.getImages().size();
        return rating;
    }
}
