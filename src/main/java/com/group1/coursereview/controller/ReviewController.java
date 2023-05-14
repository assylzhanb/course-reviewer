package com.group1.coursereview.controller;

import com.group1.coursereview.model.Review;
import com.group1.coursereview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/course/{courseCode}")
    public ResponseEntity<List<Review>> getReviewsByCourseCode(@PathVariable String courseCode) {
        List<Review> reviews = reviewRepository.getAllByCourseCode(courseCode);
        if (!reviews.isEmpty()) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/course/{courseCode}")
    public ResponseEntity<Review> createReview(@PathVariable String courseCode, @RequestBody Review review) {
        review.setCourseCode(courseCode);
        Review createdReview = reviewRepository.save(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }
}
