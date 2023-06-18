package com.group1.coursereview.controller;

import com.group1.coursereview.model.Comment;
import com.group1.coursereview.model.Review;
import com.group1.coursereview.repository.CommentRepository;
import com.group1.coursereview.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentControllerTest {
    private final CommentRepository commentRepository = mock(CommentRepository.class);
    private final ReviewRepository reviewRepository = mock(ReviewRepository.class);
    private final CommentController commentController = new CommentController(commentRepository, reviewRepository);

//    @Test
//    void getCommentsByReviewId_ReviewExists_ReturnsListOfComments() {
//        String reviewId = "review123";
//        List<Comment> comments = new ArrayList<>();
//        comments.add(new Comment(reviewId, "user1", "Great review!"));
//        comments.add(new Comment(reviewId, "user2", "I agree."));
//        Review review = new Review();
//        when(reviewRepository.getReviewByReviewId(reviewId)).thenReturn(review);
//        when(commentRepository.getAllByReviewId(reviewId)).thenReturn(comments);
//        ResponseEntity<List<Comment>> response = commentController.getCommentsByReviewId(reviewId);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(comments, response.getBody());
//        verify(reviewRepository, times(1)).getReviewByReviewId(reviewId);
//        verify(commentRepository, times(1)).getAllByReviewId(reviewId);
//        verifyNoMoreInteractions(reviewRepository, commentRepository);
//    }

    @Test
    void getCommentsByReviewId_ReviewDoesNotExist_ReturnsNotFound() {
        String reviewId = "review123";
        when(reviewRepository.getReviewByReviewId(reviewId)).thenReturn(null);
        ResponseEntity<List<Comment>> response = commentController.getCommentsByReviewId(reviewId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(reviewRepository, times(1)).getReviewByReviewId(reviewId);
        verifyNoMoreInteractions(reviewRepository, commentRepository);
    }

//    @Test
//    void addCommentToReview_ReviewExists_ReturnsSavedComment() {
//        String reviewId = "review123";
//        Comment comment = new Comment(reviewId, "user1", "Great review!");
//        Review review = new Review();
//        when(reviewRepository.getReviewByReviewId(reviewId)).thenReturn(review);
//        when(commentRepository.save(comment)).thenReturn(comment);
//        ResponseEntity<Comment> response = commentController.addCommentToReview(reviewId, comment);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(comment, response.getBody());
//        verify(reviewRepository, times(1)).getReviewByReviewId(reviewId);
//        verify(commentRepository, times(1)).save(comment);
//        verifyNoMoreInteractions(reviewRepository, commentRepository);
//    }
//    @Test
//    void updateComment_ReviewAndCommentExist_ReturnsUpdatedComment() {
//        String reviewId = "review123";
//        String commentId = "comment123";
//        Comment existingComment = new Comment(reviewId, "user1", "Great review!");
//        Comment updatedComment = new Comment(reviewId, "user1", "Updated review!");
//        Review review = new Review();
//        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
//        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
//        when(commentRepository.save(existingComment)).thenReturn(updatedComment);
//        ResponseEntity<Comment> response = commentController.updateComment(reviewId, commentId, updatedComment);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(updatedComment, response.getBody());
//        verify(reviewRepository, times(1)).findById(reviewId);
//        verify(commentRepository, times(1)).findById(commentId);
//        verify(commentRepository, times(1)).save(existingComment);
//        verifyNoMoreInteractions(reviewRepository, commentRepository);
//    }



//    @Test
//    void updateComment_CommentDoesNotExist_ReturnsNotFound() {
//        String reviewId = "review123";
//        String commentId = "comment123";
//        Comment updatedComment = new Comment(reviewId, "user1", "Updated review!");
//        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(new Review()));
//        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());
//        ResponseEntity<Comment> response = commentController.updateComment(reviewId, commentId, updatedComment);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(reviewRepository, times(1)).findById(reviewId);
//        verify(commentRepository, times(1)).findById(commentId);
//        verifyNoMoreInteractions(reviewRepository, commentRepository);
//    }


    @Test
    void deleteComment_ReviewAndCommentExist_ReturnsSuccessMessage() {
        String reviewId = "review123";
        String commentId = "comment123";
        Review review = new Review();
        Comment comment = new Comment();
        when(reviewRepository.getReviewByReviewId(reviewId)).thenReturn(review);
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        ResponseEntity<String> response = commentController.deleteComment(reviewId, commentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Comment deleted successfully", response.getBody());
        verify(reviewRepository, times(1)).getReviewByReviewId(reviewId);
        verify(commentRepository, times(1)).findById(commentId);
        verify(commentRepository, times(1)).deleteById(commentId);
        verifyNoMoreInteractions(reviewRepository, commentRepository);
    }
    @Test
    void deleteComment_CommentDoesNotExist_ReturnsNotFound() {
        String reviewId = "review123";
        String commentId = "comment123";
        Review mockReview = new Review(); // Create a mock Review object

        when(reviewRepository.getReviewByReviewId(reviewId)).thenReturn(mockReview); // Mock the behavior of getReviewByReviewId() to return the mockReview object

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());
        ResponseEntity<String> response = commentController.deleteComment(reviewId, commentId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(reviewRepository, times(1)).getReviewByReviewId(reviewId);
        verify(commentRepository, times(1)).findById(commentId);
        verifyNoMoreInteractions(reviewRepository, commentRepository);
    }



//    @Test
//    void updateComment_ReviewDoesNotExist_ReturnsNotFound() {
//        String reviewId = "review123";
//        String commentId = "comment123";
//        Comment updatedComment = new Comment(reviewId, "user1", "Updated review!");
//        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());
//
//        ResponseEntity<Comment> response = commentController.updateComment(reviewId, commentId, updatedComment);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(reviewRepository, times(1)).findById(reviewId);
//        verifyNoMoreInteractions(reviewRepository, commentRepository);
//    }
    @Test
    void deleteComment_ReviewDoesNotExist_ReturnsNotFound() {
        String reviewId = "review123";
        String commentId = "comment123";
        Review review = null;
        when(reviewRepository.getReviewByReviewId(reviewId)).thenReturn(review);
        ResponseEntity<String> response = commentController.deleteComment(reviewId, commentId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(reviewRepository, times(1)).getReviewByReviewId(reviewId);
        verifyNoMoreInteractions(reviewRepository, commentRepository);
    }



//    @Test
//    void addCommentToReview_ReviewDoesNotExist_ReturnsNotFound() {
//        String reviewId = "review123";
//        Comment comment = new Comment(reviewId, "user1", "Great review!");
//        when(reviewRepository.getReviewByReviewId(reviewId)).thenReturn(null);
//
//        ResponseEntity<Comment> response = commentController.addCommentToReview(reviewId, comment);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(reviewRepository, times(1)).getReviewByReviewId(reviewId);
//        verifyNoMoreInteractions(reviewRepository, commentRepository);
//    }

}