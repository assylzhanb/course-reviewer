package com.group1.coursereview.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String courseId;
    private String professorId;
    private String userId;
    private int courseRating;
    private int professorRating;
    private String comments;

    public Review() {}

    public Review(String courseId, String professorId, String userId, int courseRating, int professorRating, String comments) {
        this.courseId = courseId;
        this.professorId = professorId;
        this.userId = userId;
        this.courseRating = courseRating;
        this.professorRating = professorRating;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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

    public int getProfessorRating() {
        return professorRating;
    }

    public void setProfessorRating(int professorRating) {
        this.professorRating = professorRating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
