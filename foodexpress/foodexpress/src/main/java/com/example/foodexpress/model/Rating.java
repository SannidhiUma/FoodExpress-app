package com.example.foodexpress.model;


import jakarta.persistence.*;

@Entity
@Table(name = "ratings") // Ensure it matches your MySQL table name
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "reviewer_name", nullable = false)
    private String reviewerName;  // Matches MySQL column `reviewer_name`

    @Column(name = "review_text")
    private String reviewText;  // Matches MySQL column `review_text`

    @Column(nullable = false)
    private int rating;  // Matches MySQL column `rating` (DECIMAL(2,1))

    // Constructors
    public Rating() {}

    public Rating(String reviewerName, String reviewText, int rating) {
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReviewerName() { return reviewerName; }
    public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }

    public double getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}
