package com.example.foodexpress.controller;

import com.example.foodexpress.model.Rating;
import com.example.foodexpress.repository.RatingRepository;
import com.example.foodexpress.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin("*") // Allow frontend to access API
public class RatingController {

    @Autowired
    private RatingService ratingService;

        @Autowired
    private RatingRepository ratingRepository;  // This should not be null


    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Rating rating) {
        try {
            ratingService.saveRating(rating);
            return ResponseEntity.ok("Review submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

   

    // Get all reviews
    @GetMapping("/all")
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }
    @GetMapping
    public ResponseEntity<String> checkAPI() {
        return ResponseEntity.ok("API is working!");
    }

    @GetMapping("/average")
    public ResponseEntity<Double> getAverageRating() {
        Double avg = ratingRepository.findAverageRating();
        return ResponseEntity.ok((avg != null) ? avg : 0.0);
    }

}
