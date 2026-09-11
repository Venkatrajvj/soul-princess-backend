package com.soulprincess.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // Add a review
    @PostMapping
    public Review addReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    // Get all reviews
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Get reviews for a specific product
    @GetMapping("/product/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable Integer productId) {
        return reviewRepository.findAll()
                .stream()
                .filter(r -> r.getProduct().getId().equals(productId))
                .toList();
    }

    // Delete a review
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Integer id) {
        reviewRepository.deleteById(id);
        return "Review deleted successfully";
    }
}