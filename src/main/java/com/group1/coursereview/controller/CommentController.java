package com.group1.coursereview.controller;

import com.group1.coursereview.model.Comment;
import com.group1.coursereview.model.Review;
import com.group1.coursereview.repository.CommentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.group1.coursereview.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reviews/{reviewId}/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;

    public CommentController(CommentRepository commentRepository, ReviewRepository reviewRepository) {
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
    }


    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByReviewId(@PathVariable String reviewId) {
        Review reviewOptional = reviewRepository.getReviewByReviewId(reviewId);
        if (reviewOptional != null) {
            List<Comment> comments = commentRepository.getAllByReviewId(reviewId);
            return ResponseEntity.ok(comments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Comment> addCommentToReview(@PathVariable String reviewId, @RequestBody Comment comment) {
        Review review = reviewRepository.getReviewByReviewId(reviewId);
        if (review != null) {
            comment.setReviewId(reviewId);
            String commentId = UUID.randomUUID().toString(); // Generate a unique comment ID
            comment.setCommentId(commentId);
            Comment savedComment = commentRepository.save(comment);
            return ResponseEntity.ok(savedComment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable String reviewId, @PathVariable String commentId, @RequestBody Comment updatedComment) {
        Review review = reviewRepository.getReviewByReviewId(reviewId);
        if (review != null) {
            Comment comment = commentRepository.getCommentByCommentId(commentId);
            if (comment != null) {
                comment.setText(updatedComment.getText());
                Comment savedComment = commentRepository.save(comment);
                return ResponseEntity.ok(savedComment);
            }
        }
        return ResponseEntity.notFound().build();
    }





    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String reviewId, @PathVariable String commentId) {
        Review reviewOptional = reviewRepository.getReviewByReviewId(reviewId);
        if (reviewOptional != null) {
            Comment commentOptional = commentRepository.getCommentByCommentId(commentId);
            if (commentOptional != null) {
                commentRepository.deleteByCommentId(commentId);
                return ResponseEntity.ok("Comment deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
