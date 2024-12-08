package com.Chubb.EcommerceWebSite.service;

import com.Chubb.EcommerceWebSite.model.Review;
import com.Chubb.EcommerceWebSite.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ReviewRepository reviewRepository;

    // Create a new review
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    // Get all reviews for a specific item
    public List<Review> getReviewsByItemId(String itemId) {
        List<Review> Reviews =  reviewRepository.findByItemId(itemId);
        for(Review r : Reviews){
            r.setCustomerName(customerService.getCustomerById(r.getCustomerId()).getUsername());
        }
        return Reviews;
    }

    // Get all reviews by a specific customer
    public List<Review> getReviewsByCustomerId(String customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    // Get a specific review by ID
    public Optional<Review> getReviewById(String reviewId) {
        return reviewRepository.findById(reviewId);
    }

    // Update a review
    public Optional<Review> updateReview(String reviewId, Review updatedReview) {
        return reviewRepository.findById(reviewId).map(existingReview -> {
            existingReview.setReview(updatedReview.getReview());
            existingReview.setRating(updatedReview.getRating());
            return reviewRepository.save(existingReview);
        });
    }

    // Delete a review
    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }


}
