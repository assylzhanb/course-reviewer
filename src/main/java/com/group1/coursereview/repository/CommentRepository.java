package com.group1.coursereview.repository;

import com.group1.coursereview.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> getAllByReviewId(String reviewId);
}
