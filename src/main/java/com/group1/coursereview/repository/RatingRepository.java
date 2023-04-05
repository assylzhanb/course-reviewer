package com.group1.coursereview.repository;
import com.group1.coursereview.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating, String> {
}