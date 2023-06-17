package com.group1.coursereview.controller;

import com.group1.coursereview.model.Course;
import com.group1.coursereview.model.Review;
import com.group1.coursereview.repository.CourseRepository;
import com.group1.coursereview.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


class ReviewControllerTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ReviewRepository reviewRepository;
    @InjectMocks
    private ReviewController reviewController;


    public ReviewControllerTest() {
        MockitoAnnotations.openMocks(this);
    }


//    @Test
//    void getReviewsByCourseCode_ValidCourseCode_ReturnsReviews() {
//        String courseCode = "CSC101";
//        Course course = new Course();
//        course.setCourseCode(courseCode);
//        course.setCourseTitle("Introduction to Programming");
//        Review review1 = new Review();
//        review1.setId("1");
//        review1.setCourseCode(courseCode);
//        review1.setUserId("user1");
//        review1.setReviewBody("Great course!");
//        review1.setCourseRating(4);
//        review1.setCreatedAt(LocalDateTime.now());
//        Review review2 = new Review();
//        review2.setId("2");
//        review2.setCourseCode(courseCode);
//        review2.setUserId("user2");
//        review2.setReviewBody("Awesome content!");
//        review2.setCourseRating(5);
//        review2.setCreatedAt(LocalDateTime.now());
//        List<Review> reviews = Arrays.asList(review1, review2);
//        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
//        when(reviewRepository.getAllByCourseCode(courseCode)).thenReturn(reviews);
//        ResponseEntity<String> response = reviewController.getReviewsByCourseCode(courseCode);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Course Name: Introduction to Programming\n" +
//                "Count of reviews: 2\n" +
//                "Review ID: 1\n" +
//                "User ID: user1\n" +
//                "Review Body: Great course!\n" +
//                "Course Code: CSC101\n" +
//                "Course Rating: 4\n" +
//                "Created At: " + review1.getCreatedAt() + "\n" +
//                "----------\n" +
//                "Course Name: Introduction to Programming\n" +
//                "Count of reviews: 2\n" +
//                "Review ID: 2\n" +
//                "User ID: user2\n" +
//                "Review Body: Awesome content!\n" +
//                "Course Code: CSC101\n" +
//                "Course Rating: 5\n" +
//                "Created At: " + review2.getCreatedAt() + "\n" +
//                "----------\n", response.getBody());
//
//
//        verify(courseRepository, times(1)).findByCourseCode(courseCode);
//        verify(reviewRepository, times(1)).getAllByCourseCode(courseCode);
//    }
//    @Test
//    void getReviewsByCourseCode_ValidCourseCode_ReturnsEmptyReviews() {
//        String courseCode = "CSC101";
//        Course course = new Course();
//        course.setCourseCode(courseCode);
//        course.setCourseTitle("Introduction to Programming");
//        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
//        when(reviewRepository.getAllByCourseCode(courseCode)).thenReturn(Collections.emptyList());
//
//        ResponseEntity<String> response = reviewController.getReviewsByCourseCode(courseCode);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals("No reviews found on the course CSC101", response.getBody());
//        verify(courseRepository, times(1)).findByCourseCode(courseCode);
//        verify(reviewRepository, times(1)).getAllByCourseCode(courseCode);
//    }

    @Test
    void createReview_MissingCourseCode_ReturnsNotFound() {
        String courseCode = null;
        Review review = createReview(null, courseCode, "user1", "Great course!", 4);

        ResponseEntity<String> response = reviewController.createReview(courseCode, review);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No course like this", response.getBody());
        verify(courseRepository, never()).findByCourseCode(anyString());
        verify(reviewRepository, never()).save(review);
    }



    @Test
    void updateReview_InvalidReview_ReturnsNotFound() {
        String reviewId = "1";
        Review updatedReview = createReview(reviewId, "CSC101", "user1", null, 5);
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = reviewController.updateReview(reviewId, updatedReview);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Review not found", response.getBody());
        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, never()).save(updatedReview);
    }


    @Test
    void deleteReview_InvalidReviewId_ReturnsBadRequest() {
        String reviewId = "invalidReviewId"; // Provide a valid reviewId

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = reviewController.deleteReview(reviewId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Review not found", response.getBody());
        verify(reviewRepository, never()).deleteById(reviewId);
    }



    @Test
    void deleteReviewsByCourseCode_MissingCourseCode_ReturnsBadRequest() {

        String courseCode = null;

        ResponseEntity<String> response = reviewController.deleteReviewsByCourseCode(courseCode);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid course code provided", response.getBody());
        verify(courseRepository, never()).findByCourseCode(courseCode);
        verify(reviewRepository, never()).deleteAllByCourseCode(courseCode);
    }



    @Test
    void createReview_ValidReview_ReturnsReviewPosted() {
        String courseCode = "CSC101";
        Course course = createCourse(courseCode, "Introduction to Programming");
        Review review = createReview(null, courseCode, "user1", "Great course!", 4);
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        ResponseEntity<String> response = reviewController.createReview(courseCode, review);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Review posted!", response.getBody());

        verify(courseRepository, times(1)).findByCourseCode(courseCode);
        verify(reviewRepository, times(1)).save(any(Review.class));
    }
    @Test
    void deleteReviewsByCourseCode_NoReviewsFound_ReturnsNotFoundStatus() {
        String courseCode = "CSC101";
        Course course = createCourse(courseCode, "Introduction to Programming");
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
        when(reviewRepository.getAllByCourseCode(courseCode)).thenReturn(Collections.emptyList());

        ResponseEntity<String> response = reviewController.deleteReviewsByCourseCode(courseCode);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No reviews found for the course with code " + courseCode, response.getBody());

        verify(courseRepository, times(1)).findByCourseCode(courseCode);
        verify(reviewRepository, times(1)).getAllByCourseCode(courseCode);
        verify(reviewRepository, never()).deleteAllByCourseCode(anyString());
    }
    @Test
    void deleteReviewsByCourseCode_InvalidCourseCode_ReturnsBadRequestStatus() {
        String courseCode = "";
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(null);

        ResponseEntity<String> response = reviewController.deleteReviewsByCourseCode(courseCode);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid course code provided", response.getBody());

        verify(courseRepository, never()).findByCourseCode(anyString());
        verify(reviewRepository, never()).getAllByCourseCode(anyString());
        verify(reviewRepository, never()).deleteAllByCourseCode(anyString());
    }
    @Test
    void deleteReviewsByCourseCode_ReviewsExist_DeletesReviewsAndReturnsSuccessMessage() {
        String courseCode = "CSC101";
        Course course = createCourse(courseCode, "Introduction to Programming");
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
        List<Review> reviews = createReviewsList(courseCode, 5);
        when(reviewRepository.getAllByCourseCode(courseCode)).thenReturn(reviews);

        ResponseEntity<String> response = reviewController.deleteReviewsByCourseCode(courseCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully deleted all the reviews for the course with code " + courseCode, response.getBody());

        verify(courseRepository, times(1)).findByCourseCode(courseCode);
        verify(reviewRepository, times(1)).getAllByCourseCode(courseCode);
        verify(reviewRepository, times(1)).deleteAllByCourseCode(courseCode);
    }
    private List<Review> createReviewsList(String courseCode, int numReviews) {
        List<Review> reviews = new ArrayList<>();
        for (int i = 1; i <= numReviews; i++) {
            Review review = new Review();
            review.setId("Review-" + i);
            review.setUserId("User-" + i);
            review.setReviewBody("Review " + i + " body");
            review.setCourseCode(courseCode);
            review.setCourseRating(5);
            review.setCreatedAt(LocalDateTime.now());
            reviews.add(review);
        }
        return reviews;
    }



    private Course createCourse(String courseCode, String courseTitle) {
        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseTitle(courseTitle);
        return course;
    }


    private Review createReview(String id, String courseCode, String userId, String reviewBody, int courseRating) {
        Review review = new Review();
        review.setId(id);
        review.setCourseCode(courseCode);
        review.setUserId(userId);
        review.setReviewBody(reviewBody);
        review.setCourseRating(courseRating);
        return review;
    }


    @Test
    void updateReview_ExistingReview_ReturnsUpdatedReview() {
        String reviewId = "1";
        Review existingReview = createReview(reviewId, "CSC101", "user1", "Good course", 4);
        Review updatedReview = createReview(reviewId, "CSC101", "user1", "Excellent course", 5);
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(existingReview));
        when(reviewRepository.save(existingReview)).thenReturn(updatedReview);
        ResponseEntity<String> response = reviewController.updateReview(reviewId, updatedReview);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Changed the review to Excellent course", response.getBody());
        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, times(1)).save(existingReview);
    }


