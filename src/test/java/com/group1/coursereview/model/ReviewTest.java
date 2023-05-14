package com.group1.coursereview.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

class ReviewTest {

    @Test
    void getId_ReturnsCorrectValue() {

        String id = "review123";
        Review review = new Review();
        review.setId(id);
        String actualId = review.getId();
        Assertions.assertEquals(id, actualId);
    }

    @Test
    void getCourseCode_ReturnsCorrectValue() {

        String courseCode = "CSE101";
        Review review = new Review();
        review.setCourseCode(courseCode);

        String actualCourseCode = review.getCourseCode();


        Assertions.assertEquals(courseCode, actualCourseCode);
    }

    @Test
    void getProfessorId_ReturnsCorrectValue() {

        String professorId = "prof123";
        Review review = new Review();
        review.setProfessorId(professorId);


        String actualProfessorId = review.getProfessorId();


        Assertions.assertEquals(professorId, actualProfessorId);
    }

    @Test
    void getUserId_ReturnsCorrectValue() {

        String userId = "user123";
        Review review = new Review();
        review.setUserId(userId);


        String actualUserId = review.getUserId();


        Assertions.assertEquals(userId, actualUserId);
    }

    @Test
    void getCourseRating_ReturnsCorrectValue() {

        int courseRating = 4;
        Review review = new Review();
        review.setCourseRating(courseRating);


        int actualCourseRating = review.getCourseRating();


        Assertions.assertEquals(courseRating, actualCourseRating);
    }

    @Test
    void getReviewBody_ReturnsCorrectValue() {

        String reviewBody = "This course is great!";
        Review review = new Review();
        review.setReviewBody(reviewBody);


        String actualReviewBody = review.getReviewBody();

        Assertions.assertEquals(reviewBody, actualReviewBody);
    }

    @Test
    void getCreatedAt_ReturnsNonNullValue() {
        Review review = new Review();

        LocalDateTime createdAt = review.getCreatedAt();

        Assertions.assertNotNull(createdAt);
    }
}
