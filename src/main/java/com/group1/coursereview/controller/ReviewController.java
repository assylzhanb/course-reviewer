package com.group1.coursereview.controller;

import com.group1.coursereview.model.Review;
import com.group1.coursereview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/course/{courseCode}")
    public ResponseEntity<String> getReviewsByCourseCode(@PathVariable String courseCode) {
        List<Review> reviews = reviewRepository.getAllByCourseCode(courseCode);
        int count = reviews.size();
        if (!reviews.isEmpty()) {
            StringBuilder responseBuilder = new StringBuilder();
            for (Review review : reviews) {
                responseBuilder.append("Review ID: ").append(review.getId()).append("\n");
                responseBuilder.append("User ID: ").append(review.getUserId()).append("\n");
                responseBuilder.append("Review Body: ").append(review.getReviewBody()).append("\n");
                responseBuilder.append("Course Code: ").append(review.getCourseCode()).append("\n");
                responseBuilder.append("Course Rating: ").append(review.getCourseRating()).append("\n");
                responseBuilder.append("Created At: ").append(review.getCreatedAt()).append("\n");
                responseBuilder.append("----------\n");
            }
            return new ResponseEntity<>(responseBuilder.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No reviews found for the given course code.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/course/{courseCode}")
    public ResponseEntity<Review> createReview(@PathVariable String courseCode, @RequestBody Review review) {
        review.setCourseCode(courseCode);
        Review createdReview = reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable String reviewId, @RequestBody Review review) {
        Optional<Review> existingReviewOptional = reviewRepository.findById(reviewId);
        if (existingReviewOptional.isPresent()) {
            Review existingReview = existingReviewOptional.get();
            existingReview.setCourseRating(review.getCourseRating());
            existingReview.setReviewBody(review.getReviewBody());
            Review updatedReview = reviewRepository.save(existingReview);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable String reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            reviewRepository.deleteById(reviewId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/course/{courseCode}")
    public ResponseEntity<String> deleteReviewsByCourseCode(@PathVariable String courseCode) {
        List<Review> reviews = reviewRepository.getAllByCourseCode(courseCode);
        if (!reviews.isEmpty()) {
            reviewRepository.deleteAllByCourseCode(courseCode);
            String message = "Successfully deleted all the reviews on " + courseCode + " course";
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}