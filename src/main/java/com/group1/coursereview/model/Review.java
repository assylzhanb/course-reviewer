package com.group1.coursereview.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    @NotNull
    private String courseCode;
    private String professorId;
    @NotNull
    private String userId;
    @NotNull
    private int courseRating;
    @NotNull
    private String reviewBody;
    private LocalDateTime createdAt;

    public Review() {
        this.createdAt = LocalDateTime.now();
    }

    public Review(String courseCode, String professorId, String userId, int courseRating, String reviewBody) {
        this.courseCode = courseCode;
        this.professorId = professorId;
        this.userId = userId;
        this.courseRating = courseRating;
        this.reviewBody = reviewBody;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(int courseRating) {
        this.courseRating = courseRating;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
