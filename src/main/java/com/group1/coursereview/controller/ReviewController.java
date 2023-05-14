package com.group1.coursereview.controller;

import com.group1.coursereview.model.Course;
import com.group1.coursereview.model.Review;
import com.group1.coursereview.repository.CourseRepository;
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
    private CourseRepository courseRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/course/{courseCode}")
    public ResponseEntity<String> getReviewsByCourseCode(@PathVariable String courseCode) {
        List<Review> reviews = reviewRepository.getAllByCourseCode(courseCode);
        Course course = courseRepository.findByCourseCode(courseCode);
        int count = reviews.size();
        if (!reviews.isEmpty()) {
            StringBuilder responseBuilder = new StringBuilder();
            for (Review review : reviews) {
                responseBuilder.append("Course Name: ").append(course.getCourseTitle()).append("\n");
                responseBuilder.append("Count of reviews: ").append(count).append("\n");
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
    public ResponseEntity<String> createReview(@PathVariable String courseCode, @RequestBody Review review) {

        Course course = courseRepository.findByCourseCode(courseCode);
        if (course == null) {
            return new ResponseEntity<>("No course like this", HttpStatus.NOT_FOUND);
        }

        if (review.getUserId() == null) {
            return new ResponseEntity<>("User ID is required", HttpStatus.BAD_REQUEST);
        }

        if (review.getCourseRating() < 1 || review.getCourseRating() > 5) {
            return new ResponseEntity<>("Course rating should be between 1 and 5", HttpStatus.BAD_REQUEST);
        }

        if (review.getReviewBody() == null) {
            return new ResponseEntity<>("Review body is required", HttpStatus.BAD_REQUEST);
        }

        review.setCourseCode(courseCode);
        Review createdReview = reviewRepository.save(review);
        return new ResponseEntity<>("Review posted!", HttpStatus.CREATED);
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable String reviewId, @RequestBody Review review) {
        Optional<Review> existingReviewOptional = reviewRepository.findById(reviewId);
        if (existingReviewOptional.isPresent()) {
            Review existingReview = existingReviewOptional.get();
            existingReview.setCourseRating(review.getCourseRating());
            existingReview.setReviewBody(review.getReviewBody());
            Review updatedReview = reviewRepository.save(existingReview);
            return new ResponseEntity<>("Changed the review to " + updatedReview.getReviewBody(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable String reviewId) {
        Review review = reviewRepository.getReviewById(reviewId));
        if (review == null) {
            return new ResponseEntity<>("No course found with the provided course code", HttpStatus.NOT_FOUND);
        }
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            reviewRepository.deleteById(reviewId);
            return new ResponseEntity<>("deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/course/{courseCode}")
    public ResponseEntity<String> deleteReviewsByCourseCode(@PathVariable String courseCode) {
        Course course = courseRepository.findByCourseCode(courseCode);
        if (course == null) {
            return new ResponseEntity<>("No course found with the provided course code", HttpStatus.NOT_FOUND);
        }

        List<Review> reviews = reviewRepository.getAllByCourseCode(courseCode);
        if (!reviews.isEmpty()) {
            reviewRepository.deleteAllByCourseCode(courseCode);
            String message = "Successfully deleted all the reviews for the course with code " + courseCode;
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No reviews found for the course with code " + courseCode, HttpStatus.NOT_FOUND);
        }
    }



}