//    @Test
//    void deleteReview_ExistingReview_DeletesReview() {
//        String reviewId = "1";
//        Review review = createReview(reviewId, "CSC101", "user1", "Good course", 4);
//        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
//        ResponseEntity<String> response = reviewController.deleteReview(reviewId);
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        assertEquals("Deleted", response.getBody());
//        verify(reviewRepository, times(1)).findById(reviewId);
//        verify(reviewRepository, times(1)).deleteById(reviewId);
//    }


    @Test
    void deleteReviewsByCourseCode_ExistingCourseCode_DeletesReviews() {
        String courseCode = "CSC101";
        Course course = createCourse(courseCode, "Introduction to Programming");
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
        when(reviewRepository.getAllByCourseCode(courseCode)).thenReturn(Arrays.asList());
        ResponseEntity<String> response = reviewController.deleteReviewsByCourseCode(courseCode);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No reviews found for the course with code CSC101", response.getBody());
        verify(courseRepository, times(1)).findByCourseCode(courseCode);
        verify(reviewRepository, times(1)).getAllByCourseCode(courseCode);
        verify(reviewRepository, never()).deleteAllByCourseCode(courseCode);
    }


//    @Test
//    void getReviewsByCourseCode_NoReviews_ReturnsNotFound() {
//        String courseCode = "CSC101";
//        Course course = new Course();
//        course.setCourseCode(courseCode);
//        course.setCourseTitle("Introduction to Programming");
//        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
//        when(reviewRepository.getAllByCourseCode(courseCode)).thenReturn(Collections.emptyList());
//        ResponseEntity<String> response = reviewController.getReviewsByCourseCode(courseCode);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals("No reviews found on the course CSC101", response.getBody());
//        verify(courseRepository, times(1)).findByCourseCode(courseCode);
//        verify(reviewRepository, times(1)).getAllByCourseCode(courseCode);
//    }


    @Test
    void createReview_InvalidCourseCode_ReturnsNotFound() {
        String courseCode = "CSC101";
        Review review = createReview(null, courseCode, "user1", "Great course!", 4);
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(null);
        ResponseEntity<String> response = reviewController.createReview(courseCode, review);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No course like this", response.getBody());
        verify(courseRepository, times(1)).findByCourseCode(courseCode);
        verify(reviewRepository, never()).save(review);
    }


    @Test
    void createReview_MissingUserId_ReturnsBadRequest() {
        String courseCode = "CSC101";
        Course course = createCourse(courseCode, "Introduction to Programming");
        Review review = createReview(null, courseCode, null, "Great course!", 4);
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
        ResponseEntity<String> response = reviewController.createReview(courseCode, review);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User ID is required", response.getBody());
        verify(courseRepository, times(1)).findByCourseCode(courseCode);
        verify(reviewRepository, never()).save(review);
    }


    @Test
    void createReview_InvalidCourseRating_ReturnsBadRequest() {
        String courseCode = "CSC101";
        Course course = createCourse(courseCode, "Introduction to Programming");
        Review review = createReview(null, courseCode, "user1", "Great course!", 0);
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
        ResponseEntity<String> response = reviewController.createReview(courseCode, review);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Course rating should be between 1 and 5", response.getBody());
        verify(courseRepository, times(1)).findByCourseCode(courseCode);
        verify(reviewRepository, never()).save(review);
    }


    @Test
    void createReview_MissingReviewBody_ReturnsBadRequest() {
        String courseCode = "CSC101";
        Course course = createCourse(courseCode, "Introduction to Programming");
        Review review = createReview(null, courseCode, "user1", null, 4);
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
        ResponseEntity<String> response = reviewController.createReview(courseCode, review);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Review body is required", response.getBody());
        verify(courseRepository, times(1)).findByCourseCode(courseCode);
        verify(reviewRepository, never()).save(review);
    }


