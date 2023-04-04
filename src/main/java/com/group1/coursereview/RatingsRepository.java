package com.group1.coursereview;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository extends MongoRepository<Ratings, String> {
}
