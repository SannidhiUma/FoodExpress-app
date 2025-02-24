package com.example.foodexpress.repository;

import com.example.foodexpress.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    // Fetch the average rating from the database
    @Query("SELECT AVG(r.rating) FROM Rating r")
    Double findAverageRating();
}