//    @Test
//    void getReviewsByCourseCode_NonExistingCourseCode_ReturnsNotFound() {
//        String courseCode = "CSC101";
//        when(courseRepository.findByCourseCode(courseCode)).thenReturn(null);
//        ResponseEntity<String> response = reviewController.getReviewsByCourseCode(courseCode);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals("No reviews found on the course CSC101", response.getBody());
//        verify(courseRepository, times(1)).findByCourseCode(courseCode);
//        verify(reviewRepository, times(1)).getAllByCourseCode(courseCode);
//    }




    @Test
    void createReview_EmptyReviewBody_ReturnsBadRequest() {
        String courseCode = "CSC101";
        Course course = createCourse(courseCode, "Introduction to Programming");
        Review review = createReview(null, courseCode, "user1", "", 4);
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(course);
        ResponseEntity<String> response = reviewController.createReview(courseCode, review);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Review body is required", response.getBody());
        verify(courseRepository, times(1)).findByCourseCode(courseCode);
        verify(reviewRepository, never()).save(review);
    }
    @Test
    void updateReview_NonExistingReviewId_ReturnsNotFound() {
        String reviewId = "1";
        Review updatedReview = createReview(reviewId, "CSC101", "user1", "Excellent course", 5);
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());
        ResponseEntity<String> response = reviewController.updateReview(reviewId, updatedReview);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Review not found", response.getBody());
        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, never()).save(any(Review.class));
    }
