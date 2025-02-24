package com.example.foodexpress.service;

import com.example.foodexpress.model.Rating;
import com.example.foodexpress.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

   

    public Rating saveRating(Rating rating) {
        System.out.println("Saving rating: " + rating);  // Log the rating being saved
        Rating savedRating = ratingRepository.save(rating);
        System.out.println("Saved rating: " + savedRating);  // Log the saved rating
        return savedRating;
    }
    

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public double getAverageRating() {
        Double avg = ratingRepository.findAverageRating();
        System.out.println("Fetched average rating: " + avg);  // Debug log
        return avg != null ? avg : 0.0;
    }
}
