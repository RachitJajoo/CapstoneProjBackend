package com.Chubb.EcommerceWebSite.controller;

import com.Chubb.EcommerceWebSite.model.Customer;
import com.Chubb.EcommerceWebSite.model.Item;
import com.Chubb.EcommerceWebSite.model.Review;
import com.Chubb.EcommerceWebSite.service.CustomerService;
import com.Chubb.EcommerceWebSite.service.ItemService;
import com.Chubb.EcommerceWebSite.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(originPatterns = "http://localhost:4200")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ItemService itemService;

    // Create a new review
    @PostMapping("/add/{itemId}")
    public ResponseEntity<Review> addReview(@RequestBody Review review , @PathVariable String itemId) {
        review.setItemId(itemId);
        Item currItem= itemService.getItemById(itemId);
        currItem.getReviews().add(review);
        itemService.saveItem(currItem);
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    // Get all reviews for a specific item
    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<Review>> getReviewsByItemId(@PathVariable String itemId) {
        return ResponseEntity.ok(reviewService.getReviewsByItemId(itemId));
    }

    // Get all reviews by a specific customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Review>> getReviewsByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(reviewService.getReviewsByCustomerId(customerId));
    }

    // Get a specific review by ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<Optional<Review>> getReviewById(@PathVariable String reviewId) {
        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    // Update a review
    @PutMapping("/{reviewId}")
    public ResponseEntity<Optional<Review>> updateReview(@PathVariable String reviewId, @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, review));
    }

    // Delete a review
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