//    @Test
//    void deleteReview_NonExistingReviewId_ReturnsNotFound() {
//        String reviewId = "1";
//        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());
//        ResponseEntity<String> response = reviewController.deleteReview(reviewId);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals("Review not found", response.getBody());
//        verify(reviewRepository, times(1)).findById(reviewId);
//        verify(reviewRepository, never()).deleteById(reviewId);
//    }
    @Test
    void deleteReviewsByCourseCode_NonExistingCourseCode_ReturnsNotFound() {
        String courseCode = "CSC101";
        when(courseRepository.findByCourseCode(courseCode)).thenReturn(null);
        ResponseEntity<String> response = reviewController.deleteReviewsByCourseCode(courseCode);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No course found with the provided course code", response.getBody());
        verify(courseRepository, times(1)).findByCourseCode(courseCode);
        verify(reviewRepository, never()).deleteAllByCourseCode(courseCode);
    }
    @Test
    void updateReview_NonExistingReview_ReturnsNotFound() {
        String reviewId = "1";
        Review updatedReview = createReview(reviewId, "CSC101", "user1", "Excellent course", 5);
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());
        ResponseEntity<String> response = reviewController.updateReview(reviewId, updatedReview);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Review not found", response.getBody());
        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, never()).save(any(Review.class));
    }


//    @Test
//    void deleteReview_NonExistingReview_ReturnsNotFound() {
//        String reviewId = "1";
//        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());
//        ResponseEntity<String> response = reviewController.deleteReview(reviewId);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals("Review not found", response.getBody());
//        verify(reviewRepository, times(1)).findById(reviewId);
//        verify(reviewRepository, never()).deleteById(reviewId);
//    }
}