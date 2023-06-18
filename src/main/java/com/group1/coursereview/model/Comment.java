package com.group1.coursereview.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String reviewId;
    private String commentId;
    private String userId;
    private String text;

    public Comment() {}

    public Comment(String reviewId, String commentId, String userId, String text) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.text = text;
        this.commentId = commentId;
    }

    // Getters and setters

    public String getId() {
        return id;
    }
    public String getCommentId(){
        return commentId;
    }
    public void setCommentId(String commentId){
        this.commentId =commentId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
