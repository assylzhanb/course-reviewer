package com.group1.coursereview.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CommentTest {
    @Test
    void constructor_CreatesReviewWithCorrectValues() {
        String courseCode = "CSCI101";
        String professorId = "123";
        String userId = "456";
        int courseRating = 5;
        String reviewBody = "This course is excellent!";


        Review review = new Review(courseCode, professorId, userId, courseRating, reviewBody);


        Assertions.assertEquals(courseCode, review.getCourseCode());
        Assertions.assertEquals(professorId, review.getProfessorId());
        Assertions.assertEquals(userId, review.getUserId());
        Assertions.assertEquals(courseRating, review.getCourseRating());
        Assertions.assertEquals(reviewBody, review.getReviewBody());


        LocalDateTime currentTime = LocalDateTime.now();
        Assertions.assertTrue(currentTime.isBefore(review.getCreatedAt().plusSeconds(1))); // Allow a 1-second difference for test execution time
        Assertions.assertTrue(currentTime.isAfter(review.getCreatedAt().minusSeconds(1))); // Allow a 1-second difference for test execution time
    }

    @Test
    void getId_ReturnsCorrectValue() {

        String id = "comment123";
        Comment comment = new Comment();
        comment.setId(id);

        String actualId = comment.getId();
        Assertions.assertEquals(id, actualId);
    }

    @Test
    void getReviewId_ReturnsCorrectValue() {

        String reviewId = "review123";
        Comment comment = new Comment();
        comment.setReviewId(reviewId);

        String actualReviewId = comment.getReviewId();

        Assertions.assertEquals(reviewId, actualReviewId);
    }

    @Test
    void getUserId_ReturnsCorrectValue() {
        String userId = "user123";
        Comment comment = new Comment();
        comment.setUserId(userId);

        String actualUserId = comment.getUserId();

        Assertions.assertEquals(userId, actualUserId);
    }

    @Test
    void getText_ReturnsCorrectValue() {
        String text = "This is a comment.";
        Comment comment = new Comment();
        comment.setText(text);

        String actualText = comment.getText();

        Assertions.assertEquals(text, actualText);
    }
}
