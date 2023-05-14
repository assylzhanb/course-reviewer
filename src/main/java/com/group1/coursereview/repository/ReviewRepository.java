package com.group1.coursereview.repository;


import com.group1.coursereview.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> getAllByCourseCode(String courseCode);

    void deleteAllByCourseCode(String courseCode);

    //List<Review> getAllByCourseAndProfessorId(String professorId);
}

